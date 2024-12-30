package org.firstinspires.ftc.teamcode.robit;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Extendo extends GLOBALS {
    public Servo extendo = null;
    public Extendo(HardwareMap map){
        extendo = map.get(Servo.class, "extinere");
    }
    public void updateExtendoPosition(ExtendoPositions position){
        switch (position){
            case Extended:
                extendo.setPosition(Extended);
                break;
            case Retracted:
                extendo.setPosition(Retracted);
                break;
            case Transfer:
                extendo.setPosition(Transfer);
                break;
            case Init:
                extendo.setPosition(Init);
                break;
        }
    }

    public boolean isExtendoExtended(){
        return Math.abs(extendo.getPosition() - Extended) < 0.01;
    }



}
