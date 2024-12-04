package frc.robot.subsystems;

import java.util.Optional;

import javax.management.relation.RoleNotFoundException;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.PhotonUtils;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;

public class Camera
{
    private PhotonCamera camera;
    private AprilTagFieldLayout aprilTagFieldLayout = AprilTagFields.k2024Crescendo.loadAprilTagLayoutField();

    public Camera(String cameraName, Transform3d robotToCamera)
    {
        this.camera = new PhotonCamera(cameraName);
    }

    public Transform2d robotToTag()
    {
        var results = this.camera.getLatestResult();
        if (results.hasTargets())
        {
            Transform3d robotToTag = results.getBestTarget().getBestCameraToTarget();
            return new Transform2d(robotToTag.getX(), robotToTag.getY(), robotToTag.getRotation().toRotation2d());
        }
        return new Transform2d();
    }
}
