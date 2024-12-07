package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;

public class Drive extends Command {

    private double m_forwardSupplier;
    private double m_turnSupplier;
    private Drivetrain m_drivetrain;

    public Drive(Drivetrain drivetrain, Supplier<Double> forwardSupplier, Supplier<Double> turnSupplier) 
    {
      m_forwardSupplier = forwardSupplier.get();
      m_turnSupplier = turnSupplier.get();
      m_drivetrain = drivetrain;

      addRequirements(drivetrain);
    }


    @Override 
    public void initialize() {

    }
    
    @Override
    public void execute() {
      double rightPower = m_forwardSupplier - m_turnSupplier;
      double leftPower =  m_forwardSupplier + m_turnSupplier;
      m_drivetrain.runMotors(rightPower, leftPower);
      
    }


    @Override
    public void end(boolean interrupted) {}
  

    @Override
    public boolean isFinished() {
      return false;
    }
}
