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


@Autonomous(name="autoSpecimen")
public class autoSpecimen extends LinearOpMode {
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
        arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
        arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);

        Pose2d start = new Pose2d(new Vector2d(0, 0), Math.toRadians(0));
        MecanumDrive drive = new MecanumDrive(hardwareMap, start);

        ElapsedTime timp = new ElapsedTime();

        VelConstraint baseVelConstraint = new MinVelConstraint(Arrays.asList(
                new TranslationalVelConstraint(140),
                new AngularVelConstraint(Math.PI / 2)
        ));
        AccelConstraint baseAccelConstraint = new ProfileAccelConstraint(-40, 80);

        VelConstraint velPuternic = new MinVelConstraint(Arrays.asList(
                new TranslationalVelConstraint(120),
                new AngularVelConstraint(Math.PI / 2)
        ));
        AccelConstraint accelPuternic = new ProfileAccelConstraint(-50, 95);


        //Initializare


        waitForStart();
        timp.startTime();

        Actions.runBlocking(
                drive.actionBuilder(start)
                        //preload
                        .afterTime(0, () -> {
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                        })
                        .strafeToConstantHeading(new Vector2d(-30, 0), baseVelConstraint)
                        .afterTime(0, () -> {
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        })
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
                        .strafeToLinearHeading(new Vector2d(-10, 25), Math.toRadians(0), velPuternic,accelPuternic)


                        .strafeToLinearHeading(new Vector2d(-50, 37), Math.toRadians(0), velPuternic,accelPuternic)
                        .strafeToLinearHeading(new Vector2d(-8, 50), Math.toRadians(0), velPuternic,accelPuternic)

                        .strafeToLinearHeading(new Vector2d(-50, 40), Math.toRadians(0), velPuternic,accelPuternic)
                        .strafeToLinearHeading(new Vector2d(-8, 53), Math.toRadians(0), velPuternic,accelPuternic)

                        .strafeToLinearHeading(new Vector2d(-50, 52), Math.toRadians(0), velPuternic,accelPuternic)
                        .strafeToLinearHeading(new Vector2d(-8, 52), Math.toRadians(0), velPuternic,accelPuternic)


                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(-8, 52), Math.toRadians(0)))
                        //colectare specimen 1
                        .afterTime(1, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Specimen);

                        })
                        .strafeToLinearHeading(new Vector2d(-10, 40), Math.toRadians(0), baseVelConstraint,baseAccelConstraint)

                        .strafeToLinearHeading(new Vector2d(5, 40), Math.toRadians(0))
                        .afterTime(0,()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
                        })
                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(1, 40), Math.toRadians(0)))
                        //livrare specimen 1
                        .afterTime(0, () -> {
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);
                        })
                        .strafeToLinearHeading(new Vector2d(-10, 5), Math.toRadians(0), baseVelConstraint,baseAccelConstraint)



                        .strafeToConstantHeading(new Vector2d(-30, -3), baseVelConstraint,baseAccelConstraint)
                        .afterTime(0, () -> {
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        })
                        .build());


        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(-30, -3), Math.toRadians(0)))
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
                        .strafeToLinearHeading(new Vector2d(-10, 20), Math.toRadians(0), baseVelConstraint,baseAccelConstraint)

                        .strafeToLinearHeading(new Vector2d(7, 40), Math.toRadians(0))
                        .afterTime(0,()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
                        })
                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(1, 40), Math.toRadians(0)))
                        //livrare specimen 3
                        .afterTime(0, () -> {
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);
                        })
                        .strafeToLinearHeading(new Vector2d(-10, 5), Math.toRadians(0), baseVelConstraint,baseAccelConstraint)



                        .strafeToConstantHeading(new Vector2d(-31,-5 ), baseVelConstraint,baseAccelConstraint)
                        .afterTime(0, () -> {
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        })
                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(-31, -5), Math.toRadians(0)))
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
                        .strafeToLinearHeading(new Vector2d(-10, 20), Math.toRadians(0), baseVelConstraint,baseAccelConstraint)

                        .strafeToLinearHeading(new Vector2d(7, 40), Math.toRadians(0))
                        .afterTime(0,()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
                        })
                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(1, 40), Math.toRadians(0)))
                        //livrare specimen 4
                        .afterTime(0, () -> {
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);
                        })
                        .strafeToLinearHeading(new Vector2d(-10, 5), Math.toRadians(0), baseVelConstraint,baseAccelConstraint)



                        .strafeToConstantHeading(new Vector2d(-32, -7), baseVelConstraint,baseAccelConstraint)
                        .afterTime(0, () -> {
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        })
                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(-32, -7), Math.toRadians(0)))
                        //colectare specimen 5
                        .afterTime(1, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Specimen);

                        })
                        .strafeToLinearHeading(new Vector2d(-10, 20), Math.toRadians(0), baseVelConstraint,baseAccelConstraint)

                        .strafeToLinearHeading(new Vector2d(7, 40), Math.toRadians(0))
                        .afterTime(0,()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
                        })
                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(1, 40), Math.toRadians(0)))
                        //livrare specimen 5
                        .afterTime(0, () -> {
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);
                        })
                        .strafeToLinearHeading(new Vector2d(-10, 5), Math.toRadians(0), baseVelConstraint,baseAccelConstraint)



                        .strafeToConstantHeading(new Vector2d(-32, -8), baseVelConstraint,baseAccelConstraint)
                        .afterTime(0, () -> {
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        })
                        .build());


        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(-32, -8), Math.toRadians(0)))
                        //colectare specimen 5
                        .afterTime(1, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Init);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Extended);


                        })
                        .strafeToLinearHeading(new Vector2d(-8, 25), Math.toRadians(45), baseVelConstraint,baseAccelConstraint)


                        .build());

        telemetry.addData("timp", timp.time());
        telemetry.update();
        sleep(200000);
    }
}
