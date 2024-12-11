package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Constants.Intake;
import frc.robot.subsystems.Indexer;

public class IndexCommand<pull> extends Command{
    
    private Indexer m_index;
    private IntakeCommand m_intakeCommand;

    public boolean m_automatic = true;
    public boolean m_spinIndexer;
    public boolean m_openServo;

    public IndexCommand(Indexer index, IntakeCommand intakeCommand,boolean automatic, boolean spinIndexer,boolean openServo){
        m_index = index;
        m_intakeCommand = intakeCommand;
        m_spinIndexer = spinIndexer;
        m_automatic = automatic;
        m_openServo = openServo;
    }

    @Override
    public void initialize() {

       
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() { //TEST PLEASE

      if(m_automatic) {
      int balloonCounter = 0;

    if (balloonCounter == 0) {
      m_intakeCommand.execute();
    }
    
    if (m_index.getBottomBeamBreak()) {
      balloonCounter++;
      m_intakeCommand.end(true);
      m_index.spinFans(1);


      if (m_index.isColorGood() == false) {
        m_index.moveServo1(Constants.Indexer.servoAngleDegreesOpen);
      } else if (m_index.isColorGood()) {
        m_index.moveServo1(Constants.Indexer.servoAngleDegreesClosed);
      }

    }
    
    if (m_index.getTopBeamBreak()) {
      balloonCounter--;
    }
  } else if (m_automatic == false) {
      if (m_spinIndexer){
        m_index.spinFans(1);
      } else{

      

      }

      if (m_openServo){
        m_index.moveServo1(Constants.Indexer.servoAngleDegreesOpen);
      } else{

      }
  }

    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
    
    
      
    }