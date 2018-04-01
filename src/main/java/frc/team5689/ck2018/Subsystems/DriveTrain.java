package frc.team5689.ck2018.Subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5689.ck2018.DriveVictorSPX;
import frc.team5689.ck2018.RMap;

public class DriveTrain extends Subsystem {

    private MecanumDrive ckDrive;
    private BuiltInAccelerometer ckAccel;
    private ADXRS450_Gyro ckGyro;


    //----- Make Singleton -----
    private static DriveTrain instance;

    public static DriveTrain getInstance() {
        if (instance == null) {
            instance = new DriveTrain();
        }
        return instance;
    }

    private DriveTrain()  //private so no duplicate Subsystem is created
    {
        //initializes variables such as SpeedControllers, Pneumatics, etc.
        DriveVictorSPX rearLeftMotor = new DriveVictorSPX(RMap.canDriveRearLeft);
        DriveVictorSPX rearRightMotor = new DriveVictorSPX(RMap.canDriveRearRight);
        DriveVictorSPX frontLeftMotor = new DriveVictorSPX(RMap.canDriveFrontLeft);
        DriveVictorSPX frontRightMotor = new DriveVictorSPX(RMap.canDriveFrontRight);

        ckDrive = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
        ckDrive.setDeadband(RMap.DEADZONE);
        ckDrive.setSafetyEnabled(false); //Make sure it doesn't run away

        ckGyro = new ADXRS450_Gyro();
        ckAccel = new BuiltInAccelerometer();

        //SmartDashboard.putData("DriveTrain", ckDrive);
        SmartDashboard.putData("Accelerometer", ckAccel);
        SmartDashboard.putData("Gyro", ckGyro);
    }


    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {

    }

    public double getAcceleration() {
        return ckAccel.getY();
    }

    public double getAngle() {
        return ckGyro.getAngle();
    }


    public void resetGyro() {
        ckGyro.reset();
    }

    public void teleDriveCartesian(double forward, double rotation, double strafe) {
        ckDrive.driveCartesian(strafe, forward, rotation, 0);
    }

    public void driveStraight(double speed) {
        teleDriveCartesian(speed, ckGyro.getAngle() * RMap.gyroStraightKP, 0);
    }

    public void autoTurnRight(double speed) { //todo
        teleDriveCartesian(0,speed, 0);
    }

    public void autoTurnLeft(double speed) { //todo
        teleDriveCartesian(0,-speed, 0);
    }

    public void stopMotor() {
        ckDrive.stopMotor();
    }
}
