// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.IndexCommand;
import frc.robot.commands.IntakeCommand;
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
  private final Drivetrain m_drivetrain = new Drivetrain();
  
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);


  private final Intake m_intake = new Intake();
  private final IntakeCommand m_IntakeCommand = new IntakeCommand(m_intake);

  private final Indexer m_indexer = new Indexer();
  private final IndexCommand m_IndexCommand = new IndexCommand(m_indexer, m_IntakeCommand, !m_driverController.button(8).getAsBoolean(), m_driverController.b().getAsBoolean(), m_driverController.y().getAsBoolean());

  private final CratePickerUper m_CratePickerUper = new CratePickerUper();


  Trigger isThereCrateTrigger = new Trigger(m_CratePickerUper.crateSensor::get);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_drivetrain.runMotors(m_driverController.getRightY(), m_driverController.getLeftY());
    // Configure the trigger bindings
    configureBindings();

    

    

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

    isThereCrateTrigger.whileTrue(m_IndexCommand);


    
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
