package org.firstinspires.ftc.teamcode.robit;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arms extends GLOBALS {
    public Servo gripper_intake, gripper_score, brat_intake, brat_score, pivot, rotire_gripper = null;
    public Arms(HardwareMap map){

        gripper_intake = map.get(Servo.class, "gripper");
        gripper_score = map.get(Servo.class, "transfer");
        brat_intake = map.get(Servo.class, "bratGripper");
        brat_score = map.get(Servo.class, "AxonStanga");
        pivot = map.get(Servo.class, "pozGripper");
        rotire_gripper = map.get(Servo.class, "rotireGripper");

    }
    public void updateGripperIntakePosition(@NonNull grippers_positions position){
        switch (position){
            case Deschis:
                gripper_intake.setPosition(gripper_intake_deschis);
                break;
            case Inchis:
                gripper_intake.setPosition(gripper_intake_inchis);
                break;
            case Transfer:
                gripper_intake.setPosition(gripper_intake_transfer);
        }
    }
    public void updateGripperScorePosition(@NonNull grippers_positions position){
        switch (position){
            case Deschis:
                gripper_score.setPosition(gripper_score_deschis);
                break;
            case Inchis:
                gripper_score.setPosition(gripper_score_inchis);
                break;
        }
    }
    public void updateBratIntakePosition(@NonNull brat_intake_positions position){
        switch (position){
            case Intake:
                brat_intake.setPosition(brat_intake_in);
                break;
            case Colectare:
                brat_intake.setPosition(brat_intake_colectare);
                break;
            case Transfer:
                brat_intake.setPosition(brat_intake_transfer);
                break;
            case Init:
                brat_intake.setPosition(brat_intake_init);
                break;
        }
    }
    public void updateBratScorePosition(@NonNull brat_score_positions position){
        switch (position){
            case Init:
                brat_score.setPosition(brat_score_init);
                break;
            case Score:
                brat_score.setPosition(brat_score_sc);
                break;
            case Specimen:
                brat_score.setPosition(brat_score_specimen);
                break;
            case Transfer:
                brat_score.setPosition(brat_score_transfer);
                break;
            case Safe:
                brat_score.setPosition(brat_score_safe);
                break;
            case SpecimenScorare:
                brat_score.setPosition(brat_scorare_specimen_scorare);
            case SpecimenScorareTELEOP:
                brat_score.setPosition(brat_scorare_specimen_scorareTELEOP);



        }
    }
    public void updatePivotPosition(@NonNull pivot_positions position){
        switch (position){
            case Score:
                pivot.setPosition(pivot_score);
                break;
            case Specimen:
                pivot.setPosition(pivot_specimen);
                break;
            case Transfer:
                pivot.setPosition(pivot_transfer);
                break;
            case Safe:
                pivot.setPosition(pivot_safe);
                break;
            case SpecimenScorare:
                pivot.setPosition(pivot_specimen_scorare);
            case SpecimenScorareTELEOP:
                pivot.setPosition(pivot_specimen_scorareTELEOP);

        }
    }
    public void updateRotireGripperPosition(@NonNull rotire_gripper_positions position){
        switch (position){
            case pe_lat:
                rotire_gripper.setPosition(rotire_gripper_pe_lat);
                break;
            case pe_lung:
                rotire_gripper.setPosition(rotire_gripper_pe_lung);
                break;
            case mai_asea:
                rotire_gripper.setPosition(rotire_gripper_mai_asea);
                break;

        }
    }







}
