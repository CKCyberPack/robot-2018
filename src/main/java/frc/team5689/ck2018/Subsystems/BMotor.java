package frc.team5689.ck2018.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
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
        preShootLeft = new VictorSPX(RMap.preshootLeft);
        preShootRight = new VictorSPX(RMap.preshootRight);
        shootLeft = new TalonSRX(RMap.shooterLeft);
        shootRight = new TalonSRX(RMap.shooterRight);

        //Set PID
        shootLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RMap.pididx, RMap.timeout);
        //Todo shootLeft.setInverted();
        shootLeft.config_kF(RMap.pididx, RMap.shootKF);
        shootLeft.config_kP(RMap.pididx, RMap.shootKP);
        shootLeft.config_kI(RMap.pididx, RMap.shootKI);
        shootLeft.config_kD(RMap.pididx, RMap.shootKD);

        shootRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RMap.pididx, RMap.timeout);
        shootRight.config_kF(RMap.pididx, RMap.shootKF);
        shootRight.config_kP(RMap.pididx, RMap.shootKP);
        shootRight.config_kI(RMap.pididx, RMap.shootKI);
        shootRight.config_kD(RMap.pididx, RMap.shootKD);
    }


    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {

    }

    public void setspeed(double speed) {
        shootLeft.set(ControlMode.PercentOutput, speed); //Todo Reverse Shoot Motor?
        shootRight.set(ControlMode.PercentOutput, speed);
        preShootLeft.set(ControlMode.PercentOutput, shootLeft.getMotorOutputPercent());
        preShootRight.set(ControlMode.PercentOutput, shootRight.getMotorOutputPercent());
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

}
