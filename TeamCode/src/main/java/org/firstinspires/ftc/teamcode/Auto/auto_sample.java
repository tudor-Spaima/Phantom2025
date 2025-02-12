package org.firstinspires.ftc.teamcode.Auto;


import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
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

@Autonomous(name = "auto_sample")
public class auto_sample extends LinearOpMode {
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

        waitForStart();
        timp.startTime();

        Actions.runBlocking(
                drive.actionBuilder(start)
                        .afterTime(0.7, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Basket2);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Score);
                        })


                        .strafeToConstantHeading(new Vector2d(18, 34.1))
                        .strafeToConstantHeading(new Vector2d(15, 34.1))

                        .afterTime(0, ()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);

                        })

                        //ia primul sample
                        .strafeToConstantHeading(new Vector2d(12, 28))
                        .afterTime(0, ()->{
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Extended);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                            sleep(200);

                        })

                        //scoreaza primul sample
                        .afterTime(0.7, ()->{

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

                        })

                        //   .strafeToConstantHeading(new Vector2d(20, 35))
                        .strafeToLinearHeading(new Vector2d(20, 34.1), Math.toRadians(355))
                        .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Basket2);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Score);
                        })


                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(20, 34.1),Math.toRadians(355)))


                        .strafeToConstantHeading(new Vector2d(16 , 34.1))

                        .afterTime(0.4, ()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                        })
                        .build()
        );

        sleep(700);

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(16, 34.1),Math.toRadians(0)))

                        //pozitie colectare sample 2
                        .strafeToConstantHeading(new Vector2d(12.5, 18))

                        .afterTime(0, ()->{
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Extended);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                            sleep(200);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Colectare);
                            sleep(300);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Inchis);
                        })
                        .afterTime(0.3, ()->{

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

                        })


                        //pozitie scorare sample 2
                        .strafeToConstantHeading(new Vector2d(15, 28))
                        .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Basket2);
                        })

                        .afterTime(1, ()->{
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Score);
                        })

                        .afterTime(2.5, ()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            sleep(300);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);

                        })




                        .build());
        sleep(500);

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(15, 28),Math.toRadians(0)))
                        .afterTime(0, ()->{

                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                            arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lung);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Transfer);


                        })
                        // pozitie colectare sample 3
                        .strafeToLinearHeading(new Vector2d(37, 24), Math.toRadians(90))


                        .afterTime(0.1, ()->{
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Colectare);
                            sleep(300);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Inchis);
                        })
                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(37, 24),Math.toRadians(90)))
                        .afterTime(0.2, ()->{

                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Transfer);
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


                            sleep(200);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);

                            arms.updatePivotPosition(GLOBALS.pivot_positions.Score);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);

                        })
                        // pozitie scorare sample 3
                        .strafeToLinearHeading(new Vector2d(15, 15), Math.toRadians(0))
                        .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Basket2);
                        })

                        .afterTime(1, ()->{
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Score);
                        })

                        .afterTime(2.5, ()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            sleep(300);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);

                        })
                        .build());

        Actions.runBlocking(
       // colectare sample sumbersibil
                drive.actionBuilder(new Pose2d(new Vector2d(15, 15), Math.toRadians(0)))
        .afterTime(0, ()->{

            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
            arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lung);
            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Transfer);


        })

                .strafeToLinearHeading(new Vector2d(40, 15), Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(55, -8.5), Math.toRadians(270))


                .afterTime(0.1, ()->{
                    arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Colectare);
                    sleep(500);
                    arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Inchis);
                })
                .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(55, -8.5),Math.toRadians(270)))
                        .afterTime(0.2, ()->{

                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Transfer);
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


                            sleep(200);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);

                            arms.updatePivotPosition(GLOBALS.pivot_positions.Score);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);

                        })
                        // pozitie scorare sample 4
                        .strafeToLinearHeading(new Vector2d(17, 30), Math.toRadians(360))
                        .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Basket2);
                        })

                        .afterTime(1, ()->{
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Score);
                        })

                        .afterTime(2.5, ()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            sleep(300);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);

                        })
                        .build());







        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
        sleep(100);





        telemetry.addData("timp", timp.time());
        telemetry.update();
        sleep(200000);

    }
}
