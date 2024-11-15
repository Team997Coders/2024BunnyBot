// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.PickerUper;
import frc.robot.subsystems.CratePickerUper;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class CratePickerUperController extends Command {
  private CratePickerUper cratePickerUper;
  private PIDController cratePickerUperPidController;

  public CratePickerUperController(CratePickerUper cratePickerUperSubsystem) {
    cratePickerUper = cratePickerUperSubsystem;
    cratePickerUperPidController = new PIDController(
      PickerUper.pid.p, PickerUper.pid.i, PickerUper.pid.d);
    addRequirements(cratePickerUper);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    //TODO: Make sure greater then less then symbols are correct for limit switch positions
    if (!(cratePickerUper.getTopLimitSwitch() & cratePickerUper.TargetPosition >= PickerUper.topLimitSwitchPosition))
    {
      if (!(cratePickerUper.getBottomLimitSwitch() & cratePickerUper.TargetPosition <= PickerUper.bottomLimitSwitchPosition))
      {
        calculatePid();
      } else {
        stopMotor();
      }
    } else {
      stopMotor();
    }
  }

  public void calculatePid() 
  {
    cratePickerUper.setMotorOutput(cratePickerUperPidController.calculate(
      cratePickerUper.getLinkageEncoder(), cratePickerUper.TargetPosition));
  }

  public void stopMotor()
  {
    cratePickerUper.setMotorOutput(0);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
