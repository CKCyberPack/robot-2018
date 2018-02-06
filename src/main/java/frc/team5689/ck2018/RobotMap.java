package frc.team5689.ck2018;

/////////////////////////////////////////////////////////
//** Magic Numbers Class ** I think
/////////////////////////////////////////////////////////
public class RobotMap {

    //CAN outputs
    //-------------//
    //Drive
    public static int canRearLeft = 11;
    public static int canRearRight = 12;
    public static int canFrontLeft = 15;
    public static int canFrontRight = 16;
    //Shooters
    public static int shooterLeft = 21;
    public static int shooterRight = 22;
    public static int preshootLeft = 25;
    public static int preshootRight = 26;
    //Intake
    public static int suckerLeft = 0;
    public static int suckerRight = 0;


    //PCM
    //-------------//
    //Shooter
    public static int pcmShooterHigh = 0;
    public static int pcmShooterLow = 1;
    public static int pcmShooterFire = 2;
    //Intake
    public static int pcmSuckerLeft = 0;
    public static int pcmSuckerRight =1;


    //Robot Dependent Variables
    //----------------------------//
    public static double driveDeadzone = 0.1;



    //SmartDashboard
    //-----------------//
    public static String robotMode = "Robot Mode";
    public static String driveMode = "Drive Mode";
    public static String gameData = "Game Data";

}