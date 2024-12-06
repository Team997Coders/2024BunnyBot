package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.controllers.PPLTVController;
import com.pathplanner.lib.util.ReplanningConfig;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelPositions;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.Drive;

public class Drivetrain extends SubsystemBase  {
    private final TalonSRX frontRight = new TalonSRX(Constants.Drivetrain.frontRightID);
    private final TalonSRX frontLeft = new TalonSRX(Constants.Drivetrain.frontLeftID);
    private final TalonSRX backRight = new TalonSRX(Constants.Drivetrain.backRightID);
    private final TalonSRX backLeft = new TalonSRX(Constants.Drivetrain.backLeftID);

    private DifferentialDrivePoseEstimator poseEstimator;

    private DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Constants.Drivetrain.TrackWidth);

    private final AHRS navx = new AHRS(); //change input to use usb for navx micro

    private final Encoder rightEncoder = new Encoder(Constants.Drivetrain.rightEncoderInputA, Constants.Drivetrain.rightEncoderInputB);
    private final Encoder leftEncoder = new Encoder(Constants.Drivetrain.leftEncoderInputA, Constants.Drivetrain.leftEncoderInputB);

    public Drivetrain(){
        rightEncoder.reset();
        leftEncoder.reset();
        navx.reset();
        frontRight.setInverted(true);
        backRight.setInverted(true);
    }

    public Pose2d getPose(){
        return poseEstimator.getEstimatedPosition();
    }

    public void resetPose(Pose2d pose){
        poseEstimator.resetPosition(new Rotation2d(), new DifferentialDriveWheelPositions(leftEncoder.getDistance(), rightEncoder.getDistance()), pose);
    }

    public ChassisSpeeds getRobotRelativeSpeeds(){
        return kinematics.toChassisSpeeds(new DifferentialDriveWheelSpeeds(leftEncoder.getRate(), rightEncoder.getRate()));
    }

    public void runMotors(double rightPercentOutput, double leftPercentOutput){
        frontRight.set(ControlMode.PercentOutput, rightPercentOutput);
        backRight.set(ControlMode.PercentOutput, rightPercentOutput);
        frontLeft.set(ControlMode.PercentOutput, leftPercentOutput);
        backLeft.set(ControlMode.PercentOutput, leftPercentOutput);
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

    public double getYaw(){
        return navx.getYaw();
    }

    public void resetGyro(){
        navx.reset();
    }

    
}