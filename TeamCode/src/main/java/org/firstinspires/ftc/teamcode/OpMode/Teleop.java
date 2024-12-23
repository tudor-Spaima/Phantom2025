package org.firstinspires.ftc.teamcode.OpMode;

import static org.firstinspires.ftc.teamcode.OpMode.Teleop.teleopStates.*;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.outoftheboxrobotics.photoncore.PhotonCore;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

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
    volatile double driveSpeedMultiplier = 1.0;
    public DriveController(Telemetry telemetry, Gamepad gp1, MecanumDrive drive) {
        this.telemetry = telemetry;
        this.gp1 = gp1;
        this.drive = drive;
    }

    public void updateDriveSpeedMultiplier(double multiplier) {
        this.driveSpeedMultiplier = multiplier;
    }

    @Override
    public void run() {
        while (true) {
            drive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(
                            -gp1.left_stick_y * driveSpeedMultiplier,
                            -gp1.left_stick_x * driveSpeedMultiplier
                    ),
                    -gp1.right_stick_x * driveSpeedMultiplier
            ));
            drive.updatePoseEstimate();
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
         double DRIVE_INDEX;

        teleopStates currentState = Init;

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Lift lift = new Lift(hardwareMap);
        Extendo extendo = new Extendo(hardwareMap);
        Arms arms = new Arms(hardwareMap);

        //GLOBALS globals = new GLOBALS();


//        DriveController driveController = new DriveController(telemetry, gamepad1, drive);
//        Thread driveControllerThread = new Thread(new DriveController(telemetry,  gamepad1, drive));

        DriveController driveController = new DriveController(telemetry, gamepad1, drive);
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

                        //TODO optimizare timp de transfer
                        lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Transfer);
                        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);
                        arms.updatePivotPosition(GLOBALS.pivot_positions.Transfer);
                        arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lat);


                        sleep(600);
                        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Transfer);
                        sleep(400);
                        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
                        sleep(100);
                        arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                        sleep(200);
                        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Score);
                        arms.updatePivotPosition(GLOBALS.pivot_positions.Score);
                        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);

                        currentState = Score;
                    }

                    if(gamepad2.square){


                        lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Specimen);
                        arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        arms.updatePivotPosition(GLOBALS.pivot_positions.Specimen);
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

                    break;
                case SpecimenTEAVA: //adica scorare
                    if (gamepad1.circle){
                        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                        sleep(1000);


                        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);
                        arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);


                        sleep(200);
                        currentState = Intake;
                    }



            }

            if (extendo.isExtendoExtended()) {DRIVE_INDEX = 0.5;} else {
                DRIVE_INDEX = 1.0;
            }


            driveController.updateDriveSpeedMultiplier(DRIVE_INDEX);

            telemetry.addData("Runtime", getRuntime());
            telemetry.addData("Drive Thread Alive", driveControllerThread.isAlive());
            telemetry.addData("Current State", currentState);
            telemetry.update();

            }

        driveControllerThread.interrupt();



    }

}
