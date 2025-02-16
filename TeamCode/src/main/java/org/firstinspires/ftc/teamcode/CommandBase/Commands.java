package org.firstinspires.ftc.teamcode.CommandBase;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robit.Arms;
import org.firstinspires.ftc.teamcode.robit.Extendo;
import org.firstinspires.ftc.teamcode.robit.GLOBALS;
import org.firstinspires.ftc.teamcode.robit.Lift;
import org.firstinspires.ftc.teamcode.robit.Senzori;

public class Commands {
    Lift lift;
    Extendo extendo;
    Arms arms;
    Senzori senzori;
    public Commands(HardwareMap hardwareMap){
         lift = new Lift( hardwareMap );
         extendo = new Extendo( hardwareMap );
         arms = new Arms( hardwareMap );
         senzori = new Senzori( hardwareMap );
    }
    public final void intakePositions(){
        lift.updateLiftPosition( GLOBALS.LiftPositions.Jos );
        extendo.updateExtendoPosition( GLOBALS.ExtendoPositions.Init );
        arms.updateBratIntakePosition( GLOBALS.brat_intake_positions.Intake );
        arms.updateBratScorePosition( GLOBALS.brat_score_positions.Safe );
        arms.updateGripperIntakePosition( GLOBALS.grippers_positions.Deschis );
        arms.updateGripperScorePosition( GLOBALS.grippers_positions.Deschis );
    }

    public final void transfer() throws InterruptedException {
        lift.updateLiftPosition( GLOBALS.LiftPositions.Jos );
        arms.updateBratIntakePosition( GLOBALS.brat_intake_positions.Transfer );
        arms.updateBratScorePosition( GLOBALS.brat_score_positions.Safe );
        arms.updatePivotPosition( GLOBALS.pivot_positions.Transfer );
        arms.updateRotireGripperPosition( GLOBALS.rotire_gripper_positions.pe_lat );
        sleep( 300 );
        extendo.updateExtendoPosition( GLOBALS.ExtendoPositions.Transfer );
        sleep( 200 );
        arms.updateBratScorePosition( GLOBALS.brat_score_positions.Transfer );
        sleep( 200 );
        arms.updateGripperScorePosition( GLOBALS.grippers_positions.Inchis );
        sleep( 100 );
        arms.updateGripperIntakePosition( GLOBALS.grippers_positions.Deschis );
        arms.updateBratScorePosition( GLOBALS.brat_score_positions.Score );
        sleep( 200 );
        extendo.updateExtendoPosition( GLOBALS.ExtendoPositions.Init );
        arms.updatePivotPosition( GLOBALS.pivot_positions.Score );
        arms.updateBratIntakePosition( GLOBALS.brat_intake_positions.Init );

    }
    public final void specimenIntakePositions() throws InterruptedException {
        lift.updateLiftPosition( GLOBALS.LiftPositions.Jos );
        extendo.updateExtendoPosition( GLOBALS.ExtendoPositions.Init );
        arms.updateBratIntakePosition( GLOBALS.brat_intake_positions.Init );
        arms.updateGripperIntakePosition( GLOBALS.grippers_positions.Deschis );
        arms.updateGripperScorePosition( GLOBALS.grippers_positions.Deschis );
        arms.updatePivotPosition( GLOBALS.pivot_positions.Specimen );
        sleep( 500 );
        arms.updateBratScorePosition( GLOBALS.brat_score_positions.Specimen );

    }
    public final void specimenScorePositions() throws InterruptedException {
        lift.updateLiftPosition(GLOBALS.LiftPositions.Specimen);
        arms.updateBratScorePosition(GLOBALS.brat_score_positions.SpecimenScorare);
        arms.updatePivotPosition(GLOBALS.pivot_positions.SpecimenScorare);
    }

}
