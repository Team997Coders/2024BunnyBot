package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class Drive extends Command {
    private final Drivetrain drivetrain = new Drivetrain();


    public Drive(double turnInput, double driveInput) {

    }


    @Override 
    public void initialize() {

    }
    
    @Override
    public void execute() {
      







    }


    @Override
    public void end(boolean interrupted) {}
  

    @Override
    public boolean isFinished() {
      return false;
    }
}
