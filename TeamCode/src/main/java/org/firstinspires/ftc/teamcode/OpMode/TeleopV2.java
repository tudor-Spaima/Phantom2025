package org.firstinspires.ftc.teamcode.OpMode;

import static org.firstinspires.ftc.teamcode.OpMode.Teleop.teleopStates.*;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.outoftheboxrobotics.photoncore.PhotonCore;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.CommandBase.Commands;
import org.firstinspires.ftc.teamcode.robit.GLOBALS;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.robit.Arms;
import org.firstinspires.ftc.teamcode.robit.Extendo;
import org.firstinspires.ftc.teamcode.robit.Lift;
import org.firstinspires.ftc.teamcode.robit.Senzori;

import java.util.Objects;


@TeleOp(name = "TeleopV2", group = "Teleop")
public class TeleopV2 extends LinearOpMode {
    enum teleopStates {
        Intake, Score, Specimen, SpecimenTEAVA, Init
    }
    enum autoIntake {
        on, off
    }


    @Override
    public void runOpMode( ) throws InterruptedException {

        TeleopV2.teleopStates currentState = TeleopV2.teleopStates.Init;
        autoIntake autoIntake = TeleopV2.autoIntake.on;


        MecanumDrive drive = new MecanumDrive( hardwareMap, new Pose2d( 0, 0, 0 ) );
        Lift lift = new Lift( hardwareMap );
        Extendo extendo = new Extendo( hardwareMap );
        Arms arms = new Arms( hardwareMap );
        Senzori senzori = new Senzori( hardwareMap );
        Commands commands = new Commands(hardwareMap);


        PhotonCore photonCore = new PhotonCore();
        PhotonCore.ExperimentalParameters ph = new PhotonCore.ExperimentalParameters();
        ph.setMaximumParallelCommands( 8 );
        ph.setSinglethreadedOptimized( false );

        //INITIALIZARE
        lift.updateLiftPosition( GLOBALS.LiftPositions.Jos );
        extendo.updateExtendoPosition( GLOBALS.ExtendoPositions.Init );
        arms.updateBratIntakePosition( GLOBALS.brat_intake_positions.Init );
        arms.updateBratScorePosition( GLOBALS.brat_score_positions.Init );
        arms.updatePivotPosition( GLOBALS.pivot_positions.Safe );
        arms.updateGripperIntakePosition( GLOBALS.grippers_positions.Deschis );
        arms.updateGripperScorePosition( GLOBALS.grippers_positions.Deschis );
        arms.updateRotireGripperPosition( GLOBALS.rotire_gripper_positions.pe_lat );

        DriveController driveController = new DriveController( telemetry, gamepad1, drive, extendo, senzori, hardwareMap );
        Thread driveControllerThread = new Thread( driveController );

        if (driveControllerThread.isAlive()) {
            driveController.stopThread();
            driveControllerThread.interrupt();
            driveControllerThread.join();
        }



        waitForStart();
        driveControllerThread.start();

        while (opModeIsActive()) {

            switch (currentState) {

                case Init:
                    if (gamepad2.triangle) {
                        commands.intakePositions();
                        sleep( 200 );
                        currentState = TeleopV2.teleopStates.Intake;
                    }

                    break;

                case Intake:

                    //colectare
                    if (gamepad1.square) {
                        arms.updateGripperIntakePosition( GLOBALS.grippers_positions.Inchis );
                        sleep( 200 );

                        switch (autoIntake) {
                            case on:
                                if (senzori.hasSample()) {
                                    commands.transfer();

                                    if(senzori.hasSampleScore()) {
                                        currentState = TeleopV2.teleopStates.Score;
                                    }else{
                                        commands.intakePositions();
                                    }

                                }else{
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

                    if (gamepad2.cross) {
                        commands.transfer();
                        currentState = TeleopV2.teleopStates.Score;
                    }

                    if (gamepad2.square) {
                        commands.specimenIntakePositions();
                        currentState = TeleopV2.teleopStates.Specimen;
                        sleep( 200 );
                    }
                    break;


                case Score:
                    arms.updateBratIntakePosition( GLOBALS.brat_intake_positions.Init );
                    if (gamepad1.circle) {
                        arms.updateGripperScorePosition( GLOBALS.grippers_positions.Deschis );
                        sleep( 500 );
                        commands.intakePositions();
                        sleep( 200 );
                        currentState = TeleopV2.teleopStates.Intake;
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
                                currentState = teleopStates.SpecimenTEAVA;
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

                    if (gamepad2.triangle) {
                        arms.updateGripperScorePosition( GLOBALS.grippers_positions.Deschis );
                        sleep( 1000 );
                        lift.updateLiftPosition( GLOBALS.LiftPositions.Jos );
                        extendo.updateExtendoPosition( GLOBALS.ExtendoPositions.Init );
                        arms.updateBratIntakePosition( GLOBALS.brat_intake_positions.Intake );
                        arms.updateBratScorePosition( GLOBALS.brat_score_positions.Safe );
                        arms.updateGripperIntakePosition( GLOBALS.grippers_positions.Deschis );
                        arms.updateGripperScorePosition( GLOBALS.grippers_positions.Deschis );

                        sleep( 200 );
                        currentState = TeleopV2.teleopStates.Intake;
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
                        currentState = TeleopV2.teleopStates.Specimen;
                    }
                    break;
            }

            if (gamepad2.left_stick_y != 0) {
                lift.manualControl( gamepad2.left_stick_y, 60 );
                sleep( 200 );
            }
            lift.manualEncodersReset( gamepad2.touchpad );

            if (gamepad2.right_bumper) {
                lift.goToPos( 1000, 1, lift.CulisantaDreapta ); //1900
                lift.goToPos( 1000, 1, lift.CulisantaStanga );
            }
            if (gamepad2.left_bumper) {
                lift.goToPos( 0, 1, lift.CulisantaDreapta ); //1000
                lift.goToPos( 0, 1, lift.CulisantaStanga );
            }

            if (gamepad2.dpad_down) {
                if (autoIntake == TeleopV2.autoIntake.on) {
                    autoIntake = TeleopV2.autoIntake.off;

                } else {
                    autoIntake =TeleopV2.autoIntake.on;
                }
                sleep( 200 );
            }



            /*
                telemetry.addData("Runtime", getRuntime());
                telemetry.addData("Drive Thread Alive", driveControllerThread.isAlive());
                telemetry.addData("Current State", currentState);
                telemetry.addData("Distanta (CM) ", senzori.getDistance());
                telemetry.addData("Are sample in gura ", senzori.hasSample());
                telemetry.addData("Sample in range ", senzori.sampleInRange());
                telemetry.addData("Automatizare Intake", automatizareInakte);
                telemetry.addData("culisanta stanga", lift.CulisantaStanga.getCurrentPosition());
                telemetry.addData("culisanta dreapta", lift.CulisantaDreapta.getCurrentPosition());
             */

///////////....//////


            String detectedColor = senzori.detectColor( senzori.senzorIntakeCuloare.red(), senzori.senzorIntakeCuloare.green(), senzori.senzorIntakeCuloare.blue() );
            telemetry.addData( "Automatizare Intake", autoIntake );
            telemetry.addData( "Are sample in gura ", senzori.hasSample() );
            telemetry.addData( "Are sample in cioc ", senzori.hasSampleScore() );
            telemetry.addData( "Current State", currentState );
            telemetry.addData( "Detected Color", detectedColor );
            telemetry.addData("DistantaScore (CM) ", senzori.getDistanceScore());
            telemetry.addData("DistantaIntake (CM) ", senzori.getDistance());

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


