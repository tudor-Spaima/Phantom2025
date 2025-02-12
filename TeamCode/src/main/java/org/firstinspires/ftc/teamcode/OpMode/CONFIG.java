package org.firstinspires.ftc.teamcode.OpMode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.PinpointDrive;
import org.firstinspires.ftc.teamcode.robit.Arms;
import org.firstinspires.ftc.teamcode.robit.Extendo;
import org.firstinspires.ftc.teamcode.robit.Lift;

@Config
@TeleOp(name = "CONFIG")
public class CONFIG  extends LinearOpMode {

    public static double PIVOT = 0.25;
    public static double BRAT_SCORE = 0.37;
    public static double BRAT_INTAKE = 0.37;
    public static double ROTIRE_GRIPPER = 0.25;
    public static double GRIPPER_SCORE = 0.05;
    public static double GRIPPER_INTAKE = 0.97;

    public static double EXTENDO = 0.99;



    @Override
    public void runOpMode() throws InterruptedException {

        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        PinpointDrive drive = new PinpointDrive(hardwareMap, new Pose2d(0, 0, 0));
        Lift lift = new Lift(hardwareMap);
        Extendo extendo = new Extendo(hardwareMap);
        Arms arms = new Arms(hardwareMap);

        waitForStart();
        while(!isStopRequested() && opModeIsActive()){

            arms.pivot.setPosition(PIVOT);
            arms.brat_score.setPosition(BRAT_SCORE);
            arms.brat_intake.setPosition(BRAT_INTAKE);
            arms.rotire_gripper.setPosition(ROTIRE_GRIPPER);
            extendo.extendo.setPosition(EXTENDO);

            arms.gripper_score.setPosition(GRIPPER_SCORE);
            arms.gripper_intake.setPosition(GRIPPER_INTAKE);




            telemetry.addData("pivot", arms.pivot.getPosition());
            telemetry.addData("brat_score", arms.brat_score.getPosition());
            telemetry.addData("brat_intake", arms.brat_intake.getPosition());
            telemetry.addData("dreapta ", lift.CulisantaDreapta.getCurrentPosition());
            telemetry.addData("stanga ", lift.CulisantaStanga.getCurrentPosition());


            telemetry.update();











        }
    }
}


