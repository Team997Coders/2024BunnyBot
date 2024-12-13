// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.Indexer;
import frc.robot.Constants.PickerUper;
import frc.robot.Exceptions.invalidCrateMotorOutput;
import frc.robot.subsystems.CratePickerUper;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class SwitchIndexerMode extends Command {
  private IndexCommand m_Indexer;

  public SwitchIndexerMode(IndexCommand indexer) {
    m_Indexer = indexer;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (m_Indexer.m_automatic)
    {
        m_Indexer.setAutomatic(false);
    } else
    {
        m_Indexer.setAutomatic(true);
    }
    this.cancel();
  }

  @Override
  public void end(boolean interrupted) 
  {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
