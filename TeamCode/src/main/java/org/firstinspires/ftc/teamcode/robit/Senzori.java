package org.firstinspires.ftc.teamcode.robit;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Senzori {
    public RevColorSensorV3 senzorIntake = null;
    public Senzori(HardwareMap hardwareMap) {
        senzorIntake = hardwareMap.get(RevColorSensorV3.class, "senzorIntake");

        KalmanFiltering kalmanFilter = new KalmanFiltering(
                0,
                1,
                0.01,
                0.1
        );
    }

    public double getDistance() {
        return senzorIntake.getDistance(DistanceUnit.CM);
    }
    public boolean hasSample() {
        return senzorIntake.getDistance(DistanceUnit.CM) <= GLOBALS.gripper_has_sample;
    }
    public boolean sampleInRange() {
        return senzorIntake.getDistance(DistanceUnit.CM) <= GLOBALS.intake_distance;
    }
}