package frc.team5689.ck2018;


import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class DriveTrain {
    VictorSP rightFrontMotor;
    VictorSP leftFrontMotor;
    VictorSP rightBackMotor;
    VictorSP leftBackMotor;
    MecanumDrive ckDrive;


    public DriveTrain(){
        rightFrontMotor = new VictorSP(RobotMap.pwmRightFrontDrive);
        leftFrontMotor = new VictorSP(RobotMap.pwmLeftFrontDrive);
        rightBackMotor = new VictorSP(RobotMap.pwmRightBackDrive);
        leftBackMotor = new VictorSP(RobotMap.pwmLeftBackDrive);
        ckDrive = new MecanumDrive(leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor);
        ckDrive.setSafetyEnabled(false);

    }

    public void teleDriveCartesian(double forward, double strafe, double rotation){
        ckDrive.driveCartesian(forward, strafe, rotation);
    }
    public void teleDrivePolar(double mag, double angle, double zRot){
        ckDrive.drivePolar(mag, angle, zRot);
    }
}
