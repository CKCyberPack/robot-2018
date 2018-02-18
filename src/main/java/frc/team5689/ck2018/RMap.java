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
    public static int intakeArmLeftPort = 35;
    public static int intakeArmRightPort = 36;

    public static int intakeAngleLeftPort = 41;
    public static int intakeAngleRightPort = 42;

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
    public static int intakeAngleStopL= 2500;
    public static int intakeAngleStopR= 3000;
    public static int intakeAngleStep = 5;



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

    //Commands
    public static long shootTimer = 100;



    //SmartDashboard
    //-----------------//
    public static String robotMode = "Robot Mode";
    public static String driveMode = "Drive Mode";
    public static String gameData = "Game Data";

}