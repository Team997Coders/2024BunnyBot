package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;

public class Drive extends Command {
    private final Drivetrain drivetrain = new Drivetrain();

    private double m_rightInput;
    private double m_leftInput;

    public Drive(double rightInput, double leftInput) {

      m_rightInput = rightInput;
      m_leftInput = leftInput;

    }


    @Override 
    public void initialize() {

    }
    
    @Override
    public void execute() {
      drivetrain.runMotors(m_rightInput, m_leftInput);
      
    }


    @Override
    public void end(boolean interrupted) {}
  

    @Override
    public boolean isFinished() {
      return false;
    }
}
