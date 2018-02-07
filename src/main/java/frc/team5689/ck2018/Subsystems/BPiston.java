package frc.team5689.ck2018.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team5689.ck2018.RMap;

public class BPiston extends Subsystem {

    //Declare Motors Here

    private DoubleSolenoid launcherPiston;
    private Solenoid highPiston;
    private Solenoid lowPiston;
//    private int shooterPosition;
public enum Position
{
    Flat,
    Low,
    High
}
    //----- Make Singleton -----
    public static BPiston instance;

    public static BPiston getInstance()
    {
        if (instance == null)
            instance = new BPiston();

        return instance;
    }

    private BPiston()  //private so no duplicate Subsystem is created
    {
        //initializes variables such as SpeedControllers, Pneumatics, etc.
/*        highPiston = new Solenoid(RMap.pcmShooterHigh);
        lowPiston = new Solenoid(RMap.pcmShooterLow);*/

    }

    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {

    }

    public void shoot(){
        launcherPiston.set(RMap.pistonLaunch);
    }
    public void load(){
        launcherPiston.set(RMap.pistonLoad);
    }

    public void setPosition(Position pos){
        switch(pos){
            case Flat:
                lowPiston.set(false);
                highPiston.set(false);
                break;
            case Low:
                lowPiston.set(true);
                highPiston.set(false);
                break;
            case High:
                lowPiston.set(true);
                highPiston.set(true);
                break;
        }
    }
//    public void ShootHigh () {
//        highPiston.set(true);
//        lowPiston.set(true);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Shoot();
//    }
//
//    public void ShootLow () {
//        lowPiston.set(true);
//        highPiston.set(false);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Shoot();
//    }
//
//    private void Shoot() {
//        shootThread = new Thread();
//        try {
//            shootMotor1.set(ControlMode.PercentOutput,1);
//            shootMotor2.set(ControlMode.PercentOutput,1);
//            shootMotor3.set(ControlMode.PercentOutput,1);
//            shootMotor4.set(ControlMode.PercentOutput,1);
//            Thread.sleep(1000);
//            launcherPiston.set(RMap.pistonLaunch);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

}
