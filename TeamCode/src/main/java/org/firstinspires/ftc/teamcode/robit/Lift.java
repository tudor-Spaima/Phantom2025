package org.firstinspires.ftc.teamcode.robit;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Lift extends GLOBALS {

    Telemetry telemetry;
    public DcMotorEx CulisantaDreapta, CulisantaStanga = null;



    public  Lift(HardwareMap map) {
        CulisantaDreapta = map.get(DcMotorEx.class, "CulisantaDreapta");
        CulisantaStanga = map.get(DcMotorEx.class, "CulisantaStanga");

        CulisantaStanga.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        CulisantaDreapta.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        CulisantaDreapta.setDirection(DcMotorSimple.Direction.FORWARD);
        CulisantaStanga.setDirection(DcMotorSimple.Direction.REVERSE);

        CulisantaStanga.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        CulisantaDreapta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        CulisantaStanga.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        CulisantaDreapta.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        CulisantaDreapta.setPower(0);
        CulisantaStanga.setPower(0);

    }
    public void updateLiftPosition(LiftPositions position){
        switch (position){
            case Basket1:
                goToPos(Basket1, 1, CulisantaDreapta);
                goToPos(Basket1, 1, CulisantaStanga);
                break;
            case Basket2:
                goToPos(Basket2, 1, CulisantaDreapta);
                goToPos(Basket2, 1, CulisantaStanga);
                break;
            case Jos:
                goToPos(Jos, 1, CulisantaDreapta);
                goToPos(Jos, 1, CulisantaStanga);
                break;
            case Specimen:
                goToPos(lift_specimen_scorare, 1, CulisantaDreapta);
                goToPos(lift_specimen_scorare, 1, CulisantaStanga);
                break;


        }
    }

    public void goToPos(int position, double power, DcMotor motor){
        motor.setTargetPosition(position);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(power);
    }



}
