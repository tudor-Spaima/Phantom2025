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
    public static final double Extended = 0.74;
    public static final double Retracted = 0.99;
    public static final double Transfer = 0.888;

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
        pe_lung, pe_lat, mai_asea;
    }
    public enum grippers_positions{
        Deschis, Inchis, Transfer
    }



    public static final double gripper_intake_inchis = 0.8;
    public static final double gripper_intake_deschis = 0.96;
    public static final double gripper_intake_transfer = 0.83;

    public static final double gripper_score_inchis = 0.005;
    public static final double gripper_score_deschis = 0.14;




    public static final double brat_intake_in = 0.82;
    public static final double brat_intake_colectare = 0.88;
    public static final  double brat_intake_transfer = 0.37; //0.4
    public static  final double brat_intake_init = 0.37;




    public static final double brat_score_init = 0.08;
    public  static final double brat_score_safe = 0.25;
    public static final double brat_score_transfer = 0.07 ;
    public static final double brat_score_sc = 0.4;
    public static final double brat_score_specimen = 0.02;




    public static final double pivot_score = 0.55;
    public static final double pivot_specimen = 0.52;
    public static final double pivot_transfer = 0.2;
    public  static  final double pivot_safe = 0.5;



    public final static double rotire_gripper_pe_lung = 0.5;//si astea tot de facut
    public final static double rotire_gripper_pe_lat = 0.25;
    public final static double rotire_gripper_mai_asea = 0.125;



    //Pozitii pentru specimen scorare, restul cu specimen sunt pentru colectare
    public  static  final int lift_specimen_scorare = 1350;
    public static final double brat_scorare_specimen_scorare = 0.6;
    public static final double pivot_specimen_scorare = 0.75;

    //parametrii senzori
    public static final double intake_distance = 0.01;
    public static final double gripper_has_sample = 0.75;




}
