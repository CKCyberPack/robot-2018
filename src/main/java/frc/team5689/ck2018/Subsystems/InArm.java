package frc.team5689.ck2018.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team5689.ck2018.RMap;

public class InArm extends Subsystem {

    //Declare Motors Here
    private TalonSRX inMotorAngleL;
    private TalonSRX inMotorAngleR;
    public TalonSRX smt;



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
        smt = new TalonSRX(RMap.shooterLeft);

        inMotorAngleL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        inMotorAngleL.setSensorPhase(true);
        inMotorAngleR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        inMotorAngleR.setSensorPhase(true);

        //SMT - For testing
        smt.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        smt.setSensorPhase(true);
        smt.configNominalOutputForward(0, 10);
        smt.configNominalOutputReverse(0, 10);
        smt.configPeakOutputForward(0.5, 10);
        smt.configPeakOutputReverse(-0.5, 10);
        //TODO Set PID Values
    }


    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() { }
    public void setAngle(double angle){
        inMotorAngleL.set(ControlMode.Position, angle);
        inMotorAngleR.set(ControlMode.Position, angle);
    }

    public void resetangle(){
        //TODO Reset intake arm angle
    }
}
