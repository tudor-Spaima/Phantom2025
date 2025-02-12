package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.AccelConstraint;
import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.MinVelConstraint;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
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


@Autonomous(name="preload")
public class preload extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {


        Lift lift = new Lift(hardwareMap);
        Extendo extendo = new Extendo(hardwareMap);
        Arms arms = new Arms(hardwareMap);

        //INITIALIZARE
        lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
        arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
        arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lat);
        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Transfer);
        arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);

        Pose2d start = new Pose2d(new Vector2d(0, 0), Math.toRadians(0));

        PinpointDrive drive = new PinpointDrive(hardwareMap, start);

        ElapsedTime timp = new ElapsedTime();

        VelConstraint baseVelConstraint = new MinVelConstraint(Arrays.asList(
                new TranslationalVelConstraint(120),
                new AngularVelConstraint(Math.PI / 2)
        ));
        AccelConstraint baseAccelConstraint = new ProfileAccelConstraint(-40, 60);

        VelConstraint velPuternic = new MinVelConstraint(Arrays.asList(
                new TranslationalVelConstraint(120),
                new AngularVelConstraint(Math.PI / 2)
        ));
        AccelConstraint accelPuternic = new ProfileAccelConstraint(-50, 80);


        //Initializare


        waitForStart();
        timp.startTime();

        Actions.runBlocking(
                drive.actionBuilder(start)
                        .afterTime(0, () -> {

                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);
                        })
                        .afterTime(1.5, () -> {
                            new Thread(() -> {
                                arms.pivot.setPosition(0.45);


                            }).start();
                        })

                        .strafeToLinearHeading(new Vector2d(-16, 40), Math.toRadians(-90))


                        .build());


        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(drive.pose.position.x, drive.pose.position.y), drive.pose.heading.toDouble()))

                        //sample
                        .afterTime(0.5, () -> {
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Extended);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.mai_asea);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);

                        })
                        .afterTime(1.2, () -> {
                            new Thread(() -> {
                                arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Colectare);
                                sleep(200);
                                arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Inchis);
                                sleep(200);
                                arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                                extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Retracted);

                            }).start();
                        })
                        .strafeToLinearHeading(new Vector2d(26, 25), Math.toRadians(51))


                        .build());

///
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(drive.pose.position.x, drive.pose.position.y), drive.pose.heading.toDouble()))

                        .afterTime(0.7, () -> {
                            new Thread(() -> {
                                arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lat);
                                arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);

                            }).start();
                        })
                        .strafeToLinearHeading(new Vector2d(38, 18), Math.toRadians(-68))


                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(drive.pose.position.x, drive.pose.position.y), drive.pose.heading.toDouble()))

                        .afterTime(0, () -> {
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Retracted);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lung);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);

                        })

                        .afterTime(1, () -> {
                            new Thread(() -> {
                                arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Colectare);
                                sleep(200);
                                arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Inchis);
                                sleep(200);
                                arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);

                            }).start();
                        })


                        .strafeToLinearHeading(new Vector2d(35, 44), Math.toRadians(0))


                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(drive.pose.position.x, drive.pose.position.y), drive.pose.heading.toDouble()))
                        .afterTime(0, () -> {
                            new Thread(() -> {
                                extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Extended);
                            }).start();
                        })
                        .afterTime(0.8, () -> {
                            new Thread(() -> {
                                arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lat);
                                arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);

                            }).start();
                        })
                        .strafeToLinearHeading(new Vector2d(38, 18), Math.toRadians(-68))


                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(drive.pose.position.x, drive.pose.position.y), drive.pose.heading.toDouble()))

                        .afterTime(0, () -> {
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Retracted);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lung);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);

                        })

                        .afterTime(1.1, () -> {
                            new Thread(() -> {
                                arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Colectare);
                                sleep(200);
                                arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Inchis);
                                sleep(200);
                                arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);

                            }).start();
                        })
                        .strafeToLinearHeading(new Vector2d(45, 45), Math.toRadians(0))


                        .build());


        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(drive.pose.position.x, drive.pose.position.y), drive.pose.heading.toDouble()))
                        .afterTime(0.9, () -> {
                            new Thread(() -> {
                                arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lat);
                                arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);

                            }).start();
                        })
                        .strafeToLinearHeading(new Vector2d(44, 13), Math.toRadians(-61))


                        .build());


        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))
                        .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);

                        })
                        .afterTime(0.3, ()->{ new Thread(()-> {
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);

                        }).start();
                        })
                        .strafeToLinearHeading(new Vector2d(35, 6) ,Math.toRadians(-90))

                        .afterTime(1,()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
                        })
                        .strafeToLinearHeading(new Vector2d(31.5, 1), Math.toRadians(-90))

                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))
                        .afterTime(0, () -> {

                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);
                        })
                        .afterTime(1.5, ()->{ new Thread(()-> {
                            arms.pivot.setPosition(0.45);


                        }).start();})

                        .strafeToConstantHeading(new Vector2d(-10, 40) )




                        .build());

