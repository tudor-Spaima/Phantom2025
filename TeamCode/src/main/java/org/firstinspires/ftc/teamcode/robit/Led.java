package org.firstinspires.ftc.teamcode.robit;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.internal.hardware.android.DragonboardIndicatorLED;

public class Led {
    Servo bec ;
    public Led(HardwareMap hardwareMap){
        this.bec= hardwareMap.get(Servo.class, "led");
    }
    public void setColor(double val){
        this.bec.setPosition(val);
    }
}
