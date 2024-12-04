// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.PickerUper;
import frc.robot.Exceptions.invalidCrateMotorOutput;
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
    try
    {
    cratePickerUper.outputLimitCheck(cratePickerUperPidController.calculate(
      cratePickerUper.getLinkageEncoder(), cratePickerUper.TargetPosition));
    } catch(invalidCrateMotorOutput exception)
    {
      exception.emergencyStopMessage();
      cratePickerUper.EmergencyStop();
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
