package org.firstinspires.ftc.teamcode.robit;


import com.qualcomm.robotcore.hardware.HardwareMap;

public class util  {
    Lift lift;
    Arms arms;
    Extendo extendo ;
    GLOBALS globals ;

    public util(HardwareMap hardwareMap){
        this.lift = new Lift(hardwareMap);
        this.arms = new Arms(hardwareMap);
        this.extendo = new Extendo(hardwareMap);
        this.globals = new GLOBALS();
    }

    public void initRobot(){
        lift.updateLiftPosition(GLOBALS.LiftPositions.Jos);
        extendo.updateExtendoPosition(GLOBALS.ExtendoPositions.Init);
        arms.updateBratIntakePosition(GLOBALS.brat_intake_positions.Init);
        arms.updateBratScorePosition(GLOBALS.brat_score_positions.Init);
        arms.updatePivotPosition(GLOBALS.pivot_positions.Safe);
        arms.updateGripperIntakePosition(GLOBALS.grippers_positions.Deschis);
        arms.updateGripperScorePosition(GLOBALS.grippers_positions.Deschis);
        arms.updateRotireGripperPosition(GLOBALS.rotire_gripper_positions.pe_lat);
    }
    public void goToSpecimen(){

    }

    public void intakeSpecimen(){

    }



}
