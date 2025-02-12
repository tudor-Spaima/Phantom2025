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
import org.firstinspires.ftc.teamcode.robit.Senzori;

import java.util.Arrays;

@Autonomous(name = "auto_sample_V2_SIMPLU")
public class auto_sample_SIMPLU extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {


        Lift lift = new Lift(hardwareMap);
        Extendo extendo = new Extendo(hardwareMap);
        Arms arms = new Arms(hardwareMap);
        ElapsedTime timp = new ElapsedTime();
        Senzori senzori = new Senzori(hardwareMap);

        double timpMinim = 2;
        double pas = 5.0;
        int rep = 0;

        //INITIALIZARE
        lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Init);
        arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);
        arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Inchis);
        arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lat);



        VelConstraint velPuternic = new MinVelConstraint(Arrays.asList(
                new TranslationalVelConstraint(120),
                new AngularVelConstraint(Math.PI / 2)
        ));
        AccelConstraint accelPuternic = new ProfileAccelConstraint(-50, 80);


        Pose2d start = new Pose2d(0,0,Math.toRadians(0));
        PinpointDrive drive = new PinpointDrive(hardwareMap, start);




        waitForStart();
        timp.startTime();


        //preload
        Actions.runBlocking(
                drive.actionBuilder(start)
                        .afterTime(0.1, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Basket2);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Score);
                        })

                        .strafeToConstantHeading(new Vector2d(15, 35.5))
                        .strafeToConstantHeading(new Vector2d(13, 37.5))

                        //.strafeToLinearHeading(new Vector2d( 8.5, 24), Math.toRadians( -45 ) )


                        .afterTime( 0, ( ) -> {
                            sleep(500);
                            arms.updateGripperScorePosition( GLOBALS.grippers_positions.Deschis );
                        })

                        .build());

        //colectare sample 1
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))
                .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                        })

                        .strafeToLinearHeading(new Vector2d( 23.7, 28), Math.toRadians( 0 ) )


                        .afterTime( 0, ( ) -> {
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Colectare);

                        })
                        .afterTime( 0.3, ( ) -> {
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Inchis);
                        })
                        .build());

        //scorare sample 1
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))

                        .afterTime(0, ()->{ new Thread(()-> {
                            lift.updateLiftPosition( GLOBALS.LiftPositions.Jos );
                            arms.updateBratIntakePosition( GLOBALS.brat_intake_positions.Transfer );
                            arms.updateBratScorePosition( GLOBALS.brat_score_positions.Safe );
                            arms.updatePivotPosition( GLOBALS.pivot_positions.Transfer );
                            arms.updateRotireGripperPosition( GLOBALS.rotire_gripper_positions.pe_lat );

                            sleep( 300 );
                            extendo.updateExtendoPosition( GLOBALS.ExtendoPositions.Transfer );

                            sleep( 300 );
                            arms.updateBratScorePosition( GLOBALS.brat_score_positions.Transfer );
                            sleep( 400 );
                            arms.updateGripperScorePosition( GLOBALS.grippers_positions.Inchis );
                            sleep( 100 );
                            arms.updateGripperIntakePosition( GLOBALS.grippers_positions.Deschis );

                            sleep( 200 );
                            lift.updateLiftPosition( GLOBALS.LiftPositions.Basket2 );
                            extendo.updateExtendoPosition( GLOBALS.ExtendoPositions.Init );

                            arms.updatePivotPosition( GLOBALS.pivot_positions.Score );
                            arms.updateBratIntakePosition( GLOBALS.brat_intake_positions.Init );
                            }).start();
                        })

                        .strafeToLinearHeading(new Vector2d( 8.5, 24), Math.toRadians( -45 ) )

                        .afterTime( 1, ( ) -> {
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Score);
                            sleep(1000);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                                                 })
                        .build());


        //colectare sample 2
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))

                        .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                        })

                        .strafeToLinearHeading(new Vector2d(21.8, 19), Math.toRadians(0))


                        .afterTime( 0, ( ) -> {
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Colectare);

                        })
                        .afterTime( 0.3, ( ) -> {
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Inchis);
                        })

                        .build());


        //scorare sample 2
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))

                        .afterTime(0, ()->{ new Thread(()-> {
                            lift.updateLiftPosition( GLOBALS.LiftPositions.Jos );
                            arms.updateBratIntakePosition( GLOBALS.brat_intake_positions.Transfer );
                            arms.updateBratScorePosition( GLOBALS.brat_score_positions.Safe );
                            arms.updatePivotPosition( GLOBALS.pivot_positions.Transfer );
                            arms.updateRotireGripperPosition( GLOBALS.rotire_gripper_positions.pe_lat );

                            sleep( 300 );
                            extendo.updateExtendoPosition( GLOBALS.ExtendoPositions.Transfer );

                            sleep( 300 );
                            arms.updateBratScorePosition( GLOBALS.brat_score_positions.Transfer );
                            sleep( 400 );
                            arms.updateGripperScorePosition( GLOBALS.grippers_positions.Inchis );
                            sleep( 100 );
                            arms.updateGripperIntakePosition( GLOBALS.grippers_positions.Deschis );

                            sleep( 200 );
                            lift.updateLiftPosition( GLOBALS.LiftPositions.Basket2 );
                            extendo.updateExtendoPosition( GLOBALS.ExtendoPositions.Init );

                            arms.updatePivotPosition( GLOBALS.pivot_positions.Score );
                            arms.updateBratIntakePosition( GLOBALS.brat_intake_positions.Init );
                            }).start();
                        })

                        .strafeToLinearHeading(new Vector2d( 8.5, 24), Math.toRadians( -45 ) )

                        .afterTime( 1, ( ) -> {
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Score);
                            sleep(1000);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                                                 })
                        .build());



        //colectare sample 3
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))

                        .afterTime(0, ()->{
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Safe);
                            arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lung);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Intake);
                        })

                        .strafeToLinearHeading(new Vector2d(36, 25), Math.toRadians(90))

                        .afterTime( 0, ( ) -> {
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Colectare);

                        })
                        .afterTime( 0.3, ( ) -> {
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Inchis);
                        })

                        .build());

        //scorare sample 3
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(new Vector2d (drive.pose.position.x,drive.pose.position.y),drive.pose.heading.toDouble()))

                        .afterTime(0, ()->{ new Thread(()-> {
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

                            sleep(200);
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Basket2);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);

                            arms.updatePivotPosition(GLOBALS.pivot_positions.Score);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            }).start();
                        })

                        .strafeToLinearHeading(new Vector2d( 8.5, 24), Math.toRadians( -45 ) )

                        .afterTime( 1, ( ) -> {
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Score);
                            sleep(1000);
                            arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
                                                 })

               .build());



            //parcare
            Actions.runBlocking(
                    drive.actionBuilder( new Pose2d( new Vector2d( drive.pose.position.x, drive.pose.position.y ), drive.pose.heading.toDouble() ) )

                    .afterTime( 0, ( ) -> {
                        new Thread( ( ) -> {
                            lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
                            extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
                            arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
                            arms.updateBratScorePosition(GLOBALS.brat_score_positions.Init);
                            arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);
                            arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
                            arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lat);
                        } ).start();
                    })

                    .strafeToLinearHeading(new Vector2d(55, 15), Math.toRadians(90), velPuternic, accelPuternic)
                    .strafeToLinearHeading(new Vector2d(55, -5), Math.toRadians(90), velPuternic, accelPuternic)
                    .build());




        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Score);
        sleep(1500);
        stop();

        telemetry.addData("timp", timp.time());
        telemetry.update();
    }
}
