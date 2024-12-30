package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.robit.Arms;
import org.firstinspires.ftc.teamcode.robit.Extendo;
import org.firstinspires.ftc.teamcode.robit.GLOBALS;
import org.firstinspires.ftc.teamcode.robit.Lift;

@Autonomous(name = "auto_specimen")
public class auto_specimen extends LinearOpMode {


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

        Pose2d start = new Pose2d(new Vector2d(0,0),Math.toRadians(0));
        MecanumDrive drive = new MecanumDrive(hardwareMap, start);
        //Initializare


        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(start)
                        //preload
                        .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                        })
                        .strafeToConstantHeading(new Vector2d(-30, 0))
                        .afterTime(0.1, ()->{
                           arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                           sleep(300);
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);

                        })

                        //colectare specimen 1
                        //.strafeToLinearHeading(new Vector2d(-2, 16), Math.toRadians(90))

                        // sample 1
                        .afterTime(0.1, ()->{
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Extended);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                            arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.mai_asea);

                        })
                        .strafeToLinearHeading(new Vector2d(-16, 30), Math.toRadians(135))
                        .afterTime(0,()->{
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Colectare);
                            sleep(400);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Inchis);
                            sleep(400);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                        })



                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d( new Vector2d(-16, 30), Math.toRadians(135)))
                        //livrare sample 1
                        .afterTime(0, ()->{
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Retracted);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                            arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lat);

                        })
                        .strafeToLinearHeading(new Vector2d(-6, 28), Math.toRadians(45))
                        .afterTime(0,()->{
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                        })



                        //sample 2
                        .afterTime(0.2, ()->{
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Extended);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                            arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.mai_asea);

                        })
                        .strafeToLinearHeading(new Vector2d(-17, 36.5), Math.toRadians(135))
                        .afterTime(0,()->{
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Colectare);
                            sleep(400);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Inchis);
                            sleep(400);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                        })

                        .build()

        );

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d( new Vector2d(-17 ,36.5), Math.toRadians(135)))
                        //livrare sample 2
                        .afterTime(0, ()->{
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Retracted);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                            arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lat);

                        })
                        .strafeToLinearHeading(new Vector2d(-6, 32), Math.toRadians(45))
                        .afterTime(0.1,()->{
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                        })



                        //sample 3
                        .afterTime(0.2, ()->{
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Extended);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                            arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.mai_asea);

                        })
                        .strafeToLinearHeading(new Vector2d(-17, 48.5), Math.toRadians(135))
                        .afterTime(0,()->{
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Colectare);
                            sleep(400);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Inchis);
                            sleep(400);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                        })

                        .build()

        );
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d( new Vector2d(-17, 48.5), Math.toRadians(135)))
                        //livrare sample 3
                        .afterTime(0, ()->{
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Retracted);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                            arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lat);

                        })

                        .strafeToLinearHeading(new Vector2d(-10, 55), Math.toRadians(0))
                        .afterTime(0.1,()->{
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                        })

                        //specimen1
                        .afterTime(0.1, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Specimen);

                        })
                        .strafeToLinearHeading(new Vector2d(-20, 43), Math.toRadians(180))

                        .strafeToLinearHeading(new Vector2d(-2, 43), Math.toRadians(180))
                        .afterTime(0,()->{

                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);

                        })

                        .build()

        );
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(-2, 43), Math.toRadians(180)))
                        .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);

                        })
                        .strafeToLinearHeading(new Vector2d(-32, -5), Math.toRadians(0))
                        .afterTime(0, ()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        })

                        .build()
        );
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(-32, -5), Math.toRadians(0)))
                        //specimen 2
                        .afterTime(0.4, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Specimen);

                        })

                        .strafeToLinearHeading(new Vector2d(-10, 43), Math.toRadians(180))
                        .strafeToLinearHeading(new Vector2d(-2, 43), Math.toRadians(180))


                        .afterTime(0,()->{

                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);

                        })

                        .build()
        );

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(-2, 43), Math.toRadians(180)))
                        .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);

                        })
                        .strafeToLinearHeading(new Vector2d(-30, -5), Math.toRadians(0))
                        .afterTime(0.1, ()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        })

                        .build()
        );

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(-30, -5), Math.toRadians(0)))
                        //specimen 3
                        .afterTime(0.4, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Specimen);

                        })

                        .strafeToLinearHeading(new Vector2d(-10, 43), Math.toRadians(180))
                        .strafeToLinearHeading(new Vector2d(0, 43), Math.toRadians(180))


                        .afterTime(0,()->{

                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);

                        })

                        .build()
        );

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(-1, 43), Math.toRadians(180)))
                        .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);

                        })
                        .strafeToLinearHeading(new Vector2d(-29, -5), Math.toRadians(0))
                        .afterTime(0, ()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        })

                        .build()
        );

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(-29, -5), Math.toRadians(0)))
                        //specimen 3
                        .afterTime(0.4, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Specimen);

                        })

                        .strafeToLinearHeading(new Vector2d(-10, 43), Math.toRadians(180))
                        .strafeToLinearHeading(new Vector2d(0, 43), Math.toRadians(180))


                        .afterTime(0,()->{

                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);

                        })

                        .build()
        );

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(0, 43), Math.toRadians(180)))
                        .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);

                        })
                        .strafeToLinearHeading(new Vector2d(-28, -5), Math.toRadians(0))
                        .afterTime(0, ()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        })

                        .build()
        );

        sleep(10000);

    }
}
