package frc.team5689.ck2018.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5689.ck2018.RMap;

public class InArm extends Subsystem {

    //Declare Motors Here
    private TalonSRX inMotorAngleL;
    private TalonSRX inMotorAngleR;
   // public TalonSRX smt;



    //----- Make Singleton -----
    public static InArm instance;

    public static InArm getInstance()
    {
        if (instance == null)
            instance = new InArm();

        return instance;
    }

    private InArm()  //private so no duplicate Subsystem is created
    {

        //initializes variables such as SpeedControllers, Pneumatics, etc.
        //Initialize Motors
        inMotorAngleL= new TalonSRX(RMap.intakeAngleLeft);
        inMotorAngleR = new TalonSRX(RMap.intakeAngleRight);
        //smt = new TalonSRX(RMap.shooterLeft);

        //Left Motor
        inMotorAngleL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        inMotorAngleL.setSensorPhase(true);
        inMotorAngleL.configPeakOutputForward(RMap.armPOW, 10);
        inMotorAngleL.configPeakOutputReverse(-RMap.armPOW, 10);
        inMotorAngleL.config_kF(0, SmartDashboard.getNumber("kF",RMap.armKF), 10);     // overcome friction
        inMotorAngleL.config_kP(0, RMap.armKP, 10);     // Proportional
        inMotorAngleL.config_kI(0, SmartDashboard.getNumber("kI",RMap.armKI), 10);   // Integral
        inMotorAngleL.config_kD(0, SmartDashboard.getNumber("kD",RMap.armKD), 10);   // Derivative

        //Right Motor
        inMotorAngleR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        inMotorAngleR.setSensorPhase(true);
        inMotorAngleR.configPeakOutputForward(RMap.armPOW, 10);
        inMotorAngleR.configPeakOutputReverse(-RMap.armPOW, 10);
        inMotorAngleR.config_kF(0, RMap.armKF, 10);     // overcome friction
        inMotorAngleR.config_kP(0, RMap.armKP, 10);     // Proportional
        inMotorAngleR.config_kI(0, RMap.armKI, 10);   // Integral
        inMotorAngleR.config_kD(0, RMap.armKD, 10);   // Derivative
        //TODO Get rid of getNumbers

    }


    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() { }
    public void setAngle(double angle){
        inMotorAngleL.set(ControlMode.Position, angle);//TODO Negate angle?
        inMotorAngleR.set(ControlMode.Position, angle);
        SmartDashboard.putNumber("POS", inMotorAngleR.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("POW", inMotorAngleR.getMotorOutputPercent());

    }

    public void stopMotor(){
        inMotorAngleL.set(ControlMode.Disabled, 0);
        inMotorAngleR.set(ControlMode.Disabled, 0);

    }

    public void resetangle(){
        inMotorAngleL.setSelectedSensorPosition(0,RMap.pididx,RMap.timeout);
        inMotorAngleR.setSelectedSensorPosition(0,RMap.pididx,RMap.timeout);
    }
}
