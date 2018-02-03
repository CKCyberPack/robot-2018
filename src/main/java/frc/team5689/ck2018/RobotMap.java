package frc.team5689.ck2018;

/////////////////////////////////////////////////////////
//** Magic Numbers Class ** I think
/////////////////////////////////////////////////////////
public class RobotMap {

    //PWM outputs
    public static int pwmRearLeftDrive = 0;
    public static int pwmRearRightDrive = 1;
    public static int pwmFrontLeftDrive = 2;
    public static int pwmFrontRightDrive = 3;



    public static int shooterLeft = 21;
    public static int shooterRight = 22;
    public static int preshootLeft = 25;
    public static int preshootRight = 26;

    public static int pcmShooterHigh = 0;
    public static int pcmShooterLow = 1;
    public static int pcmShooterFire = 2;


    //Robot Dependent Variables
    public static double driveDeadzone = 0.2;



    //SmartDashboard
    public static String robotMode = "Robot Mode";
    public static String driveMode = "Drive Mode";
    public static  String gameData = "Game Data";

}