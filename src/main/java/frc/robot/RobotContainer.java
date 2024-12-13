// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.CratePickerUperManualDown;
import frc.robot.commands.CratePickerUperManualUp;
import frc.robot.commands.Drive;
import frc.robot.commands.CratePickerUperNextDown;
import frc.robot.commands.CratePickerUperNextUp;
import frc.robot.commands.IndexCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.SwitchIndexerMode;
import frc.robot.subsystems.Intake;

import java.util.function.BooleanSupplier;

import edu.wpi.first.networktables.BooleanSubscriber;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.CratePickerUper;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
 // private final CratePickerUper cratePickerUper = new CratePickerUper();

  private final Drivetrain m_drivetrain = new Drivetrain();
  
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

      
  private final Intake m_intake = new Intake();
  private final IntakeCommand m_IntakeCommand = new IntakeCommand(m_intake);

  private final Indexer m_indexer = new Indexer();
  private final CratePickerUper m_cratePickerUper = new CratePickerUper();

  private final IndexCommand m_IndexCommand = new IndexCommand(
      m_indexer, false, 
      () -> (m_driverController.b().getAsBoolean()), 
      () -> (m_driverController.y().getAsBoolean()));

  Trigger isThereCrateTrigger = new Trigger(m_cratePickerUper.crateSensor::get);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    m_indexer.setDefaultCommand(m_IndexCommand);

    m_driverController.leftBumper().onTrue(new SwitchIndexerMode(m_IndexCommand));


    m_drivetrain.setDefaultCommand(new Drive(
      m_drivetrain,
      () -> m_driverController.getRightY(), 
      () -> m_driverController.getRightX()));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    // m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    
    m_driverController.a().whileTrue(m_IntakeCommand);

    //isThereCrateTrigger.whileTrue(m_IndexCommand);


    m_driverController.povUp().whileTrue(new CratePickerUperManualUp(m_cratePickerUper));
    m_driverController.povDown().whileTrue(new CratePickerUperManualDown(m_cratePickerUper));
    m_driverController.povLeft().onTrue(new CratePickerUperNextUp(m_cratePickerUper));
    m_driverController.povRight().onTrue(new CratePickerUperNextDown(m_cratePickerUper));
    
  }

  /**.
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  public Command getAutonomousCommand() {
    return null;
  }
}
