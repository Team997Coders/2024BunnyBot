package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase  {
    private final VictorSP frontRight = new VictorSP(Constants.Drivetrain.frontRightID);
    private final VictorSP frontLeft = new VictorSP(Constants.Drivetrain.frontLeftID);
    private final VictorSP backRight = new VictorSP(Constants.Drivetrain.backRightID);
    private final VictorSP backLeft = new VictorSP(Constants.Drivetrain.backLeftID);

    private final AHRS navx = new AHRS();

    private final Encoder rightEncoder = new Encoder(Constants.Drivetrain.rightEncoderInputA, Constants.Drivetrain.rightEncoderInputB);
    private final Encoder leftEncoder = new Encoder(Constants.Drivetrain.leftEncoderInputA, Constants.Drivetrain.leftEncoderInputB);
    

    public void runMotors(double rightVoltage, double leftVoltage){
        frontRight.setVoltage(rightVoltage);
        backRight.setVoltage(rightVoltage);
        frontLeft.setVoltage(leftVoltage);
        backLeft.setVoltage(leftVoltage);
    }

    public double getRightDistance(){
        return rightEncoder.getDistance();    
    }
    
    public double getLeftDistance(){
        return leftEncoder.getDistance();
    }
    
    public void resetRightEncoder(){
        rightEncoder.reset();
    }

    public void resetLeftEncoder(){
        leftEncoder.reset();
    }

    public void resetGyro(){
        navx.reset();
    }

}
