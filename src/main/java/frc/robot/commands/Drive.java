package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;

public class Drive extends Command {
    private final Drivetrain drivetrain = new Drivetrain();

    private double m_forwardSupplier;
    private double m_turnSupplier;

    public Drive(Supplier<Double> forwardSupplier, Supplier<Double> turnSupplier
) {
      m_forwardSupplier = forwardSupplier.get();
      m_turnSupplier = turnSupplier.get();

    }


    @Override 
    public void initialize() {

    }
    
    @Override
    public void execute() {
      double rightPower = m_forwardSupplier - m_turnSupplier;
      double leftPower =  m_forwardSupplier + m_turnSupplier;
      drivetrain.runMotors(rightPower, leftPower);
      
    }


    @Override
    public void end(boolean interrupted) {}
  

    @Override
    public boolean isFinished() {
      return false;
    }
}
