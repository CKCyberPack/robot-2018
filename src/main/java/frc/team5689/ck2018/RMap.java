package frc.team5689.ck2018;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import sun.awt.SunHints;

/////////////////////////////////////////////////////////
//** Magic Numbers Class ** I think
/////////////////////////////////////////////////////////
public class RMap {

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


    //PCM
    public static DoubleSolenoid.Value pistonLaunch = DoubleSolenoid.Value.kForward;
    public static DoubleSolenoid.Value pistonLoad = DoubleSolenoid.Value.kReverse;

    //Shooter
    public static int pcmShooterHigh = 0;
    public static int pcmShooterLow = 1;
    public static int pcmShooterFireA = 2;
    public static int pcmShooterFireB = 3;


    //Robot Dependent Variables
    //----------------------------//
    public static double driveDeadzone = 0.1;



    //SmartDashboard
    //-----------------//
    public static String robotMode = "Robot Mode";
    public static String driveMode = "Drive Mode";
    public static String gameData = "Game Data";

}