package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeCommand extends Command{

    private Intake m_intake;

    public IntakeCommand(Intake intake){
        m_intake = intake;
    }


  @Override 
  public void initialize(){}

  @Override
  public void execute() {
    m_intake.spinFlapper(Constants.Intake.defaultSpinnerSpeed);
  }

  @Override 
  public void end(boolean interrupted) {
    m_intake.spinFlapper(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}




