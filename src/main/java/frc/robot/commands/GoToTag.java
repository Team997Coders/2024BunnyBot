package frc.robot.commands;

import java.util.List;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.GoalEndState;
import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Camera;

public class GoToTag extends Command {

  private final Drivetrain drivebase;
  private final Camera frontCamera;
  private Command currentPath;

  public GoToTag(Drivetrain drivebase, Camera frontCamera) {
    this.drivebase = drivebase;
    this.frontCamera = frontCamera;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivebase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Pose2d origin = new Pose2d();
    List<Translation2d> waypoints = PathPlannerPath.bezierFromPoses
    (
      origin,
      origin.plus(frontCamera.robotToTag())
    );

    PathPlannerPath path = new PathPlannerPath(
        waypoints,
        new PathConstraints(3.0, 3.0, 2 * Math.PI, 4 * Math.PI),
        new GoalEndState(0, new Rotation2d()) // Goal end state. You can set a holonomic rotation here. If using a differential drivetrain, the rotation will have no effect.
        );
    this.currentPath = AutoBuilder.followPath(path);
    }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    this.currentPath.schedule();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  
}
