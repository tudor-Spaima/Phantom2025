package org.firstinspires.ftc.teamcode.robit;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Senzori {
    public RevColorSensorV3 senzorIntake,senzorOutake = null;
    public Senzori(HardwareMap hardwareMap) {
        senzorIntake = hardwareMap.get(RevColorSensorV3.class, "senzorIntake");
        KalmanFiltering kalmanFilter = new KalmanFiltering(
                0,
                1,
                0.01,
                0.1
        );
    }
}