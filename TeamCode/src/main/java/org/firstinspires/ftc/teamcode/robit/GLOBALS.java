package org.firstinspires.ftc.teamcode.robit;

public class GLOBALS {


    //Lift
    public enum LiftPositions{
        Basket1, Basket2, Jos, Specimen;
    }

    public static final int Basket1 = 565;
    public static final int Basket2 = 1800;
    public static final int Jos = 0;

    //Extendo
    public enum ExtendoPositions{
        Extended, Retracted, Transfer, Init;
    }
    public static final double Init = 0.99;
    public static final double Extended = 0.735;
    public static final double Retracted = 0.99;
    public static final double Transfer = 0.25;

    //Arms
    public enum brat_intake_positions{
        Intake, Colectare, Transfer, Init;
    }
    public enum brat_score_positions{
        Init, Transfer, Score, Specimen, Safe, SpecimenScorare;
    }
    public enum pivot_positions{
        Score, Specimen, Transfer, Safe, SpecimenScorare;
    }
    public enum rotire_gripper_positions{
        pe_lung, pe_lat;
    }
    public enum grippers_positions{
        Deschis, Inchis;
    }



    public static final double gripper_intake_inchis = 0.83;
    public static final double gripper_intake_deschis = 0.97;
    public static final double gripper_score_inchis = 0.05;
    public static final double gripper_score_deschis = 0.325;




    public static final double brat_intake_in = 0.82;
    public static final double brat_intake_colectare = 0.88;
    public static final  double brat_intake_transfer = 0.4;
    public static  final double brat_intake_init = 0.37;




    public static final double brat_score_init = 0.08;
    public  static final double brat_score_safe = 0.25;
    public static final double brat_score_transfer = 0.055;
    public static final double brat_score_sc = 0.4;
    public static final double brat_score_specimen = 0.65;




    public static final double pivot_score = 0.55;
    public static final double pivot_specimen = 0.45;
    public static final double pivot_transfer = 0.2;
    public  static  final double pivot_safe = 0.5;



    public final static double rotire_gripper_pe_lung = 0.5;//si astea tot de facut
    public final static double rotire_gripper_pe_lat = 0.25;


    //Pozitii pentru specimen scorare, restul cu specimen sunt pentru colectare
    public  static  final int lift_specimen_scorare = 800;
    public static final double brat_scorare_specimen_scorare = 0.7;
    public static final double pivot_specimen_scorare = 0.2;




}