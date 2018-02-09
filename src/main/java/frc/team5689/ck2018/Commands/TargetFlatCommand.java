package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5689.ck2018.Robot;
import frc.team5689.ck2018.Subsystems.BPiston;

import static frc.team5689.ck2018.Subsystems.BPiston.Position.Flat;

public class TargetFlatCommand extends Command {

    public TargetFlatCommand() {
        //List Subsystems required to run this command
        requires(Robot.ckBPiston);
    }

    /*
     *	This method sets up the command and is called immediately before the command
     *	is executed for the first time and every subsequent time it is started .
     */
    protected void initialize() {
    }

    /*
     * This method is called periodically (about every 20ms)
     */
    protected void execute() {
        BPiston.getInstance().setPosition(Flat);
    }

    /*
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        return false;
    }

    /*
     * Called once after isFinished returns true
     */
    protected void end() {

    }

    /* Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {

    }
}