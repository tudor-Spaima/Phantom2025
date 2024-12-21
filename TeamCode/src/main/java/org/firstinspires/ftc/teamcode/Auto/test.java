package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

public class test  extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d startPose = new Pose2d(0, 0, Math.toRadians(0));
        MecanumDrive drive = new MecanumDrive(hardwareMap, startPose);



    }
}
