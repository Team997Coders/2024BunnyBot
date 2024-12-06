// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class Drivetrain  {
    public static final int frontRightID = 0;
    public static final int frontLeftID = 0;
    public static final int backRightID = 0;
    public static final int backLeftID = 0;

    public static final int rightEncoderInputA = 0;
    public static final int rightEncoderInputB = 0;

    public static final int leftEncoderInputA = 0;
    public static final int leftEncoderInputB = 0;

    //TODO: get track width of drive base, probably important
    public static final double TrackWidth = 0.0;
  }
    public static class Intake {
      public static final int armSparkMaxCANID = 0;

      public static final int intakeMotorID = 0;
      public static final int topLimitSwitchID = 0;
      public static final int bottomLimitSwitchID = 0;
    }
    
   public static class Indexer {
    public static final int fanChunkID = 0;
    public static final int escapefanID = 0;
    public static final int bottombBeamBreakID = 0;
    public static final int topBeamBreakID = 0;
    public static final int servo1ID = 0;
   }



}
