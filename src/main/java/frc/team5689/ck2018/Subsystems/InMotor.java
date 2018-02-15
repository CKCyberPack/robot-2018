package frc.team5689.ck2018.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team5689.ck2018.RMap;

public class InMotor extends Subsystem {

    //Declare Motors Here
    private VictorSPX inMotorL;
    private VictorSPX inMotorR;
    private VictorSPX inMotorArmL;
    private VictorSPX inMotorArmR;



    //----- Make Singleton -----
    public static InMotor instance;

    public static InMotor getInstance()
    {
        if (instance == null)
            instance = new InMotor();

        return instance;
    }

    private InMotor()  //private so no duplicate Subsystem is created
    {
        //initializes variables such as SpeedControllers, Pneumatics, etc.
        //Initialize Motors
        inMotorArmL= new VictorSPX(RMap.intakeArmLeft);
        inMotorArmR = new VictorSPX(RMap.intakeArmRight);
        inMotorL = new VictorSPX(RMap.intakeAngleLeft);
        inMotorR= new VictorSPX(RMap.intakeAngleRight);
    }


    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() { }
    public void setspeed(double speed){
        inMotorArmL.set(ControlMode.PercentOutput, speed);
        inMotorArmR.set(ControlMode.PercentOutput, speed);
        inMotorR.set(ControlMode.PercentOutput, speed);
        inMotorL.set(ControlMode.PercentOutput, speed);
    }

    public void stopIntake(){
        inMotorArmL.set(ControlMode.PercentOutput, 0);
        inMotorArmR.set(ControlMode.PercentOutput, 0);
        inMotorR.set(ControlMode.PercentOutput, 0);
        inMotorL.set(ControlMode.PercentOutput, 0);
    }

}
