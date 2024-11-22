package frc.robot.subsystems;

import java.util.Optional;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.lib.ColorSensor;

public class Indexer extends SubsystemBase { 

    private final VictorSP fanChunk = new VictorSP(Constants.Indexer.fanChunkID);

    private final VictorSP escapefan = new VictorSP(Constants.Indexer.escapefanID);

    private final ColorSensor colorSensor = new ColorSensor();
    

    
     Optional<Alliance> ally = DriverStation.getAlliance();
     
     private final DigitalInput bottombBeamBreak = new DigitalInput(Constants.Indexer.bottombBeamBreakID);
     private final DigitalInput topBeamBreak = new DigitalInput(Constants.Indexer.bottombBeamBreakID);
     private final Servo servo1 = new Servo(Constants.Indexer.servo1ID);


     public Indexer(){
      
     }

     public void spinFans(double speed){
      fanChunk.set(speed);
     }
     public void spinEscapeFan(double speed){
      escapefan.set(speed);
     }

     public boolean getBottomBeamBreak(){
      return bottombBeamBreak.get();
      // verify if true means something there
     }

     public boolean getTopBeamBreak(){
      return topBeamBreak.get();
      // verify if true means something there
     }

     public void moveServo1(double angleDegrees){
       servo1.setAngle(angleDegrees);
     }

     public boolean isColorGood() {
      Color color = colorSensor.readColor();
      double blue = color.blue;
      double red = color.red;
      Alliance estimatedColor = null;
      boolean output = false;
      
      if (blue-red >= 25) {
        estimatedColor = Alliance.Blue;
      } else if (red-blue >= 25) {
        estimatedColor = Alliance.Red;
      }

      if (estimatedColor == ally.get()) {
        output = true;
      } else {
        output = false;
      }
      
      return output;
     }

     

}
