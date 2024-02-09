// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.vision;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PhotonVision extends SubsystemBase {
  /** Creates a new PhotonVision. */
  public PhotonVision() {
    PhotonCamera robocam = new PhotonCamera("photonvision");

    var result = camera.getLatestValue();

    boolean hasTargets = results.hasTargets();

    List<PhotonTrackedTarget> targets = results.getTargets();

    PhotonTrackedTarget target = result.getBestTarget();

    double yaw = target.getYaw();
    double pitch = target.getPitch();
    double area = target.getArea();
    double skew = target.getSkew();
    Transform2d pose = target.getCameraToTarget();
    List<TargetCorner> corners = target.getCorners();

    int targetID = target.getFiducialId();
    double poseAmbiguity = target.getPoseAmbiguity();
    Transform3d bestCameraToTarget = target.getBestCameraToTarget();
    Transform3d alternateCameraToTarget = target.getAlternateCameraToTarget();

    camera.takeInputSnapshot();
    camera.takeOutputSnapshot();

    Pose3d robotPose = PhotonUtils.estimateFieldToRobotAprilTag(
      target.getBestCameraToTarget(), 
      aprilTagFieldLayout.getTagPose(target.getFiducialId()),
      cameraToRobot);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
