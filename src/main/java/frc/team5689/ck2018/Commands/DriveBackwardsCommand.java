package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5689.ck2018.RMap;
import frc.team5689.ck2018.Subsystems.DriveTrain;

public class DriveBackwardsCommand extends Command {

    private long timer;
    private long totalTime;
    private boolean finished;
    private double maxG;

    @SuppressWarnings("WeakerAccess")
    public DriveBackwardsCommand(long timeInSeconds) {
        totalTime = timeInSeconds * 1000;
        //List Subsystems required to run this command
        requires(DriveTrain.getInstance());
    }

    /*
     *	This method sets up the command and is called immediately before the command
     *	is executed for the first time and every subsequent time it is started .
     */
    protected void initialize() {
        timer = System.currentTimeMillis();
        DriveTrain.getInstance().resetGyro();
        maxG = 0;
        finished = false;
    }

    /*
     * This method is called periodically (about every 20ms)
     */
    protected void execute() {
        DriveTrain.getInstance().driveStraight(-RMap.autoStraightSpeed);

        if (DriveTrain.getInstance().getAcceleration() > maxG) {
            maxG = DriveTrain.getInstance().getAcceleration();
        }

        if (System.currentTimeMillis() - timer >= totalTime) {
            finished = true;
        }
    }

    /*
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        //Return if maxG or time has finished
        return (maxG > RMap.maxCollisionG || finished);
    }

    /*
     * Called once after isFinished returns true
     */
    protected void end() {
        DriveTrain.getInstance().stopMotor();
    }

    /* Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
        end();
    }
}
