// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.PickerUper;
import frc.robot.Exceptions.invalidCrateMotorOutput;
import frc.robot.subsystems.CratePickerUper;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class CratePickerUperManualUp extends Command {
  private CratePickerUper cratePickerUper;

  public CratePickerUperManualUp(CratePickerUper cratePickerUperSubsystem) {
    cratePickerUper = cratePickerUperSubsystem;

    addRequirements(cratePickerUper);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    try
    {
      //TODO: Make sure motor is spinning the right direction
        cratePickerUper.outputLimitCheck(PickerUper.manualOutput);
    } catch(invalidCrateMotorOutput exception)
    {
        exception.emergencyStopMessage();
        cratePickerUper.EmergencyStop();
    }
  }

  @Override
  public void end(boolean interrupted) 
  {
    cratePickerUper.setTargetToCurrentPosition();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
