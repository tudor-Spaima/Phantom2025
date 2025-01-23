package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name = "coloare")
public class coloare extends OpMode {


        private ColorSensor colorSensor;

        private static final int RED_THRESHOLD = 150;
        private static final int BLUE_THRESHOLD = 150;
        private static final int GREEN_THRESHOLD = 150;
        private static final int TOLERANCE = 40;

        @Override
        public void init() {
            colorSensor = hardwareMap.get(ColorSensor.class, "senzorIntake");
            telemetry.addData("Status", "Initialized");
        }

        @Override
        public void loop() {
            int red = colorSensor.red();
            int green = colorSensor.green();
            int blue = colorSensor.blue();

            String detectedColor = detectColor(red, green, blue);

            telemetry.addData("Red", red);
            telemetry.addData("Green", green);
            telemetry.addData("Blue", blue);
            telemetry.addData("Detected Color", detectedColor);
            telemetry.update();
        }


    private String detectColor(int r, int g, int b) {
        if (isRed(r, g, b)) {
            return "Red";
        } else if (isYellow(r, g, b)) {
            return "Yellow";
        } else if (isBlue(r, g, b)) {
            return "Blue";
        } else {
            return "Unknown";
        }
    }

    private boolean isRed(int r, int g, int b) {
        return r > RED_THRESHOLD && r > g + (TOLERANCE + 20) && r > b + (TOLERANCE + 20);
    }

    private boolean isYellow(int r, int g, int b) {
        return r > RED_THRESHOLD && g > GREEN_THRESHOLD && (r - g) < (TOLERANCE - 10) && b < (g - TOLERANCE);
    }

    private boolean isBlue(int r, int g, int b) {
        return b > BLUE_THRESHOLD && b > r + TOLERANCE && b > g + TOLERANCE;
    }



}
