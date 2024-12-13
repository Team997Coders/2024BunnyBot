package frc.robot.commands;

import java.util.function.Supplier;


import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

public class IndexCommand<pull> extends Command{
    
    private Indexer m_index;
    public boolean m_automatic = true;
    public Supplier<Boolean> m_spinIndexer;
    public Supplier<Boolean> m_openServo;
    public int balloonCounter = 0;
    public boolean lastBottomBeamBreak = false;
    public boolean lastTopBeamBreak = false;
    public boolean lastColor = false;

    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(Constants.Indexer.i2cPort);

    private final ColorMatch m_colorMatcher = new ColorMatch();

    private final Color kBlueTarget = new Color(0.143, 0.427, 0.429);
    private final Color kGreenTarget = new Color(0.197, 0.561, 0.240);
    private final Color kRedTarget = new Color(0.561, 0.232, 0.114);
    private final Color kYellowTarget = new Color(0.361, 0.524, 0.113);

    private int delayCounter = 0;

    private boolean exiting = false;

    public IndexCommand(Indexer index, boolean automatic, Supplier<Boolean> spinIndexer, Supplier<Boolean> openServo){
        m_index = index;
        m_spinIndexer = spinIndexer;
        m_automatic = automatic;
        m_openServo = openServo; 
        
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);

        addRequirements(index);
    }

    @Override
    public void initialize() {
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() { 

      Color detectedColor = m_colorSensor.getColor();

      ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

      delayCounter++;

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
          } else if (!m_index.getBottomBeamBreak() && lastBottomBeamBreak)
          {
            lastBottomBeamBreak = false;
          }

          if (match.color == kRedTarget) //Balloon is Red
          {
            if (!lastColor)
            {
              lastColor = true;
              if (m_index.getAlliance() == Alliance.Blue)
              {
                m_index.moveEscapeServo(Constants.Indexer.escapeServoAngleDegrees);
              } else
              {
                m_index.moveEscapeServo(Constants.Indexer.closedServoAngleDegrees);
              }
            }
          } else if (match.color == kBlueTarget) //Balloon is Blue
          {
            if (!lastColor)
            {
              lastColor = true;
              if (m_index.getAlliance() == Alliance.Red)
              {
                m_index.moveEscapeServo(Constants.Indexer.escapeServoAngleDegrees);
              } else
              {
                m_index.moveEscapeServo(Constants.Indexer.closedServoAngleDegrees);
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
      } else if (!m_index.getTopBeamBreak() && lastTopBeamBreak)
      {
        lastTopBeamBreak = false;
        exiting = true;
        delayCounter = 0;
      }
      if (delayCounter > 100 && exiting)
      {
        m_index.spinFans(0);
        m_index.moveEscapeServo(Constants.Indexer.closedServoAngleDegrees);
        exiting = false;
        balloonCounter = 0;
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
          m_index.moveEscapeServo(Constants.Indexer.escapeServoAngleDegrees);
      } else
      {
          m_index.moveEscapeServo(Constants.Indexer.closedServoAngleDegrees);
      }
    }

    String colorString;
    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    SmartDashboard.putNumber("Balloon Counter", balloonCounter);
    SmartDashboard.putBoolean("top beam break", m_index.getTopBeamBreak());
    SmartDashboard.putBoolean("bottom beam break", m_index.getBottomBeamBreak());

    SmartDashboard.putString("Color detected", colorString);

    SmartDashboard.putBoolean("Automatic", m_automatic);
  }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }

    public void setAutomatic(boolean automatic)
    {
      m_automatic = automatic;
      balloonCounter = 0;
    }
  }