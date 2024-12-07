package frc.robot.commands;

import java.util.function.Supplier;

import javax.lang.model.util.ElementScanner14;

import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Constants.Intake;
import frc.robot.lib.ColorSensor.ColorData;
import frc.robot.subsystems.Indexer;

public class IndexCommand<pull> extends Command{
    
    private Indexer m_index;
    public boolean m_automatic = true;
    public Supplier<Boolean> m_spinIndexer;
    public Supplier<Boolean> m_openServo;
    public int balloonCounter = 0;
    public boolean lastBottomBeamBreak = false;
    public boolean lastTopBeamBreak = false;
    public boolean lastColor = false;

    public IndexCommand(Indexer index, boolean automatic, Supplier<Boolean> spinIndexer, Supplier<Boolean> openServo){
        m_index = index;
        m_spinIndexer = spinIndexer;
        m_automatic = automatic;
        m_openServo = openServo;  

        addRequirements(index);
    }

    @Override
    public void initialize() {
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() { //TEST PLEASE
      ColorData color = m_index.getColorTuples();
      double red = color.red;
      double blue = color.blue;
        if(m_automatic) 
        {
          if (balloonCounter == 0) 
          {
            //m_intakeCommand.schedule();
          }
          if (m_index.getBottomBeamBreak() && !lastBottomBeamBreak) 
          {
            lastBottomBeamBreak = true;
            balloonCounter++;
            //m_intakeCommand.end(true);
            m_index.spinFans(1);
          } else if (!m_index.getBottomBeamBreak())
          {
            lastBottomBeamBreak = false;
          }

          if (red - blue >= 25) //Balloon is Red
          {
            if (!lastColor)
            {
              lastColor = true;
              if (m_index.getAlliance() == Alliance.Red)
              {
                m_index.moveEscapeServo(Constants.Indexer.closedServoAngleDegrees);
              } else
              {
                m_index.moveEscapeServo(Constants.Indexer.escapeServoAngleDegrees);
              }
            }
          } else if (blue - red >= 25) //Balloon is Blue
          {
            if (!lastColor)
            {
              lastColor = true;
              if (m_index.getAlliance() == Alliance.Blue)
              {
                m_index.moveEscapeServo(Constants.Indexer.closedServoAngleDegrees);
              } else
              {
                m_index.moveEscapeServo(Constants.Indexer.escapeServoAngleDegrees);
              }
            }
          } else //There is no balloon
          {
            lastColor = false;
          }

      if (m_index.getTopBeamBreak() && !lastTopBeamBreak) 
      {
        lastTopBeamBreak = true;
        balloonCounter--;
      } else if (!m_index.getTopBeamBreak())
      {
        lastTopBeamBreak = false;
        m_index.spinFans(0);
      }
    } else 
    {
      if (m_spinIndexer.get())
      {
        m_index.spinFans(1);
      } else
      {
        m_index.spinFans(0);
      }
      if (m_openServo.get())
      {
          m_index.moveEscapeServo(Constants.Indexer.closedServoAngleDegrees);
      } else
      {
          m_index.moveEscapeServo(Constants.Indexer.escapeServoAngleDegrees);
      }
    }

    SmartDashboard.putNumber("Balloon Counter", balloonCounter);
    SmartDashboard.putBoolean("top beam break", m_index.getTopBeamBreak());
    SmartDashboard.putBoolean("bottom beam break", m_index.getBottomBeamBreak());
    
    SmartDashboard.putNumber("red", color.red);
    SmartDashboard.putNumber("green", color.green);
    SmartDashboard.putNumber("blue", color.blue);
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