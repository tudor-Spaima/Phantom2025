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
import org.firstinspires.ftc.teamcode.robit.Arms;
import org.firstinspires.ftc.teamcode.robit.Extendo;
import org.firstinspires.ftc.teamcode.robit.GLOBALS;
import org.firstinspires.ftc.teamcode.robit.Lift;

import java.util.Arrays;


@Autonomous(name="autoSpecimen4")
public class autoSpecimenPuternic extends LinearOpMode {
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

        MecanumDrive drive = new MecanumDrive(hardwareMap, start, -0.5);

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
                        //preload
                        .afterTime(0, () -> {
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                        })
                        .afterTime(1.2, () -> {
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        })
                        .strafeToConstantHeading(new Vector2d(-30, 0), baseVelConstraint)

                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(-30, 0), Math.toRadians(0)))

                        //sample
                        .afterTime(1, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Specimen);

                        })
                        .splineToConstantHeading(new Vector2d(-10, 25) ,Math.toRadians(0), velPuternic,accelPuternic)


                        .strafeToLinearHeading(new Vector2d(-48, 37) ,Math.toRadians(0), velPuternic,accelPuternic)
                        .strafeToLinearHeading(new Vector2d(-8, 40), Math.toRadians(0), velPuternic,accelPuternic)


                        .strafeToConstantHeading(new Vector2d(-47, 40) , velPuternic,accelPuternic)
                        .strafeToLinearHeading(new Vector2d(-48, 50), Math.toRadians(0),velPuternic,accelPuternic)
                        .strafeToConstantHeading(new Vector2d(-8, 50) , velPuternic,accelPuternic)


                        .strafeToConstantHeading(new Vector2d(-47, 50) , velPuternic,accelPuternic)
                        .strafeToLinearHeading(new Vector2d(-47, 57), Math.toRadians(0))
                        .strafeToConstantHeading(new Vector2d(-8, 55),  velPuternic,accelPuternic)


                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(-8, 55), Math.toRadians(0)))
                        //colectare specimen 1
                        .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Specimen);

                        })
                        .splineToConstantHeading(new Vector2d(4, 55) ,Math.toRadians(0), baseVelConstraint,baseAccelConstraint)


                        //.strafeToLinearHeading(new Vector2d(3 ,40), Math.toRadians(0))
                        .afterTime(0,()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
                        })
                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(3, 35), Math.toRadians(0)))
                        //livrare specimen 1
                        .afterTime(0, () -> {
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);
                        })
                        //.strafeToLinearHeading(new Vector2d(-10, 5), Math.toRadians(0), baseVelConstraint,baseAccelConstraint)

                        .afterTime(1.9, () -> {
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        })

                        .splineToConstantHeading(new Vector2d(-29, -15) ,Math.toRadians(0), baseVelConstraint,baseAccelConstraint)


                        .build());


        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(-29, -15), Math.toRadians(0)))
                        //colectare specimen 3
                        .afterTime(1, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Specimen);

                        })
                        .splineToConstantHeading(new Vector2d(-10, 35) ,Math.toRadians(0), baseVelConstraint,baseAccelConstraint)


                        .strafeToLinearHeading(new Vector2d(3, 35), Math.toRadians(0))
                        .afterTime(0,()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
                        })
                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(3, 35), Math.toRadians(0)))
                        //livrare specimen 3
                        .afterTime(0, () -> {
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);
                        })
                        .afterTime(1.9, () -> {
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        })
                        .splineToConstantHeading(new Vector2d(-28, -15) ,Math.toRadians(0), baseVelConstraint,baseAccelConstraint)

                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(-32, -10), Math.toRadians(0)))
                        //colectare specimen 4
                        .afterTime(1, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Specimen);

                        })
                        .splineToConstantHeading(new Vector2d(-10, 30) ,Math.toRadians(0), baseVelConstraint,baseAccelConstraint)


                        .strafeToLinearHeading(new Vector2d(3, 35), Math.toRadians(0))
                        .afterTime(0,()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
                        })
                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(3, 35), Math.toRadians(0)))
                        //livrare specimen 4
                        .afterTime(0, () -> {
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);
                        })

                        .afterTime(1.9, () -> {
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        })
                        .splineToConstantHeading(new Vector2d(-28, -15) ,Math.toRadians(0), baseVelConstraint,baseAccelConstraint)


                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(-31, -15), Math.toRadians(0)))
                        //colectare specimen 4
                        .afterTime(1, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Specimen);

                        })
                        .splineToConstantHeading(new Vector2d(-10, 30) ,Math.toRadians(0), baseVelConstraint,baseAccelConstraint)


                        .strafeToLinearHeading(new Vector2d(3, 35), Math.toRadians(0))
                        .afterTime(0,()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
                        })
                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(3, 35), Math.toRadians(0)))
                        //livrare specimen 4
                        .afterTime(0, () -> {
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);
                        })

                        .afterTime(1.9, () -> {
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        })
                        .splineToConstantHeading(new Vector2d(-28, -15) ,Math.toRadians(0), baseVelConstraint,baseAccelConstraint)


                        .build());




        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(-31, -15), Math.toRadians(0)))
                        //parcare
                        .afterTime(1, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Init);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Retracted);


                        })
                        .strafeToLinearHeading(new Vector2d(-5, 30), Math.toRadians(45), baseVelConstraint,baseAccelConstraint)



                        .build());

        telemetry.addData("timp", timp.time());
        telemetry.update();
        sleep(200000);
    }
}
