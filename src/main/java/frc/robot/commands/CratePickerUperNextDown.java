// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Exceptions.invalidNextPosition;
import frc.robot.subsystems.CratePickerUper;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class CratePickerUperNextDown extends Command {
  private CratePickerUper cratePickerUper;

  public CratePickerUperNextDown(CratePickerUper cratePickerUperSubsystem) {
    cratePickerUper = cratePickerUperSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    try
    {
    cratePickerUper.moveToNextPositionDown();
    } catch(invalidNextPosition exception)
    {
      exception.invalidNextPositionMessage();
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
