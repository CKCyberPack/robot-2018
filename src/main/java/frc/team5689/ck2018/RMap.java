package frc.team5689.ck2018;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/////////////////////////////////////////////////////////
//** Magic Numbers Class **
/////////////////////////////////////////////////////////
public class RMap {


    //GLOBAL
    //-------------//
    public static int TIMEOUT = 10;
    public static int PIDIDX = 0;


    //CAN outputs
    //-------------//
    //Drive
    public static int canDriveRearLeft = 11;
    public static int canDriveRearRight = 12;
    public static int canDriveFrontLeft = 15;
    public static int canDriveFrontRight = 16;
    //Shooters
    public static int canShooterLeft = 21;
    public static int canShooterRight = 22;
    public static int canPreShootLeft = 25;
    public static int canPreShootRight = 26;
    //Intake
    public static int canIntakeLeft = 31;
    public static int canIntakeRight = 32;
    //Intake Arm
    public static int canIntakeArmRight = 41;
    public static int canIntakeArmLeft = 42;


    //PCM
    //-------------//
    public static int pcmShooterHigh = 4;
    public static int pcmShooterHighB = 1;
    public static int pcmShooterLow = 5;
    public static int pcmShooterLowB = 0;
    public static int pcmShooterFireA = 3;
    public static int pcmShooterFireB = 2;


    //Aliases
    //-------------//
    public static DoubleSolenoid.Value UP = DoubleSolenoid.Value.kForward;
    public static DoubleSolenoid.Value FIRE = DoubleSolenoid.Value.kForward;
    public static DoubleSolenoid.Value DOWN = DoubleSolenoid.Value.kReverse;
    public static DoubleSolenoid.Value LOAD = DoubleSolenoid.Value.kReverse;


    //PID Loop
    //-------------//
    //Intake Arm Angle
    public static double armPOW = 0.4;
    public static double armKP = 10;//SmartDashboard.getNumber("kP", 0);
    public static double armKI = 0;
    public static double armKD = 0.01;
    public static double armKF = 0;
    //Shooter Motors
    public static double shootKP = 0.05;//SmartDashboard.getNumber("kP", 0);
    public static double shootKI = 0.0;
    public static double shootKD = 0;
    public static double shootKF = 0;


    //Robot Dependent Variables
    //----------------------------//
    public static double DEADZONE = 0.15;
    //Intake angles
    public static int intakeAngle = 2000;
    public static int intakeAngleStop = 4000;
    public static int intakeAngleMax = 6000;
    public static int intakeAngleSpeed = 30;
    //Intake speeds
    public  static double intakeSpeedMax = 0.5;
    //Shooter speeds
    public static double shootRPMHigh = 5000;
    public static double shootRPMLow = 3000;
    public static double shootRPMFlat = 1000;
    public static double shootSpeedHigh = 1;
    public static double shootSpeedLow = 0.5;
    public static double shootSpeedFlat = 0.3;
    //Command Timing
    public static long timerShoot = 1000;
    public static long timerAim = 1000;
    public static long timerPreShoot = 2000;


    //SmartDashboard
    //-----------------//
    public static String robotMode = "Robot Mode";
    public static String driveMode = "Drive Mode";
    public static String gameData = "Game Data";
    public static String shootPosition = "Shooter";
}