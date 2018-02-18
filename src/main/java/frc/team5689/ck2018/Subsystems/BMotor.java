package frc.team5689.ck2018.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team5689.ck2018.RMap;

public class BMotor extends Subsystem {

    //Declare Motors Here
    private VictorSPX preShootLeft;
    private VictorSPX preShootRight;
    private TalonSRX shootLeft;
    private TalonSRX shootRight;

    //----- Make Singleton -----
    public static BMotor instance;

    public static BMotor getInstance() {
        if (instance == null) {
            instance = new BMotor();
        }
        return instance;
    }

    private BMotor()  //private so no duplicate Subsystem is created
    {
        //Initialize Motors
        preShootLeft = new VictorSPX(RMap.preshootLeftPort);
        preShootRight = new VictorSPX(RMap.preshootRightPort);
        shootLeft = new TalonSRX(RMap.shooterLeftPort);
        shootRight = new TalonSRX(RMap.shooterRightPort);

        //Set PID
        shootLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RMap.pididx, RMap.timeout);
        //Todo shootLeft.setInverted();
        shootLeft.config_kF(RMap.pididx, RMap.shootKF,RMap.timeout);
        shootLeft.config_kP(RMap.pididx, RMap.shootKP,RMap.timeout);
        shootLeft.config_kI(RMap.pididx, RMap.shootKI,RMap.timeout);
        shootLeft.config_kD(RMap.pididx, RMap.shootKD,RMap.timeout);

        shootRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RMap.pididx, RMap.timeout);
        shootRight.config_kF(RMap.pididx, RMap.shootKF,RMap.timeout);
        shootRight.config_kP(RMap.pididx, RMap.shootKP,RMap.timeout);
        shootRight.config_kI(RMap.pididx, RMap.shootKI,RMap.timeout);
        shootRight.config_kD(RMap.pididx, RMap.shootKD,RMap.timeout);
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
        preShootLeft.set(ControlMode.PercentOutput, shootLeft.getMotorOutputPercent());
        preShootRight.set(ControlMode.PercentOutput, shootRight.getMotorOutputPercent());
    }

    public void stopMotor() {
        shootLeft.set(ControlMode.Disabled, 0);
        shootRight.set(ControlMode.Disabled, 0);
        preShootLeft.set(ControlMode.Disabled, 0);
        preShootRight.set(ControlMode.Disabled, 0);
    }

    public double getRPM(){
        return Math.min(shootLeft.getSelectedSensorVelocity(RMap.pididx),shootRight.getSelectedSensorVelocity(RMap.pididx));
    }
}
