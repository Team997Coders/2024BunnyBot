package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;

public class Drive extends Command {
    private Supplier<Double> m_forwardSupplier;
    private Supplier<Double> m_turnSupplier;
    private Drivetrain m_drivetrain;

    public Drive(Drivetrain drivetrain, Supplier<Double> forwardSupplier, Supplier<Double> turnSupplier) {
      m_forwardSupplier = forwardSupplier;
      m_turnSupplier = turnSupplier;
      m_drivetrain = drivetrain;

      addRequirements(drivetrain);
    }


    @Override 
    public void initialize() {

    }
    
    @Override
    public void execute() {
      double rightPower = -m_forwardSupplier.get() + m_turnSupplier.get() * Constants.Controller.movingTurningFactor;
      double leftPower =  -m_forwardSupplier.get() - m_turnSupplier.get() * Constants.Controller.movingTurningFactor;

      if (Math.abs(m_forwardSupplier.get()) < 0.05 && Math.abs(m_turnSupplier.get()) < 0.05)
      {
        rightPower = 0;
        leftPower = 0;
      }

      m_drivetrain.runMotors(rightPower, leftPower);
  }


    @Override
    public void end(boolean interrupted) {}
  

    @Override
    public boolean isFinished() {
      return false;
    }
}
