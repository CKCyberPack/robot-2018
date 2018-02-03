package frc.team5689.ck2018;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain {
    private VictorSP rearRightMotor;
    private VictorSP rearLeftMotor;
    private VictorSP frontRightMotor;
    private VictorSP frontLeftMotor;

    VictorSPX rearL;
    VictorSPX rearR;
    VictorSPX frontL;
    VictorSPX frontR;

    private MecanumDrive ckDrive;
    private BuiltInAccelerometer ckAccel;
    ADXRS450_Gyro ckGyro;


    public DriveTrain() {
        rearLeftMotor = new VictorSP(RobotMap.pwmRearLeftDrive);
        rearRightMotor = new VictorSP(RobotMap.pwmRearRightDrive);
        frontLeftMotor = new VictorSP(RobotMap.pwmFrontLeftDrive);
        frontRightMotor = new VictorSP(RobotMap.pwmFrontRightDrive);

        rearL = new VictorSPX(RobotMap.pwmRearLeftDrive);
        rearR= new VictorSPX(RobotMap.pwmRearRightDrive);
        frontL= new VictorSPX(RobotMap.pwmFrontLeftDrive);
        frontR= new VictorSPX(RobotMap.pwmFrontRightDrive);

        rearRightMotor.setInverted(true);
        frontRightMotor.setInverted(true);

        ckDrive = new MecanumDrive(frontLeftMotor,rearLeftMotor,frontRightMotor,rearRightMotor);
        ckDrive.setSafetyEnabled(false);

        ckGyro = new ADXRS450_Gyro();
        ckAccel = new BuiltInAccelerometer();


        SmartDashboard.putData("DriveTrain", ckDrive);
        SmartDashboard.putData("Accel",ckAccel);
        SmartDashboard.putData("Gyro", ckGyro);

    }

    public void teleDriveCartesian(double forward, double rotation, double strafe) {
        if (Math.abs(forward) < RobotMap.driveDeadzone) {
            forward = 0;
        }
        if (Math.abs(rotation) < RobotMap.driveDeadzone) {
            rotation = 0;
        }
        if (Math.abs(strafe) < RobotMap.driveDeadzone) {
            strafe = 0;
        }
        ckDrive.driveCartesian(strafe, forward, rotation);
        rearL.set(ControlMode.PercentOutput,rearLeftMotor.getSpeed());
        rearR.set(ControlMode.PercentOutput,-rearRightMotor.getSpeed());
        frontL.set(ControlMode.PercentOutput,frontLeftMotor.getSpeed());
        frontR.set(ControlMode.PercentOutput,-frontRightMotor.getSpeed());
    }

    public void teleDrivePolar(double mag, double angle, double zRot) {
        ckDrive.drivePolar(mag, angle, zRot);
    }
}
