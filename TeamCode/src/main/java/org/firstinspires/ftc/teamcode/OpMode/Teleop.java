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
import org.firstinspires.ftc.teamcode.robit.GLOBALS;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.robit.Arms;
import org.firstinspires.ftc.teamcode.robit.Extendo;
import org.firstinspires.ftc.teamcode.robit.Lift;


class DriveController implements Runnable {
    Telemetry telemetry;
    MecanumDrive drive ;
    Gamepad gp1;
    Extendo extendo;
    HardwareMap hm;
    volatile double driveSpeedMultiplier ;
    public DriveController(Telemetry telemetry, Gamepad gp1, MecanumDrive drive, Extendo extendo, HardwareMap hm) {
        this.telemetry = telemetry;
        this.gp1 = gp1;
        this.drive = drive;
        this.extendo = extendo;
        this.hm = hm;
    }


    @Override
    public void run() {
        extendo = new Extendo(this.hm);
        while (true) {
            double speedMultiplier = extendo.isExtendoExtended() ? 0.5 : 1.0;

            drive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(
                            -gp1.left_stick_y * speedMultiplier,
                            -gp1.left_stick_x * speedMultiplier
                    ),
                    -gp1.right_stick_x * speedMultiplier
            ));


        }
    }
}
@TeleOp(name = "Teleop", group = "Teleop")
public class Teleop extends LinearOpMode {
     enum teleopStates{
        Intake, Score, Specimen, SpecimenTEAVA ,Init
    }

    @Override
    public void runOpMode() throws InterruptedException {

        teleopStates currentState = Init;

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Lift lift = new Lift(hardwareMap);
        Extendo extendo = new Extendo(hardwareMap);
        Arms arms = new Arms(hardwareMap);

        DriveController driveController = new DriveController(telemetry, gamepad1, drive, extendo, hardwareMap);
        Thread driveControllerThread = new Thread(driveController);


        PhotonCore photonCore = new PhotonCore();
        PhotonCore.ExperimentalParameters ph = new PhotonCore.ExperimentalParameters();
        ph.setMaximumParallelCommands(8);
        ph.setSinglethreadedOptimized(false);

        //INITIALIZARE
        lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Init);
        arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);
        arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
        arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lat);



        waitForStart();

        driveControllerThread.start();
        while (opModeIsActive()) {


            switch (currentState){

                case Init:
                    if(gamepad2.triangle){
                        lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);
                        arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);

                        sleep(200);
                        currentState = Intake;
                    }

                    break;
                case Intake:

                    //colectare
                    if(gamepad1.square) {
                        arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Inchis);
                        sleep(200);
                    }
                    if (gamepad1.circle){
                        arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                        sleep(200);
                    }

                    if(gamepad1.right_trigger > 0.1){
                        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Colectare);
                    }else{
                        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                    }

                    //extendo
                    if(gamepad1.right_bumper) {
                        if(extendo.extendo.getPosition() == GLOBALS.Retracted) {
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Extended);
                            sleep(200);
                        }
                        else{
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Retracted);
                            sleep(200);

                        }
                    }


                    if(gamepad1.left_trigger != 0){
                            arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lung);
                        }
                        else{
                            arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lat);
                        }


                    //transfer
                    if(gamepad2.cross){

                        lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Transfer);
                        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);
                        arms.updatePivotPosition(GLOBALS.pivot_positions.Transfer);
                        arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lat);

                        sleep(300);
                        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Transfer);

                        sleep(300);

                        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Transfer);
                        sleep(400);
                        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
                        sleep(100);
                        arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Score);

                        sleep(200);
                        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                        arms.updatePivotPosition(GLOBALS.pivot_positions.Score);
                        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);

                        currentState = Score;
                    }

                    if(gamepad2.square){
                        lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);

                        arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        arms.updatePivotPosition(GLOBALS.pivot_positions.Specimen);
                        sleep(500);
                        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);
                        currentState = Specimen;
                        sleep(200);
                    }
                    break;


                case Score:
                    //scoring
                    if(gamepad1.circle) {
                        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        sleep(500);

                        lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);
                        arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);

                        sleep(200);
                        currentState = Intake;
                    }

                    if(gamepad2.dpad_up){
                        lift.updateLiftPosition(GLOBALS.LiftPositions.Basket2);
                        sleep(200);
                    }
                    if(gamepad2.dpad_right){
                        lift.updateLiftPosition(GLOBALS.LiftPositions.Basket1);
                        sleep(200);
                    }
                    break;


                case Specimen:

                    if(gamepad1.square) {
                        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
                        sleep(200);
                    }
                    if (gamepad1.circle){
                        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        sleep(200);
                    }
                    if(gamepad2.cross){
                        lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
                        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                        arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
                        arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);
                        currentState = SpecimenTEAVA;
                    }

                    if (gamepad2.triangle){
                        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        sleep(1000);
                        lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);
                        arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);

                        sleep(200);
                        currentState = Intake;
                    }

                    break;


                case SpecimenTEAVA: //adica scorare

                    if (gamepad1.circle){
                        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        sleep(1000);
                        lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                        arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        arms.updatePivotPosition(GLOBALS.pivot_positions.Specimen);
                        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);

                        sleep(200);
                        currentState = Specimen;
                    }
                    break;
            }


/*
            if(gamepad2.left_stick_x>1 || gamepad2.left_stick_x<-1){
                lift.manualControl(gamepad2.left_stick_y, 75);
            }
*/
            lift.manualEncodersReset(gamepad2.dpad_down);

            if(gamepad2.left_bumper){
                lift.goToPos(1950, 1, lift.CulisantaDreapta);
                lift.goToPos(1950, 1, lift.CulisantaStanga);
            }
            if(gamepad2.right_bumper){
                lift.goToPos(1000, 1, lift.CulisantaDreapta);
                lift.goToPos(1000, 1, lift.CulisantaStanga);
            }



            telemetry.addData("Runtime", getRuntime());
            telemetry.addData("Drive Thread Alive", driveControllerThread.isAlive());
            telemetry.addData("Current State", currentState);
            telemetry.update();

            }

        driveControllerThread.interrupt();

    }

}
