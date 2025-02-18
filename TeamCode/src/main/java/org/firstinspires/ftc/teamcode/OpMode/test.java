package org.firstinspires.ftc.teamcode.OpMode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.outoftheboxrobotics.photoncore.PhotonCore;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.CommandBase.Commands;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.PinpointDrive;
import org.firstinspires.ftc.teamcode.robit.Arms;
import org.firstinspires.ftc.teamcode.robit.Extendo;
import org.firstinspires.ftc.teamcode.robit.Lift;
import org.firstinspires.ftc.teamcode.robit.Senzori;
import org.opencv.core.Mat;


@TeleOp(name = "TEST")
public class test extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d initialPose = new Pose2d(new Vector2d(0, 0), Math.toRadians(0));
        Lift lift = new Lift( hardwareMap );
        Extendo extendo = new Extendo( hardwareMap );
        Arms arms = new Arms( hardwareMap );
        Senzori senzori = new Senzori( hardwareMap );
        Commands commands = new Commands(hardwareMap);
        PinpointDrive drive = new PinpointDrive(hardwareMap, initialPose);
        PhotonCore.ExperimentalParameters ph = new PhotonCore.ExperimentalParameters();
        ph.setMaximumParallelCommands( 8 );
        ph.setSinglethreadedOptimized( false );
        Teleop.DriveController driveController = new Teleop.DriveController( telemetry, gamepad1, drive, extendo, senzori, hardwareMap );
        Thread driveControllerThread = new Thread( driveController );




        if (driveControllerThread.isAlive()) {
            driveController.stopThread();
            driveControllerThread.interrupt();
            driveControllerThread.join();
        }



        waitForStart();
        driveControllerThread.start();

        while(opModeIsActive()) {




            if (gamepad1.right_bumper) {

                driveController.stopThread();
                sleep(200);


                drive.updatePoseEstimate();
                TrajectoryActionBuilder finish_spec_new = drive.actionBuilder(drive.pose)
                        .strafeToLinearHeading(new Vector2d(0, 0), Math.toRadians(0));


                Actions.runBlocking(
                        new SequentialAction(
                                finish_spec_new.build()
                        ));
                sleep(200);

                driveController.continueThread();



            }
        }




    }
}
