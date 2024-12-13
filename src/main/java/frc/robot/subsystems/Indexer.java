package frc.robot.subsystems;

import java.util.Optional;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase { 

    private final Spark fanChunk = new Spark(Constants.Indexer.FanChunkID);

    
     Optional<Alliance> ally = DriverStation.getAlliance();
     
     private final DigitalInput bottomBeamBreak = new DigitalInput(Constants.Indexer.bottomBeamBreakID);
     private final DigitalInput topBeamBreak = new DigitalInput(Constants.Indexer.topBeamBreakID);
     private final Servo escapeServo = new Servo(Constants.Indexer.escapeServoID);

     public Indexer(){}

     public void spinFans(double speed){
      fanChunk.set(speed);
     }

     public boolean getBottomBeamBreak(){
      return !bottomBeamBreak.get();
      // verify if true means something there
     }

     public boolean getTopBeamBreak(){
      return !topBeamBreak.get();
      // verify if true means something there
     }

     public void moveEscapeServo(double angleDegrees){
       escapeServo.setAngle(angleDegrees);
     }

    public Alliance getAlliance()
    {
      return ally.get();
    }
}
