package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5689.ck2018.RMap;
import frc.team5689.ck2018.Subsystems.BPiston;
import frc.team5689.ck2018.Subsystems.InArm;

import static frc.team5689.ck2018.Subsystems.BPiston.Position.High;
import static frc.team5689.ck2018.Subsystems.BPiston.Position.HighFLING;

public class TargetCubeCommand extends Command {

    private long timer;
    private boolean finished;

    public TargetCubeCommand() {
        //List Subsystems required to run this command
        requires(BPiston.getInstance());
        requires(InArm.getInstance());
    }

    /*
     *	This method sets up the command and is called immediately before the command
     *	is executed for the first time and every subsequent time it is started .
     */
    protected void initialize() {
        timer = System.currentTimeMillis();
        finished = BPiston.getInstance().getCurrentPos() == High;
    }

    /*
     * This method is called periodically (about every 20ms)
     */
    protected void execute() {
        BPiston.getInstance().setPosition(High);

        InArm.getInstance().setAngle(RMap.getIntakeAngleStopFling);

        if (System.currentTimeMillis() - timer >= RMap.timerAim) {
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
    protected void end() { }

    /* Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
        end();
    }
}
