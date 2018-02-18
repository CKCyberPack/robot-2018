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
    private static InArm instance;

    public static InArm getInstance() {
        if (instance == null) {
            instance = new InArm();
        }
        return instance;
    }

    private InArm()  //private so no duplicate Subsystem is created
    {
        //Initialize Motors
        inMotorAngleL = new TalonSRX(RMap.canIntakeArmRight);
        inMotorAngleR = new TalonSRX(RMap.canIntakeArmLeft);

        //Left Motor
        inMotorAngleL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RMap.PIDIDX, RMap.TIMEOUT);
        inMotorAngleL.setSensorPhase(true);
        inMotorAngleL.setInverted(true);
        inMotorAngleL.configPeakOutputForward(RMap.armPOW, RMap.TIMEOUT);
        inMotorAngleL.configPeakOutputReverse(-RMap.armPOW, RMap.TIMEOUT);
        inMotorAngleL.config_kF(RMap.PIDIDX, SmartDashboard.getNumber("kF", RMap.armKF), RMap.TIMEOUT);     // overcome friction
        inMotorAngleL.config_kP(RMap.PIDIDX, RMap.armKP, RMap.TIMEOUT);     // Proportional
        inMotorAngleL.config_kI(RMap.PIDIDX, SmartDashboard.getNumber("kI", RMap.armKI), RMap.TIMEOUT);   // Integral
        inMotorAngleL.config_kD(RMap.PIDIDX, SmartDashboard.getNumber("kD", RMap.armKD), RMap.TIMEOUT);   // Derivative

        //Right Motor
        inMotorAngleR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RMap.PIDIDX, RMap.TIMEOUT);
        inMotorAngleR.setSensorPhase(true);
        inMotorAngleR.setInverted(false);
        inMotorAngleR.configPeakOutputForward(RMap.armPOW, RMap.TIMEOUT);
        inMotorAngleR.configPeakOutputReverse(-RMap.armPOW, RMap.TIMEOUT);
        inMotorAngleR.config_kF(RMap.PIDIDX, RMap.armKF, RMap.TIMEOUT);     // overcome friction
        inMotorAngleR.config_kP(RMap.PIDIDX, RMap.armKP, RMap.TIMEOUT);     // Proportional
        inMotorAngleR.config_kI(RMap.PIDIDX, RMap.armKI, RMap.TIMEOUT);   // Integral
        inMotorAngleR.config_kD(RMap.PIDIDX, RMap.armKD, RMap.TIMEOUT);   // Derivative
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
        targetAngle = Math.min(Math.max(0, angle),RMap.intakeAngleMax);//Set between 0 and MaxAngle
        setLArmAngle(targetAngle);
        setRArmAngle(targetAngle);
    }

    private void setLArmAngle(double angle) {
        inMotorAngleL.set(ControlMode.Position, Math.min(angle,RMap.intakeAngleStop));
    }

    private void setRArmAngle(double angle) {
        //If Position is High, Don't let it go out so far
        if (BPiston.getInstance().getCurrentPos() == BPiston.Position.High){
            inMotorAngleR.set(ControlMode.Position, Math.min(angle,RMap.intakeAngleStop));
        }
        else{
            inMotorAngleR.set(ControlMode.Position, Math.min(angle,RMap.intakeAngleMax));
        }
    }

    public void stopMotor() {
        inMotorAngleL.set(ControlMode.Disabled, 0);
        inMotorAngleR.set(ControlMode.Disabled, 0);
    }

    public void resetAngle() {
        inMotorAngleL.setSelectedSensorPosition(0, RMap.PIDIDX, RMap.TIMEOUT);
        inMotorAngleR.setSelectedSensorPosition(0, RMap.PIDIDX, RMap.TIMEOUT);
    }

    public double getTargetAngle() {
        return targetAngle;
    }

    public void smartDashboard(){
        SmartDashboard.putNumber("ArmLPOS", inMotorAngleL.getSelectedSensorPosition(RMap.PIDIDX));
        SmartDashboard.putNumber("ArmLPOW", inMotorAngleL.getMotorOutputPercent());
        SmartDashboard.putNumber("ArmRPOS", inMotorAngleR.getSelectedSensorPosition(RMap.PIDIDX));
        SmartDashboard.putNumber("ArmRPOW", inMotorAngleR.getMotorOutputPercent());
        SmartDashboard.putNumber("ArmTarget", targetAngle);
    }

}
