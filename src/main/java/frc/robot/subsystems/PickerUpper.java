package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PickerUpper extends SubsystemBase{
    private final CANSparkMax lifterMotor = new CANSparkMax(Constants.PickerUpper.lifterMotorID, MotorType.kBrushless);
    
    private final DigitalInput limitSwitch = new DigitalInput(Constants.PickerUpper.limitSwitchID);

    public PickerUpper(){

    }
}