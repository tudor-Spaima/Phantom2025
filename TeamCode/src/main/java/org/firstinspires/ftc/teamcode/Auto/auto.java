package org.firstinspires.ftc.teamcode.Auto;


import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.outoftheboxrobotics.photoncore.PhotonCore;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.OpMode.Teleop;
import org.firstinspires.ftc.teamcode.robit.Arms;
import org.firstinspires.ftc.teamcode.robit.Extendo;
import org.firstinspires.ftc.teamcode.robit.GLOBALS;
import org.firstinspires.ftc.teamcode.robit.Lift;

@Autonomous(name = "auto")
public class auto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {


        Lift lift = new Lift(hardwareMap);
        Extendo extendo = new Extendo(hardwareMap);
        Arms arms = new Arms(hardwareMap);




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
        MecanumDrive drive = new MecanumDrive(hardwareMap, start);

        waitForStart();


        Actions.runBlocking(
                drive.actionBuilder(start)
                        .afterTime(0.7, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Basket2);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Score);
                        })

                        .strafeToConstantHeading(new Vector2d(16, 34.1))
                        .afterTime(0, ()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);

                        })


                        .strafeToConstantHeading(new Vector2d(14, 29))
                        .afterTime(0, ()->{
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Extended);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                            sleep(200);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Colectare);
                            sleep(400);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Inchis);
                        })
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


                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(14, 28),Math.toRadians(0)))
                        .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Basket2);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Score);
                        })
                        .afterTime(1, ()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);

                        })
                        .strafeToConstantHeading(new Vector2d(16, 34.1))

                        .strafeToConstantHeading(new Vector2d(14, 18))

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

                        .strafeToConstantHeading(new Vector2d(14.5, 28))
                        .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Basket2);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Score);
                        })
                        .afterTime(2, ()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                            sleep(300);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);

                        })




                        .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(15, 28),Math.toRadians(0)))
                        .afterTime(0, ()->{

                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                            arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lung);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Transfer);


                        })
                        .strafeToLinearHeading(new Vector2d(37, 25), Math.toRadians(90))


                        .afterTime(0.1, ()->{
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Colectare);
                            sleep(300);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Inchis);
                        })
                                .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d(37, 25),Math.toRadians(90)))
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
                        .strafeToLinearHeading(new Vector2d(14, 15), Math.toRadians(0))
                        .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Basket2);
                        })
                        .afterTime(1, ()->{
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Score);



                        })

                        .afterTime(2, ()->{
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);


                        })
                        .build());

        Actions.runBlocking(

                drive.actionBuilder(new Pose2d(new Vector2d(14, 15), Math.toRadians(0)))
                        .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Score);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
                            arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lat);
                        })
                        .strafeToLinearHeading(new Vector2d(70, -6), Math.toRadians(90))

                        .afterTime(0, ()->{
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Score);
                        })

                                .build());









        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
        sleep(100);







    }
}
