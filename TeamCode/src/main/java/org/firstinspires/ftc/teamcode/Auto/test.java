package org.firstinspires.ftc.teamcode.Auto;


import com.acmerobotics.roadrunner.AccelConstraint;
import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.MinVelConstraint;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.PinpointDrive;
import org.firstinspires.ftc.teamcode.robit.Arms;
import org.firstinspires.ftc.teamcode.robit.Extendo;
import org.firstinspires.ftc.teamcode.robit.GLOBALS;
import org.firstinspires.ftc.teamcode.robit.Lift;

import java.util.Arrays;

@Autonomous(name = "test")
public class test extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {


        Lift lift = new Lift(hardwareMap);
        Extendo extendo = new Extendo(hardwareMap);
        Arms arms = new Arms(hardwareMap);
        ElapsedTime timp = new ElapsedTime();

        //INITIALIZARE
        lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Init);
        arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);
        arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
        arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lat);

        Pose2d start = new Pose2d(new Vector2d (0,0),Math.toRadians(0));
        PinpointDrive drive = new PinpointDrive(hardwareMap, start);


        VelConstraint velPuternic = new MinVelConstraint( Arrays.asList(
                new TranslationalVelConstraint(120),
                new AngularVelConstraint(Math.PI / 2)
        ));
        AccelConstraint accelPuternic = new ProfileAccelConstraint(-50, 80);

        waitForStart();
        timp.startTime();

//        Actions.runBlocking(
//                drive.actionBuilder(start)
//                        .strafeToConstantHeading(new Vector2d(18, 34.1))
//                        .strafeToConstantHeading(new Vector2d(15, 34.1))
//                        .strafeToConstantHeading(new Vector2d(23, 29))
//                        .strafeToLinearHeading(new Vector2d(17, 34), Math.toRadians(0))
//                        .strafeToLinearHeading(new Vector2d(20, 18), Math.toRadians(0))
//                        .strafeToLinearHeading(new Vector2d(17, 34), Math.toRadians(0))
//                        .strafeToLinearHeading(new Vector2d(37, 24), Math.toRadians(90))
//                        .strafeToLinearHeading(new Vector2d(17, 34), Math.toRadians(0))
//                        .build());

        Actions.runBlocking(
                drive.actionBuilder(start)
                        .strafeToConstantHeading(new Vector2d(18, 34.1))
                        .build());
        sleep( 3000 );
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))
                        .strafeToConstantHeading(new Vector2d(0, 0))

                        .build());





        telemetry.addData("timp", timp.time());
        telemetry.update();
        sleep(200000);

    }
}
