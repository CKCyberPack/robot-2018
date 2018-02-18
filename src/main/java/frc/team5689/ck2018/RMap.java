package frc.team5689.ck2018;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/////////////////////////////////////////////////////////
//** Magic Numbers Class ** I think
/////////////////////////////////////////////////////////
public class RMap {


    //GLOBAL
    public static int timeout = 10;//10ms
    public static int pididx = 0;
    //CAN outputs
    //-------------//
    //Drive
    public static int driveRearLeft = 11;
    public static int driveRearRight = 12;
    public static int driveFrontLeft = 15;
    public static int driveFrontRight = 16;
    //Shooters
    public static int shooterLeft = 21;
    public static int shooterRight = 22;
    public static int preshootLeft = 25;
    public static int preshootRight = 26;
    //Intake
    public static int intakeLeft = 31;
    public static int intakeRight = 32;
    public static int intakeArmLeft = 35;
    public static int intakeArmRight = 36;

    public static int intakeAngleLeft = 41;
    public static int intakeAngleRight = 42;

    public static double armKP = 10;//SmartDashboard.getNumber("kP", 0);
    public static double armKI = 0;
    public static double armKD = 0;
    public static double armKF = 0;

    public static double shootKP = 10;//SmartDashboard.getNumber("kP", 0);
    public static double shootKI = 0;
    public static double shootKD = 0;
    public static double shootKF = 0;

    public static double armPOW = 0.5;


    //Intake angles
    public static int intakeAngle = 2300;


    //PCM
    public static DoubleSolenoid.Value pistonLaunch = DoubleSolenoid.Value.kForward;
    public static DoubleSolenoid.Value pistonLoad = DoubleSolenoid.Value.kReverse;

    //Shooter
    public static int pcmShooterHigh = 0;
    public static int pcmShooterLow = 1;
    public static int pcmShooterFireA = 2;
    public static int pcmShooterFireB = 3;
    //Shooter speeds
    public static double shootSpeed = 1;//0 is 0 % and 1 is 100%
    //Intake speeds
    public  static double intakeSpeed = 0.5;


    //Robot Dependent Variables
    //----------------------------//
    public static double driveDeadzone = 0.15;



    //SmartDashboard
    //-----------------//
    public static String robotMode = "Robot Mode";
    public static String driveMode = "Drive Mode";
    public static String gameData = "Game Data";

}