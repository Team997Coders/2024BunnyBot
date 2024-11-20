package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase { 

    private final VictorSP fanChunk = new VictorSP(Constants.Indexer.fanChunkID);

    private final VictorSP escapefan = new VictorSP(Constants.Indexer.escapefanID);

  // color sensor here 
    
     private final DigitalInput bottombBeamBreak = new DigitalInput(Constants.Indexer.bottombBeamBreakID);
     private final DigitalInput topBeamBreak = new DigitalInput(Constants.Indexer.bottombBeamBreakID);

     public Indexer(){
      
     }
}
