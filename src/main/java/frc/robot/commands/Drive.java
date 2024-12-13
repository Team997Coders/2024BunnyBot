package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;

public class Drive extends Command {
    private Supplier<Double> m_forwardSupplier;
    private Supplier<Double> m_turnSupplier;
    private Drivetrain m_drivetrain = new Drivetrain();

    public Drive(Drivetrain drivetrain, Supplier<Double> forwardSupplier, Supplier<Double> turnSupplier) {
      m_forwardSupplier = forwardSupplier;
      m_turnSupplier = turnSupplier;

      addRequirements(drivetrain);
    }


    @Override 
    public void initialize() {

    }
    
    @Override
    public void execute() {

      double forward = m_forwardSupplier.get();
      double turn = m_turnSupplier.get();

      if (Math.abs(forward) < 0.05 && Math.abs(turn) < 0.05)
      {
         double total = forward + turn;
      if (Math.abs(total) > 1 ) {
        double totalPercent = total / 2;
        double forwardPercent = forward/ total;
        double turnPercent = turn /total;
        
        double newForward = forwardPercent*totalPercent;
        double newTurn = turnPercent*totalPercent;

        forward =(Math.signum(forward)*newForward);
        turn = Math.signum(turn)*newTurn;
        
      }

      double rightPower = -forward + turn * Constants.Controller.movingTurningFactor;
      double leftPower =  -forward - turn * Constants.Controller.movingTurningFactor;

      m_drivetrain.runMotors(rightPower, leftPower);
      } else {
        m_drivetrain.runMotors(0, 0);
      }
  }


    @Override
    public void end(boolean interrupted) {}
  

    @Override
    public boolean isFinished() {
      return false;
    }
}
