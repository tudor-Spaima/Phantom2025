package org.firstinspires.ftc.teamcode.OpMode;

import static org.firstinspires.ftc.teamcode.OpMode.TeleopSOLO.teleopStates.SpecimenTEAVA;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.outoftheboxrobotics.photoncore.PhotonCore;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.CommandBase.Commands;
import org.firstinspires.ftc.teamcode.PinpointDrive;
import org.firstinspires.ftc.teamcode.robit.GLOBALS;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.robit.Arms;
import org.firstinspires.ftc.teamcode.robit.Extendo;
import org.firstinspires.ftc.teamcode.robit.Lift;
import org.firstinspires.ftc.teamcode.robit.Senzori;


@TeleOp(name = "TeleopSOLO", group = "Teleop")
public class TeleopSOLO extends LinearOpMode {
    enum teleopStates {Intake, Score, Specimen, SpecimenTEAVA, Init}
    enum autoIntake {on, off}
    enum feedMode {on, off}


    @Override
    public void runOpMode( ) throws InterruptedException {

        teleopStates currentState = teleopStates.Intake;
        autoIntake autoIntake = TeleopSOLO.autoIntake.on;


        Lift lift = new Lift( hardwareMap );
        Extendo extendo = new Extendo( hardwareMap );
        Arms arms = new Arms( hardwareMap );
        Senzori senzori = new Senzori( hardwareMap );
        Commands commands = new Commands(hardwareMap);
        GLOBALS.LiftPositions scorePos = GLOBALS.LiftPositions.Basket2;
        PinpointDrive pp = new PinpointDrive(hardwareMap, new Pose2d(new Vector2d(0, 0), Math.toRadians(0)));


        PhotonCore photonCore = new PhotonCore();
        PhotonCore.ExperimentalParameters ph = new PhotonCore.ExperimentalParameters();
        ph.setMaximumParallelCommands( 8 );
        ph.setSinglethreadedOptimized( false );

        commands.initPositions();

        Teleop.DriveController driveController = new Teleop.DriveController( telemetry, gamepad1, pp, extendo, senzori, hardwareMap );
        Thread driveControllerThread = new Thread( driveController );

        if (driveControllerThread.isAlive()) {
            driveController.stopThread();
            driveControllerThread.interrupt();
            driveControllerThread.join();
        }



        waitForStart();
        commands.intakePositions();
        driveControllerThread.start();

        while (opModeIsActive()) {

            switch (currentState) {

                case Init:
                    if (gamepad2.triangle) {
                        commands.intakePositions();
                        sleep( 200 );
                        currentState = TeleopSOLO.teleopStates.Intake;
                    }
                    break;

                case Intake:

                    if (gamepad1.square) {
                        arms.updateGripperIntakePosition( GLOBALS.grippers_positions.Inchis );
                        sleep( 200 );

                        switch (autoIntake) {
                            case on:
                                if (senzori.hasSample()) {
                                    commands.transfer();
                                    lift.updateLiftPosition(scorePos);
                                    currentState = TeleopSOLO.teleopStates.Score;
/*                                    if(senzori.hasSampleScore()) {
                                        currentState = TeleopSOLO.teleopStates.Score;
                                    }else{
                                        commands.intakePositions();
                                    }
*/                                }else{
                                    arms.updateGripperIntakePosition( GLOBALS.grippers_positions.Deschis );
                                }
                                break;
                            case off:
                                break;
                        }
                    }
                    if (gamepad1.circle) {
                        arms.updateGripperIntakePosition( GLOBALS.grippers_positions.Deschis );
                        sleep( 200 );
                    }

                    if (gamepad1.right_trigger > 0.1) {
                        arms.updateBratIntakePosition( GLOBALS.brat_intake_positions.Colectare );
                    } else {
                        arms.updateBratIntakePosition( GLOBALS.brat_intake_positions.Intake );
                    }

                    if (gamepad1.right_bumper) {
                        if (extendo.extendo.getPosition() == GLOBALS.Retracted) {
                            extendo.updateExtendoPosition( GLOBALS.ExtendoPositions.Extended );
                            sleep( 200 );
                        } else {
                            extendo.updateExtendoPosition( GLOBALS.ExtendoPositions.Retracted );
                            sleep( 200 );
                        }
                    }

                    if (gamepad1.left_trigger != 0) {
                        arms.updateRotireGripperPosition( GLOBALS.rotire_gripper_positions.pe_lung );
                    } else {
                        arms.updateRotireGripperPosition( GLOBALS.rotire_gripper_positions.pe_lat );
                    }

                    if (gamepad1.cross) {
                        commands.transfer();
                        lift.updateLiftPosition(scorePos);
                        currentState = TeleopSOLO.teleopStates.Score;
                    }
                    if (gamepad1.dpad_right) {
                        commands.specimenIntakePositions();
                        currentState = TeleopSOLO.teleopStates.Specimen;
                        sleep( 200 );
                    }
                    if(gamepad1.left_bumper){
                        if( scorePos== GLOBALS.LiftPositions.Basket2){
                            scorePos= GLOBALS.LiftPositions.Basket1;
                            sleep(200);
                        }else{
                            scorePos= GLOBALS.LiftPositions.Basket2;
                            sleep(200);
                        }
                    }

                    break;


                case Score:
                    arms.updateBratIntakePosition( GLOBALS.brat_intake_positions.Init );
                    if (gamepad1.circle) {
                        arms.updateGripperScorePosition( GLOBALS.grippers_positions.Deschis );
                        sleep( 200 );
                        arms.updatePivotPosition( GLOBALS.pivot_positions.Score );
                        sleep( 500 );
                        commands.intakePositions();
                        sleep( 200 );
                        currentState = TeleopSOLO.teleopStates.Intake;
                    }
                    if (gamepad2.dpad_up) {
                        lift.updateLiftPosition( GLOBALS.LiftPositions.Basket2 );
                        sleep( 200 );
                    }
                    if (gamepad2.dpad_right) {
                        lift.updateLiftPosition( GLOBALS.LiftPositions.Basket1 );
                        sleep( 200 );
                    }
                    if (gamepad1.right_trigger > 0.1) {
                        arms.updatePivotPosition( GLOBALS.pivot_positions.Infipt);
                    } else {
                        arms.updatePivotPosition( GLOBALS.pivot_positions.Score );
                    }
                    break;


                case Specimen:

                    switch (autoIntake) {
                        case on:
                            if (senzori.hasSampleScore()) {
                                arms.updateGripperScorePosition( GLOBALS.grippers_positions.Inchis );
                                sleep(300);
                                commands.specimenScorePositions();
                                pp = new PinpointDrive(hardwareMap, new Pose2d(new Vector2d(0, 0), Math.toRadians(0)));
                                currentState = SpecimenTEAVA;
                            }
                        case off:
                            if (gamepad1.square) {
                                arms.updateGripperScorePosition( GLOBALS.grippers_positions.Inchis );
                                sleep( 200 );
                            }
                            if (gamepad1.circle) {
                                arms.updateGripperScorePosition( GLOBALS.grippers_positions.Deschis );
                                sleep( 200 );
                            }
                            break;
                    }

                    if(gamepad1.left_bumper) {
                        driveController.stopThread();
                        sleep(200);
                        pp.updatePoseEstimate();
                        TrajectoryActionBuilder finish_spec_new = pp.actionBuilder(pp.pose)
                                .strafeToLinearHeading(new Vector2d(-10, 0), Math.toRadians(0));
                        Actions.runBlocking(
                                new SequentialAction(
                                        finish_spec_new.build()
                                ));
                        sleep(200);
                        driveController.continueThread();

                    }


                    if (gamepad1.dpad_left) {
                        arms.updateGripperScorePosition( GLOBALS.grippers_positions.Deschis );
                        sleep( 1000 );
                        lift.updateLiftPosition( GLOBALS.LiftPositions.Jos );
                        extendo.updateExtendoPosition( GLOBALS.ExtendoPositions.Init );
                        arms.updateBratIntakePosition( GLOBALS.brat_intake_positions.Intake );
                        arms.updateBratScorePosition( GLOBALS.brat_score_positions.Safe );
                        arms.updateGripperIntakePosition( GLOBALS.grippers_positions.Deschis );
                        arms.updateGripperScorePosition( GLOBALS.grippers_positions.Deschis );
                        sleep( 200 );
                        currentState = TeleopSOLO.teleopStates.Intake;
                    }
                break;

                case SpecimenTEAVA: //adica scorare

                    if (gamepad1.circle) {
                        arms.pivot.setPosition(0.45);
                        sleep( 1000 );
                        arms.updateGripperScorePosition( GLOBALS.grippers_positions.Deschis );
                        sleep(200);
                        commands.specimenIntakePositions();
                        sleep( 200 );
                        currentState = TeleopSOLO.teleopStates.Specimen;
                    }
                    break;
            }


            if (gamepad2.left_stick_y != 0) {
                lift.manualControl( gamepad2.left_stick_y, 60 );
                sleep( 200 );
            }
            lift.manualEncodersReset( gamepad2.touchpad );


            if (gamepad1.dpad_down) {
                if (autoIntake == TeleopSOLO.autoIntake.on) {
                    autoIntake = TeleopSOLO.autoIntake.off;
                } else {
                    autoIntake =TeleopSOLO.autoIntake.on;
                }
                sleep( 200 );
            }

            if(gamepad1.touchpad){
                sleep(200);
                pp = new PinpointDrive(hardwareMap, new Pose2d(new Vector2d(0, 0), Math.toRadians(0)));

            }


            String detectedColor = senzori.detectColor( senzori.senzorIntakeCuloare.red(), senzori.senzorIntakeCuloare.green(), senzori.senzorIntakeCuloare.blue() );
            telemetry.addData( "Automatizare Intake", autoIntake );
            telemetry.addData( "Are sample in gura ", senzori.hasSample() );
            telemetry.addData( "Are sample in cioc ", senzori.hasSampleScore() );
            telemetry.addData( "Current State", currentState );
            telemetry.addData( "Detected Color", detectedColor );
            telemetry.addData("DistantaScore (CM) ", senzori.getDistanceScore());
            telemetry.addData("DistantaIntake (CM) ", senzori.getDistance());
            telemetry.addLine();
            telemetry.addData("BASKET: ",scorePos );
            telemetry.update();



        }

        driveController.stopThread();
        driveControllerThread.interrupt();
        try {
            driveControllerThread.join(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}


