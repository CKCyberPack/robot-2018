package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5689.ck2018.RMap;
import frc.team5689.ck2018.Subsystems.BPiston;

public class ShootCommand extends Command {

    private long timer;
    private boolean finished = false;

    public ShootCommand() {
        //List Subsystems required to run this command
        requires(BPiston.getInstance());
    }

    /*
     *	This method sets up the command and is called immediately before the command
     *	is executed for the first time and every subsequent time it is started .
     */
    protected void initialize() {
        timer = System.currentTimeMillis();
    }

    /*
     * This method is called periodically (about every 20ms)
     */
    protected void execute() {
        BPiston.getInstance().shoot();

        if (System.currentTimeMillis() - timer >= RMap.shootTimer){
            finished = true;
        }
    }

    /*
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        return finished;
    }

    /*
     * Called once after isFinished returns true
     */
    protected void end() {
        BPiston.getInstance().load();
    }

    /* Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
        end();
    }
}
