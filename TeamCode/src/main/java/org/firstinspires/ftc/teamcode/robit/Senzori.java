package org.firstinspires.ftc.teamcode.robit;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Arrays;

public class Senzori {
    public RevColorSensorV3 senzorIntake,senzorIntakeCuloare = null;
    public Rev2mDistanceSensor senzorScore = null;
    private static final int RED_THRESHOLD = 150;
    private static final int BLUE_THRESHOLD = 150;
    private static final int GREEN_THRESHOLD = 150;
    private static final int TOLERANCE = 40;
    public Senzori(HardwareMap hardwareMap) {
        senzorIntake = hardwareMap.get(RevColorSensorV3.class, "senzorIntake");
        senzorIntakeCuloare = hardwareMap.get(RevColorSensorV3.class, "senzorIntake");
        senzorScore = hardwareMap.get(Rev2mDistanceSensor.class, "senzorsus");


        KalmanFiltering kalmanFilter = new KalmanFiltering(
                0,
                1,
                0.01,
                0.1
        );
    }

    public double getFilteredDistance( int samples) {
        double total = 0;
        for (int i = 0; i < samples; i++) {
            total += senzorScore.getDistance(DistanceUnit.CM);
        }
        return total / samples;
    }

    public double getDistance() {
        return senzorIntake.getDistance(DistanceUnit.CM);
    }
    public double getDistanceScore() {
        return senzorScore.getDistance(DistanceUnit.CM);
    }
    public boolean hasSample() {
        return senzorIntake.getDistance(DistanceUnit.CM) <= GLOBALS.gripper_has_sample;
    }
    public boolean hasSampleScore() {
        return senzorScore.getDistance(DistanceUnit.CM) <= GLOBALS.gripper_has_sampleScore;
    }
    public boolean hasSampleScore(int samples) {
        return getFilteredDistance(samples) <= GLOBALS.gripper_has_sampleScore;
    }
    public boolean sampleInRange() {
        return senzorIntake.getDistance(DistanceUnit.CM) <= GLOBALS.intake_distance;
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
    public  String detectColor(int r, int g, int b) {
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

    public boolean isRedOrYellow(int r, int g, int b) {
        return isRed(r, g, b) || isYellow(r, g, b);
    }

    public boolean isBlueOrYellow(int r, int g, int b) {
        return isBlue(r, g, b) || isYellow(r, g, b);
    }

}