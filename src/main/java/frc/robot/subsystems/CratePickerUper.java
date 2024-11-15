// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants.PickerUper;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CratePickerUper extends SubsystemBase 
{
  public CANSparkMax LinkageMotor;
  public AbsoluteEncoder LinkageEncoder;
  public Double TargetPosition;

  public DigitalInput topLimitSwitch;
  public DigitalInput bottomLimitSwitch;

  public CratePickerUper() 
  {
    LinkageMotor = new CANSparkMax(PickerUper.LinkageMotorID, MotorType.kBrushless);
    LinkageMotor.setIdleMode(IdleMode.kBrake);
    LinkageEncoder = LinkageMotor.getAbsoluteEncoder();
    this.TargetPosition = PickerUper.startTargetPosition;

    topLimitSwitch = new DigitalInput(PickerUper.topLimitSwitchID);
    bottomLimitSwitch = new DigitalInput(PickerUper.bottomLimitSwitchID);
  }

  public void setMotorVoltage(double volts) 
  {
    LinkageMotor.setVoltage(volts);
  }

  public void setMotorOutput(double motorOutput) 
  {
    LinkageMotor.set(motorOutput);
  }

  public double getLinkageEncoder()
  {
    return LinkageEncoder.getPosition();
  }

  public void setTargetPosition(double newTargetPosition)
  {
    TargetPosition = newTargetPosition;
  }

  public Command setTargetPositionCommand(double newTargetPosition) {
    return runOnce(
        () -> {
          TargetPosition = newTargetPosition;
        });
  }

  public boolean getTopLimitSwitch()
  {
    return topLimitSwitch.get();
  }

  public boolean getBottomLimitSwitch()
  {
    return bottomLimitSwitch.get();
  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
}
