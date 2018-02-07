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
    private VictorSPX shootMotor1;
    private VictorSPX shootMotor2;
    private TalonSRX shootMotor3;
    private TalonSRX shootMotor4;

    public TalonSRX smt;



    //----- Make Singleton -----
    public static BMotor instance;

    public static BMotor getInstance()
    {
        if (instance == null)
            instance = new BMotor();

        return instance;
    }

    private BMotor()  //private so no duplicate Subsystem is created
    {
        //initializes variables such as SpeedControllers, Pneumatics, etc.
        //Initialize Motors
    /*    shootMotor1 = new VictorSPX(RMap.preshootLeft);
        shootMotor2 = new VictorSPX(RMap.preshootRight);
        shootMotor3 = new TalonSRX(RMap.shooterLeft);
        shootMotor4 = new TalonSRX(RMap.shooterRight);
        */

        //Shooter Test Motor
        smt = new TalonSRX(RMap.shooterLeft);
        smt.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        smt.setSensorPhase(true);
        smt.configNominalOutputForward(0, 10);
        smt.configNominalOutputReverse(0, 10);
        smt.configPeakOutputForward(0.5, 10);
        smt.configPeakOutputReverse(-0.5, 10);

    }


    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {

    }

    public void highshoot(){
        //TODO Setup motor
    }

    public void lowshoot(){
        //TODO Setup motor
    }
    public void flatshoot(){
        //TODO Setup motor
    }



}
