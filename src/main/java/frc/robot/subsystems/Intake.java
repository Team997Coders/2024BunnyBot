package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

    private final CANSparkMax armSparkMax = new CANSparkMax(Constants.Intake.armSparkMaxCANID, MotorType.kBrushless);
    private final RelativeEncoder armEncoder = armSparkMax.getEncoder();

    private final VictorSP intakeMotor = new VictorSP(Constants.Intake.intakeMotorID);

    private final DigitalInput topLimitSwitch = new DigitalInput(Constants.Intake.topLimitSwitchID);
    private final DigitalInput bottomLimitSwitch = new DigitalInput(Constants.Intake.bottomLimitSwitchID);

    public Intake(){
        
    }
    
}
