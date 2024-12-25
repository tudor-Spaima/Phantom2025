package org.firstinspires.ftc.teamcode.OpMode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.robit.KalmanFiltering;

@Config
@TeleOp(name = "KalmanFilterConfig")
public class KalmanFilterConfig extends LinearOpMode {
    public static double Q = 0.01;
    public static double R = 0.1;
    @Override
    public void runOpMode() throws InterruptedException {
        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        RevColorSensorV3 distanceSensor = hardwareMap.get(RevColorSensorV3.class, "sensor_distance");
        KalmanFiltering kalmanFilter = new KalmanFiltering(
                0,
                1,
                Q,
                R
        );
        waitForStart();
        while (!isStopRequested() && opModeIsActive()) {
            double rawDistance = distanceSensor.getDistance(DistanceUnit.CM);
            double filteredDistance = kalmanFilter.update(rawDistance, Q, R);
            telemetry.addData("Raw Distance", rawDistance);
            telemetry.addData("Filtered Distance", filteredDistance);
            telemetry.addData("Q (Process Noise)", Q);
            telemetry.addData("R (Measurement Noise)", R);
            telemetry.update();
        }
    }
}