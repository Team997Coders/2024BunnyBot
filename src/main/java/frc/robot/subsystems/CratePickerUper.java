// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import java.util.List;

import com.ctre.phoenix.GadgeteerUartClient.GadgeteerUartStatus;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants.PickerUper;
import frc.robot.Exceptions.invalidCrateMotorOutput;
import frc.robot.Exceptions.invalidNextPosition;
import frc.robot.Exceptions.invalidTargetPosition;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CratePickerUper extends SubsystemBase 
{
  public CANSparkMax LinkageMotor;
  public AbsoluteEncoder LinkageEncoder;
  public Double TargetPosition;

  public DigitalInput topLimitSwitch;
  public DigitalInput bottomLimitSwitch;
  
  public DigitalInput crateSensor;

  //Triggered when Motor runs into Limit Switch and poses a danger to the robot
  public static boolean LinkageMotorEStop = false;

  public CratePickerUper() 
  {
    LinkageMotor = new CANSparkMax(PickerUper.LinkageMotorID, MotorType.kBrushless);
    LinkageMotor.setIdleMode(IdleMode.kBrake);
    LinkageEncoder = LinkageMotor.getAbsoluteEncoder();
    this.TargetPosition = PickerUper.startTargetPosition;

    topLimitSwitch = new DigitalInput(PickerUper.topLimitSwitchID);
    bottomLimitSwitch = new DigitalInput(PickerUper.bottomLimitSwitchID);

    crateSensor = new DigitalInput(PickerUper.crateSensorID);
  }
  // Sensor Status 
  public boolean isThereACrate(){
    if (crateSensor.get()){
      return true;
    } else{
      return false;
    }
  }

  public void setMotorVoltage(double volts) 
  {
    if (!LinkageMotorEStop)
    {
    LinkageMotor.setVoltage(volts);
    }
  }

  public void setMotorOutput(double motorOutput) 
  {
    if (!LinkageMotorEStop)
    {
    LinkageMotor.set(motorOutput);
    }
  }

  public void EmergencyStop()
  {
    LinkageMotorEStop = true;
  }

  public double getLinkageEncoder()
  {
    return LinkageEncoder.getPosition();
  }

  public void setTargetPosition(double newTargetPosition)
  {
    TargetPosition = newTargetPosition;
  }

  public void setTargetToCurrentPosition()
  {
    setTargetPosition(getLinkageEncoder());
  }

  public boolean getTopLimitSwitch()
  {
    return topLimitSwitch.get();
  }

  public boolean getBottomLimitSwitch()
  {
    return bottomLimitSwitch.get();
  }

  public void targetPositionLimitCheck(double targetPosition) throws invalidTargetPosition
  {
    //TODO: Make sure greater then less then symbols are correct for limit switch positions
    if (targetPosition > PickerUper.topLimitSwitchPosition || targetPosition < PickerUper.bottomLimitSwitchPosition)
    {
      throw new invalidTargetPosition("targetPosition out of bounds");
    } else 
    {
      setTargetPosition(targetPosition);
    }
  }

  public void outputLimitCheck(double motorOutput) throws invalidCrateMotorOutput
  {
    //TODO: Make sure greater then less then symbols are correct for limit switch positions
    if ((getTopLimitSwitch() & motorOutput > 0))
    {
      setMotorOutput(0);
      throw new invalidCrateMotorOutput("Emergency stop; invalid motorOutput into topLimitSwitch");
    } else if ((getBottomLimitSwitch() & motorOutput < 0))
    {
      setMotorOutput(0);
      throw new invalidCrateMotorOutput("Emergency stop; invalid motorOutput into bottomLimitSwitch");
    } else 
    {
      setMotorOutput(motorOutput);
    }
  }

  public void moveToNextPositionDown() throws invalidNextPosition
  {
    //TODO: Make sure greater then less then symbols are correct for motor direction
    List<Double> targetPositions = PickerUper.targetPositions;
    double currentPosition = getLinkageEncoder();
    for(int targetPosition = 1; targetPosition > targetPositions.size(); targetPosition++)
    {
      if (currentPosition > targetPositions.get(targetPosition))
      {
        setTargetPosition(targetPositions.get(targetPosition));
        return;
      }
    }
    throw new invalidNextPosition("No next position available; direction: down");
  }

  public void moveToNextPositionUp() throws invalidNextPosition
  {
    //TODO: Make sure greater then less then symbols are correct for motor direction
    List<Double> targetPositions = PickerUper.targetPositions;
    double currentPosition = getLinkageEncoder();
    for(int targetPosition = targetPositions.size(); targetPosition > 1; targetPosition--)
    {
      if (currentPosition < targetPositions.get(targetPosition))
      {
        setTargetPosition(targetPositions.get(targetPosition));
        return;
      }
    }
    throw new invalidNextPosition("No next position available; direction: up");
  }

  @Override
  public void periodic() {}
}
