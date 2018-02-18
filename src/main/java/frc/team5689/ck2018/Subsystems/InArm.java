package frc.team5689.ck2018.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5689.ck2018.RMap;

public class InArm extends Subsystem {

    //Declare Motors Here
    private TalonSRX inMotorAngleL;
    private TalonSRX inMotorAngleR;

    private double targetAngle;

    //----- Make Singleton -----
    public static InArm instance;

    public static InArm getInstance() {
        if (instance == null) {
            instance = new InArm();
        }
        return instance;
    }

    private InArm()  //private so no duplicate Subsystem is created
    {
        //Initialize Motors
        inMotorAngleL = new TalonSRX(RMap.intakeAngleLeftPort);
        inMotorAngleR = new TalonSRX(RMap.intakeAngleRightPort);

        //Left Motor
        inMotorAngleL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RMap.pididx, RMap.timeout);
        inMotorAngleL.setSensorPhase(true);
        inMotorAngleL.setInverted(true);
        inMotorAngleL.configPeakOutputForward(RMap.armPOW, RMap.timeout);
        inMotorAngleL.configPeakOutputReverse(-RMap.armPOW, RMap.timeout);
        inMotorAngleL.config_kF(RMap.pididx, SmartDashboard.getNumber("kF", RMap.armKF), RMap.timeout);     // overcome friction
        inMotorAngleL.config_kP(RMap.pididx, RMap.armKP, RMap.timeout);     // Proportional
        inMotorAngleL.config_kI(RMap.pididx, SmartDashboard.getNumber("kI", RMap.armKI), RMap.timeout);   // Integral
        inMotorAngleL.config_kD(RMap.pididx, SmartDashboard.getNumber("kD", RMap.armKD), RMap.timeout);   // Derivative

        //Right Motor
        inMotorAngleR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RMap.pididx, RMap.timeout);
        inMotorAngleR.setSensorPhase(true);
        inMotorAngleR.setInverted(false);
        inMotorAngleR.configPeakOutputForward(RMap.armPOW, RMap.timeout);
        inMotorAngleR.configPeakOutputReverse(-RMap.armPOW, RMap.timeout);
        inMotorAngleR.config_kF(RMap.pididx, RMap.armKF, RMap.timeout);     // overcome friction
        inMotorAngleR.config_kP(RMap.pididx, RMap.armKP, RMap.timeout);     // Proportional
        inMotorAngleR.config_kI(RMap.pididx, RMap.armKI, RMap.timeout);   // Integral
        inMotorAngleR.config_kD(RMap.pididx, RMap.armKD, RMap.timeout);   // Derivative
    }


    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {
    }

    public void setAngle(double angle) {
        if (angle < 0) {
            angle = 0;
        }else if (angle >= Math.max(RMap.intakeAngleStopL, RMap.intakeAngleStopR)){
            angle = Math.max(RMap.intakeAngleStopL, RMap.intakeAngleStopR);
        }

        setLArmAngle(angle);
        setRArmAngle(angle);
        targetAngle = angle;
    }

    public void setLArmAngle(double angle) {
        if (angle >= RMap.intakeAngleStopL) {
            angle = RMap.intakeAngleStopL;
        }

        inMotorAngleL.set(ControlMode.Position, angle);
    }

    public void setRArmAngle(double angle) {
        if (angle >= RMap.intakeAngleStopR) {
            angle = RMap.intakeAngleStopR;
        }

        inMotorAngleR.set(ControlMode.Position, angle);
    }

    public void stopMotor() {
        inMotorAngleL.set(ControlMode.Disabled, 0);
        inMotorAngleR.set(ControlMode.Disabled, 0);
    }

    public void resetAngle() {
        inMotorAngleL.setSelectedSensorPosition(0, RMap.pididx, RMap.timeout);
        inMotorAngleR.setSelectedSensorPosition(0, RMap.pididx, RMap.timeout);
    }

    public double getTargetAngle() {
        return targetAngle;
    }

    public void smartDashboard(){
        SmartDashboard.putNumber("ArmLPOS", inMotorAngleL.getSelectedSensorPosition(RMap.pididx));
        SmartDashboard.putNumber("ArmLPOW", inMotorAngleL.getMotorOutputPercent());
        SmartDashboard.putNumber("ArmRPOS", inMotorAngleR.getSelectedSensorPosition(RMap.pididx));
        SmartDashboard.putNumber("ArmRPOW", inMotorAngleR.getMotorOutputPercent());
        SmartDashboard.putNumber("ArmTarget", targetAngle);
    }

}
