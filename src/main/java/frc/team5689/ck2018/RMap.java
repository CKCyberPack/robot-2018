package frc.team5689.ck2018;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/////////////////////////////////////////////////////////
//** Magic Numbers Class **
/////////////////////////////////////////////////////////
public class RMap {


    //GLOBAL
    public static int timeout = 10;//10ms
    public static int pididx = 0;

    //CAN outputs
    //-------------//
    //Drive
    public static int driveRearLeftPort = 11;
    public static int driveRearRightPort = 12;
    public static int driveFrontLeftPort = 15;
    public static int driveFrontRightPort = 16;
    //Shooters
    public static int shooterLeftPort = 21;
    public static int shooterRightPort = 22;
    public static int preshootLeftPort = 25;
    public static int preshootRightPort = 26;
    //Intake
    public static int intakeLeftPort = 31;
    public static int intakeRightPort = 32;
    //Intake Arm
    public static int intakeAngleLeftPort = 41;
    public static int intakeAngleRightPort = 42;

    //PID Loop
    public static double armPOW = 0.4;
    public static double armKP = 10;//SmartDashboard.getNumber("kP", 0);
    public static double armKI = 0;
    public static double armKD = 0.01;
    public static double armKF = 0;

    public static double shootKP = 0.05;//SmartDashboard.getNumber("kP", 0);
    public static double shootKI = 0.0;
    public static double shootKD = 0;
    public static double shootKF = 0;

    public static double preShootSpeed = 0.5;

    //Intake angles
    public static int intakeAngle = 2000;
    public static int intakeAngleStopL= 4000;
    public static int intakeAngleStopR= 6000;
    public static int intakeAngleStep = 30;

    //Intake speeds
    public  static double intakeSpeed = 0.5;


    //PCM
    public static DoubleSolenoid.Value pistonLaunch = DoubleSolenoid.Value.kForward;
    public static DoubleSolenoid.Value pistonLoad = DoubleSolenoid.Value.kReverse;

    //Shooter
    public static int pcmShooterHigh = 4;
    public static int pcmShooterHighB = 1;
    public static int pcmShooterLow = 5;
    public static int pcmShooterLowB = 0;
    public static int pcmShooterFireA = 3;
    public static int pcmShooterFireB = 2;



    //Shooter speeds
//    public static double shootSpeedHigh = 5000;
//    public static double shootSpeedLow = 3000;
//    public static double shootSpeedFlat = 1000;
    public static double shootSpeedHigh = 1;
    public static double shootSpeedLow = 0.5;
    public static double shootSpeedFlat = 0.3;


    //Robot Dependent Variables
    //----------------------------//
    public static double driveDeadzone = 0.15;

    //Commands
    public static long shootTimer = 1000;
    public static long shootPistonTimer = 1000;
    public static long preShootTimer = 2000;


    //SmartDashboard
    //-----------------//
    public static String robotMode = "Robot Mode";
    public static String driveMode = "Drive Mode";
    public static String gameData = "Game Data";

}