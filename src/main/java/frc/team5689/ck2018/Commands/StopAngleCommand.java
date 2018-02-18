package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5689.ck2018.RMap;
import frc.team5689.ck2018.Robot;

public class StopAngleCommand extends Command {

    public StopAngleCommand() {
        super();
        //List Subsystems required to run this command
        requires(Robot.ckInArm);
    }

    /*
     *	This method sets up the command and is called immediately before the command
     *	is executed for the first time and every subsequent time it is started .
     */
    protected void initialize() {
        System.out.println("INIT");
    }

    /*
     * This method is called periodically (about every 20ms)
     */
    protected void execute() {
        System.out.println("EXEC");
        Robot.ckInArm.stopMotor();
    }

    /*
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        System.out.print("Stop done");
        return true;
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

        Robot.ckInArm.stopMotor();

    }
}