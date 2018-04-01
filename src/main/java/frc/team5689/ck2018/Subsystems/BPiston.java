package frc.team5689.ck2018.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5689.ck2018.Commands.NullCommand;
import frc.team5689.ck2018.Commands.TargetFlatCommand;
import frc.team5689.ck2018.Commands.TargetHighCommand;
import frc.team5689.ck2018.Commands.TargetLowCommand;
import frc.team5689.ck2018.RMap;

public class BPiston extends Subsystem {

    //Declare Motors Here

    private DoubleSolenoid launcherPiston;
    private DoubleSolenoid highPiston;
    private DoubleSolenoid lowPiston;

    private Position currentPos = Position.Flat;

    public enum Position {
        Flat,
        Low,
        High
    }

    //----- Make Singleton -----
    private static BPiston instance;

    public static BPiston getInstance() {
        if (instance == null) {
            instance = new BPiston();
        }
        return instance;
    }

    private BPiston()  //private so no duplicate Subsystem is created
    {
        //initializes variables such as SpeedControllers, Pneumatics, etc.
        highPiston = new DoubleSolenoid(RMap.pcmShooterHigh, RMap.pcmShooterHighB);
        lowPiston = new DoubleSolenoid(RMap.pcmShooterLow, RMap.pcmShooterLowB);
        launcherPiston = new DoubleSolenoid(RMap.pcmShooterFireA, RMap.pcmShooterFireB);
    }

    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {

    }

    public void shoot() {
        launcherPiston.set(RMap.FIRE);
    }

    public void load() {
        launcherPiston.set(RMap.LOAD);
    }

    public Position getCurrentPos() {
        return currentPos;
    }

    public void setPosition(Position pos) {      // sets shooting angle
        switch (pos) {
            case Flat:
                lowPiston.set(RMap.DOWN);
                highPiston.set(RMap.DOWN);
                SmartDashboard.putString(RMap.shootPosition, "Flat");
                break;
            case Low:
                lowPiston.set(RMap.UP);
                highPiston.set(RMap.DOWN);
                SmartDashboard.putString(RMap.shootPosition, "Low");
                break;
            case High:
                lowPiston.set(RMap.UP);
                highPiston.set(RMap.UP);
                SmartDashboard.putString(RMap.shootPosition, "High");
                break;
        }
        currentPos = pos;
    }

    public Command nextPostion() {
        switch (currentPos) {
            case Flat:
                return new TargetLowCommand();
            case Low:
                return new TargetHighCommand();
            case High:
                return new TargetHighCommand();
            default:
                return new NullCommand();
        }
    }

    public Command maxPosition() {
        switch (currentPos) {
            case High:
                return new TargetHighCommand();
            case Low:
                return new TargetHighCommand();
            case Flat:
                return new TargetHighCommand();
            default:
                return new NullCommand();

        }
    }

    public Command prevPostion() {
        switch (currentPos) {
            case High:
                return new TargetLowCommand();
            case Low:
                return new TargetFlatCommand();
            case Flat:
                return new TargetFlatCommand();
            default:
                return new NullCommand();
        }
    }
}