//specimen 2

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))

                        .afterTime(0.5, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);


                        })

                        //.strafeToConstantHeading(new Vector2d(30, 15) )


                        .strafeToLinearHeading(new Vector2d(31.5, 1), Math.toRadians(-90))
                        .afterTime(0,()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
                        })

                        .build());




        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))
                        .afterTime(0, () -> {

                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);
                        })
                        .afterTime(1.5, ()->{ new Thread(()-> {
                            arms.pivot.setPosition(0.45);


                        }).start();})

                        .strafeToConstantHeading(new Vector2d(-10, 40) )




                        .build());
        //specimen 3
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))

                        .afterTime(0.5, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);


                        })

                        //.strafeToConstantHeading(new Vector2d(30, 15) )

                        .strafeToLinearHeading(new Vector2d(31.5, 1), Math.toRadians(-90))
                        .afterTime(0,()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
                        })
                        .build());


        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))
                        .afterTime(0, () -> {

                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);
                        })
                        .afterTime(1.5, ()->{ new Thread(()-> {
                            arms.pivot.setPosition(0.45);


                        }).start();})

                        .strafeToConstantHeading(new Vector2d(-10, 44) )




                        .build());


        //specimen 4

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))

                        .afterTime(0.5, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);


                        })

                        //.strafeToConstantHeading(new Vector2d(30, 15) )

                        .strafeToLinearHeading(new Vector2d(31.5, 1), Math.toRadians(-90))
                        .afterTime(0,()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
                        })

                        .build());


        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))
                        .afterTime(0, () -> {

                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);
                        })
                        .afterTime(1.5, ()->{ new Thread(()-> {
                            arms.pivot.setPosition(0.45);


                        }).start();})

                        .strafeToConstantHeading(new Vector2d(-10, 40) )




                        .build());


        //specimen 5

//        Actions.runBlocking(
//                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))
//
//                        .afterTime(0.6, ()->{
//                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
//                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
//                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
//                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
//                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
//                            arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);
//                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);
//
//
//                        })
//
//                        //.strafeToConstantHeading(new Vector2d(30, 15) )
//
//                        .strafeToLinearHeading(new Vector2d(30, 3), Math.toRadians(-90))
//                        .afterTime(0,()->{
//                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
//                        })
//
//                        .build());
//
//        Actions.runBlocking(
//                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))
//                        .afterTime(0, () -> {
//
//                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
//                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
//                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);
//                        })
//                        .afterTime(1.5, ()->{ new Thread(()-> {
//                            arms.pivot.setPosition(0.45);
//
//
//                        }).start();})
//
//                        .strafeToConstantHeading(new Vector2d(-13, 40) )
//
//
//
//
//                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))

                        .afterTime(0.65, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Extended);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);


                        })


                        .strafeToLinearHeading(new Vector2d(30, 5), Math.toRadians(0))
                        .afterTime(0,()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake );

                        })

                        .build());

    }
}