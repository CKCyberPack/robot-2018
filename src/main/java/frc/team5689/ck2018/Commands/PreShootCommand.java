package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5689.ck2018.RMap;
import frc.team5689.ck2018.Subsystems.BMotor;
import frc.team5689.ck2018.Subsystems.BPiston;

public class PreShootCommand extends Command {

    private long timer;
    private boolean finished = false;
    private BPiston.Position curPosition;
    private double maxSpeed;

    public PreShootCommand() {
        //List Subsystems required to run this command
        requires(BMotor.getInstance());
    }

    /*
     *	This method sets up the command and is called immediately before the command
     *	is executed for the first time and every subsequent time it is started .
     */
    protected void initialize() {
        timer = System.currentTimeMillis();
        curPosition = BPiston.getInstance().getCurrentPos();
    }

    /*
     * This method is called periodically (about every 20ms)
     */
    protected void execute() {
        switch (curPosition){
            case High:
                maxSpeed = RMap.shootSpeedHigh;
                break;
            case Low:
                maxSpeed = RMap.shootSpeedLow;
                break;
            case Flat:
                maxSpeed = RMap.shootSpeedFlat;
                break;
            default:
                System.out.println("ERROR - Invalid Shooter Position");
        }
        BMotor.getInstance().setRPM(maxSpeed);
    }

    /*
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        if (BMotor.getInstance().getRPM() >= (maxSpeed - 50)){
            return true;
        }
        else{
            return false;
        }
    }

    /*
     * Called once after isFinished returns true
     */
    protected void end() {
        //BMotor.getInstance().stopMotor();
    }

    /* Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
        end();
    }
}
