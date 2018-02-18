package frc.team5689.ck2018.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5689.ck2018.RMap;

public class BMotor extends Subsystem {

    //Declare Motors Here
    private VictorSPX preShootLeft;
    private VictorSPX preShootRight;
    private TalonSRX shootLeft;
    private TalonSRX shootRight;

    //----- Make Singleton -----
    private static BMotor instance;

    public static BMotor getInstance() {
        if (instance == null) {
            instance = new BMotor();
        }
        return instance;
    }

    private BMotor()  //private so no duplicate Subsystem is created
    {
        //Initialize Motors
        preShootLeft = new VictorSPX(RMap.canPreShootLeft);
        preShootRight = new VictorSPX(RMap.canPreShootRight);
        shootLeft = new TalonSRX(RMap.canShooterLeft);
        shootRight = new TalonSRX(RMap.canShooterRight);

        //Set PID
        shootLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RMap.PIDIDX, RMap.TIMEOUT);
        shootLeft.setSensorPhase(true);
        //shootLeft.configNominalOutputReverse(0,RMap.TIMEOUT);
        shootLeft.config_kF(RMap.PIDIDX, RMap.shootKF, RMap.TIMEOUT);
        shootLeft.config_kP(RMap.PIDIDX, RMap.shootKP, RMap.TIMEOUT);
        shootLeft.config_kI(RMap.PIDIDX, RMap.shootKI, RMap.TIMEOUT);
        shootLeft.config_kD(RMap.PIDIDX, RMap.shootKD, RMap.TIMEOUT);

        shootRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RMap.PIDIDX, RMap.TIMEOUT);
        shootRight.setSensorPhase(false);
        //shootRight.configNominalOutputReverse(0,RMap.TIMEOUT);
        shootRight.config_kF(RMap.PIDIDX, RMap.shootKF, RMap.TIMEOUT);
        shootRight.config_kP(RMap.PIDIDX, RMap.shootKP, RMap.TIMEOUT);
        shootRight.config_kI(RMap.PIDIDX, RMap.shootKI, RMap.TIMEOUT);
        shootRight.config_kD(RMap.PIDIDX, RMap.shootKD, RMap.TIMEOUT);
    }


    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {

    }

    public void setRPM(double RPM) {
        shootLeft.set(ControlMode.Velocity, RPM);
        shootRight.set(ControlMode.Velocity, RPM);
        preShootLeft.set(ControlMode.Follower, shootLeft.getDeviceID());
        preShootRight.set(ControlMode.Follower, shootRight.getDeviceID());
        SmartDashboard.putNumber("ShootTarget", RPM);
    }

    public void setSpeed(double speed) {
        shootLeft.set(ControlMode.PercentOutput, speed);
        shootRight.set(ControlMode.PercentOutput, speed);
        preShootLeft.set(ControlMode.PercentOutput, speed);
        preShootRight.set(ControlMode.PercentOutput, speed);
        SmartDashboard.putNumber("ShootTarget", speed);
    }

    public void stopMotor() {
        shootLeft.set(ControlMode.Disabled, 0);
        shootRight.set(ControlMode.Disabled, 0);
        preShootLeft.set(ControlMode.Disabled, 0);
        preShootRight.set(ControlMode.Disabled, 0);
    }

    public double getRPM(){
        return Math.min(shootLeft.getSelectedSensorVelocity(RMap.PIDIDX), shootRight.getSelectedSensorVelocity(RMap.PIDIDX));
    }

    public void smartDashboard(){
        SmartDashboard.putNumber("ShootLVel", shootLeft.getSelectedSensorVelocity(RMap.PIDIDX));
        SmartDashboard.putNumber("ShootLPow", shootLeft.getMotorOutputPercent());
        SmartDashboard.putNumber("ShootRVel", shootRight.getSelectedSensorVelocity(RMap.PIDIDX));
        SmartDashboard.putNumber("ShootRPow", shootRight.getMotorOutputPercent());
    }
}
