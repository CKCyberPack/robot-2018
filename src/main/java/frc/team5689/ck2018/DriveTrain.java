package frc.team5689.ck2018;


import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain {

    private DriveVictorSPX rearRightMotor;
    private DriveVictorSPX rearLeftMotor;
    private DriveVictorSPX frontRightMotor;
    private DriveVictorSPX frontLeftMotor;

    private MecanumDrive ckDrive;
    private BuiltInAccelerometer ckAccel;
    private ADXRS450_Gyro ckGyro;

    DriveTrain() {

        rearLeftMotor = new DriveVictorSPX(RMap.canRearLeft);
        rearRightMotor = new DriveVictorSPX(RMap.canRearRight);
        frontLeftMotor = new DriveVictorSPX(RMap.canFrontLeft);
        frontRightMotor = new DriveVictorSPX(RMap.canFrontRight);

        rearRightMotor.setInverted(true);
        frontRightMotor.setInverted(true);

        ckDrive = new MecanumDrive(frontLeftMotor,rearLeftMotor,frontRightMotor,rearRightMotor);
        ckDrive.setDeadband(RMap.driveDeadzone);
        ckDrive.setSafetyEnabled(false);

        ckGyro = new ADXRS450_Gyro();
        ckAccel = new BuiltInAccelerometer();


        SmartDashboard.putData("DriveTrain", ckDrive);
        SmartDashboard.putData("Accel",ckAccel);
        SmartDashboard.putData("Gyro", ckGyro);

    }

    public void teleDriveCartesian(double forward, double rotation, double strafe) {

        ckDrive.driveCartesian(strafe, forward, rotation, 0);

    }
}
