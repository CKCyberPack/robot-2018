package frc.team5689.ck2018.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team5689.ck2018.RMap;

public class InMotor extends Subsystem {

    //Declare Motors Here
    private VictorSPX inMotorL;
    private VictorSPX inMotorR;

    //----- Make Singleton -----
    private static InMotor instance;

    public static InMotor getInstance() {
        if (instance == null) {
            instance = new InMotor();
        }
        return instance;
    }

    private InMotor()  //private so no duplicate Subsystem is created
    {
        //Initialize Motors
        inMotorL = new VictorSPX(RMap.canIntakeLeft);
        inMotorR = new VictorSPX(RMap.canIntakeRight);

        inMotorL.setInverted(true);
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
        //Math.min to make sure you can't go over IntakeSpeed
        inMotorR.set(ControlMode.PercentOutput, Math.min(speed,RMap.intakeSpeedMax));
        inMotorL.set(ControlMode.PercentOutput, Math.min(speed,RMap.intakeSpeedMax));
    }

    public void stopIntake() {
        inMotorR.set(ControlMode.Disabled, 0);
        inMotorL.set(ControlMode.Disabled, 0);
    }
}
