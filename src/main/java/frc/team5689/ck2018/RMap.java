package frc.team5689.ck2018;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/////////////////////////////////////////////////////////
//** Magic Numbers Class **
/////////////////////////////////////////////////////////
public class RMap {


    //GLOBAL
    //-------------//
    public static final int TIMEOUT = 10;
    public static final int PIDIDX = 0;


    //CAN outputs
    //-------------//
    //Drive
    public static final int canDriveRearLeft = 11;
    public static final int canDriveRearRight = 12;
    public static final int canDriveFrontLeft = 15;
    public static final int canDriveFrontRight = 16;
    //Shooters
    public static final int canShooterLeft = 21;
    public static final int canShooterRight = 22;
    public static final int canPreShootLeft = 25;
    public static final int canPreShootRight = 26;
    //Intake
    public static final int canIntakeLeft = 31;
    public static final int canIntakeRight = 32;
    //Intake Arm
    public static final int canIntakeArmRight = 41;
    public static final int canIntakeArmLeft = 42;


    //PCM
    //-------------//
    public static final int pcmShooterHigh = 4;
    public static final int pcmShooterHighB = 1;
    public static final int pcmShooterLow = 5;
    public static final int pcmShooterLowB = 0;
    public static final int pcmShooterFireA = 3;
    public static final int pcmShooterFireB = 2;


    //Aliases
    //-------------//
    public static final DoubleSolenoid.Value UP = DoubleSolenoid.Value.kForward;
    public static final DoubleSolenoid.Value FIRE = DoubleSolenoid.Value.kForward;
    public static final DoubleSolenoid.Value DOWN = DoubleSolenoid.Value.kReverse;
    public static final DoubleSolenoid.Value LOAD = DoubleSolenoid.Value.kReverse;


    //PID Loop
    //-------------//
    //Intake Arm Angle
    public static final double armPOW = 0.4;
    public static final double armKP = 10;//SmartDashboard.getNumber("kP", 0);
    public static final double armKI = 0;
    public static final double armKD = 0.01;
    public static final double armKF = 0;
    //Shooter Motors
    public static final double shootKP = 0.05;//SmartDashboard.getNumber("kP", 0);
    public static final double shootKI = 0.0;
    public static final double shootKD = 0;
    public static final double shootKF = 0;


    //Robot Dependent Variables
    //----------------------------//
    public static final double DEADZONE = 0.15;
    //Intake angles
    public static final int intakeAngle = 2000;
    public static final int intakeAngleStop = 4000;
    public static final int intakeAngleMax = 6000;
    public static final int intakeAngleSpeed = 30;
    //Intake speeds
    public static final double intakeSpeedMax = 0.5;
    //Shooter speeds
    public static final double shootRPMHigh = 5000;
    public static final double shootRPMLow = 3000;
    public static final double shootRPMFlat = 1000;
    public static final double shootSpeedHigh = 1;
    public static final double shootSpeedLow = 0.5;
    public static final double shootSpeedFlat = 0.3;
    //Command Timing
    public static final long timerShoot = 1000;
    public static final long timerAim = 1000;
    public static final long timerPreShoot = 2000;


    //SmartDashboard
    //-----------------//
    public static final String robotMode = "Robot Mode";
    public static final String driveMode = "Drive Mode";
    public static final String gameData = "Game Data";
    public static final String shootPosition = "Shooter";
}