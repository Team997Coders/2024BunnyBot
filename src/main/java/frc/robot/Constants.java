// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;

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
    public static final int topLimitSwitchID = 0;
    public static final int bottomLimitSwitchID = 0;
    //TODO: fix the inequalities in cratePickerUperController Command for corrected limit switch locations
    public static final int topLimitSwitchPosition = 0;
    public static final int bottomLimitSwitchPosition = 0;
    //TODO: Tune PID for the CratePickerUper
    public static class pid {
      public static final double p = 0.1;
      public static final double i = 0;
      public static final double d = 0; 
    }
  }
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
}
