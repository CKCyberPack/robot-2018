package frc.team5689.ck2018.Subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5689.ck2018.DriveVictorSPX;
import frc.team5689.ck2018.RMap;

public class DriveTrain extends Subsystem {

    //Declare Motors Here
    private DriveVictorSPX rearRightMotor;
    private DriveVictorSPX rearLeftMotor;
    private DriveVictorSPX frontRightMotor;
    private DriveVictorSPX frontLeftMotor;

    private MecanumDrive ckDrive;
    private BuiltInAccelerometer ckAccel;
    private ADXRS450_Gyro ckGyro;


    //----- Make Singleton -----
    public static DriveTrain instance;

    public static DriveTrain getInstance() {
        if (instance == null) {
            instance = new DriveTrain();
        }
        return instance;
    }

    private DriveTrain()  //private so no duplicate Subsystem is created
    {
        //initializes variables such as SpeedControllers, Pneumatics, etc.
        rearLeftMotor = new DriveVictorSPX(RMap.driveRearLeft);
        rearRightMotor = new DriveVictorSPX(RMap.driveRearRight);
        frontLeftMotor = new DriveVictorSPX(RMap.driveFrontLeft);
        frontRightMotor = new DriveVictorSPX(RMap.driveFrontRight);

        ckDrive = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
        ckDrive.setDeadband(RMap.driveDeadzone);    // calibrate control sticks
        ckDrive.setSafetyEnabled(false);

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

    public void teleDriveCartesian(double forward, double rotation, double strafe) {
        ckDrive.driveCartesian(strafe, forward, rotation, 0);
    }
}
