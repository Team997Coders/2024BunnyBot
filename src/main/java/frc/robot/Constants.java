// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Arrays;
import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.I2C;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class PickerUper {
    //TODO: Get motor id for 4-bar-linkage controller
    public static final int LinkageMotorID = 0;
    //TODO: Get starting target position of the 4 bar linkage (probably up)
    public static final double startTargetPosition = 0.0;
    //TODO: Get Limit switch (Digital input) id# for both top and bottom max
    public static final int topLimitSwitchID = 1;
    public static final int bottomLimitSwitchID = 0;

    public static final int crateSensorID = 0;
    //TODO: fix the inequalities in cratePickerUperController Command for corrected limit switch locations
    public static final int topLimitSwitchPosition = 0;
    public static final int bottomLimitSwitchPosition = 0;
    //TODO: Tune PID for the CratePickerUper
    public static class pid {
      public static final double p = 0.1;
      public static final double i = 0;
      public static final double d = 0; 
    }
    //TODO: Set the four positions for 4-bar-linkage targets
    public static final List<Double> targetPositions = Arrays.asList(
      0.0, 0.0, 0.0, 0.0);
    //      1    2    3    4
    //1: All the way Up
    //2: Crate up holding position
    //3: Crate ready to pick up position
    //4: Crate pick up
    //TODO: set manual output and voltage for cratePickerUper
    public static final double manualOutput = 0.0;
    public static final double manualVoltage = 0.0;
  }
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class Drivetrain  {
    public static final int frontRightCANID = 0; //CAN
    public static final int frontLeftCANID = 1;
    public static final int backRightCANID = 2;
    public static final int backLeftCANID = 3;

    public static final int rightEncoderInputA = 0; //DIO
    public static final int rightEncoderInputB = 1;
     public static final int leftEncoderInputA = 2;
    public static final int leftEncoderInputB = 3;
  }
    public static class Intake {
      public static final int intakeMotorID = 0; //PWM
      public static final int topLimitSwitchID = 1;
      public static final int bottomLimitSwitchID = 2;

      public static final double defaultSpinnerSpeed = 0.3;
    }
    
   public static class Indexer {
    public static final int FanChunkID = 0; //PWM
    public static final int escapeServoID = 1;

    public static final int bottomBeamBreakID = 0; //DIO
    public static final int topBeamBreakID = 1;

    public static final double escapeServoAngleDegrees = 0;
    public static final double closedServoAngleDegrees = 90;

    public static final I2C.Port i2cPort = I2C.Port.kMXP;
   }



}
