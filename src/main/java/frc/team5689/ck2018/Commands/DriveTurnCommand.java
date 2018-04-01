package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5689.ck2018.RMap;
import frc.team5689.ck2018.Subsystems.DriveTrain;

public class DriveTurnCommand extends Command {

    private long timer;
    private double targetAngle;
    private boolean finished;
    private double maxG;

    @SuppressWarnings("WeakerAccess")
    public DriveTurnCommand(double angle) {//todo\
        targetAngle = angle;
        //List Subsystems required to run this command
        requires(DriveTrain.getInstance());
    }

    /*
     *	This method sets up the command and is called immediately before the command
     *	is executed for the first time and every subsequent time it is started .
     */
    protected void initialize() {
        //timer = System.currentTimeMillis();
        DriveTrain.getInstance().resetGyro();
        //maxG = 0;
        finished = false;
    }

    /*
     * This method is called periodically (about every 20ms)
     */
    protected void execute() {
        if (targetAngle < 0 ){
            //Turn Left
            DriveTrain.getInstance().autoTurnLeft(RMap.gyroTurnSpeed); //todo
        }else{
            //Turn Right
            DriveTrain.getInstance().autoTurnRight(RMap.gyroTurnSpeed);//todo
        }

        if (Math.abs(DriveTrain.getInstance().getAngle()) >= Math.abs(targetAngle)) {
            finished = true;
        }
    }

    /*
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        //Return if maxG or time has finished
        return (finished);
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
