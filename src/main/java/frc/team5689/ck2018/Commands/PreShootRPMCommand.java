package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team5689.ck2018.RMap;
import frc.team5689.ck2018.Subsystems.BMotor;
import frc.team5689.ck2018.Subsystems.BPiston;

public class PreShootRPMCommand extends Command {

    private long timer;
    private boolean finished = false;

    private BPiston.Position curPosition;
    private double maxRPM;

    public PreShootRPMCommand() {
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
        switch (curPosition) {
            case High:
                maxRPM = RMap.shootRPMHigh;
                break;
            case Low:
                maxRPM = RMap.shootRPMLow;
                break;
            case Flat:
                maxRPM = RMap.shootRPMFlat;
                break;
            default:
                System.out.println("ERROR - Invalid Shooter Position");
        }
        BMotor.getInstance().setRPM(maxRPM);

        if (System.currentTimeMillis() - timer >= RMap.timerPreShoot) {
            finished = true;
        }
    }

    /*
     * Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        //Finish if timer is up OR RPM is within set-point
        return finished || BMotor.getInstance().getRPM() > (maxRPM - 100);
    }

    /*
     * Called once after isFinished returns true
     */
    protected void end() {
        //Let Motor Keep Running
    }

    /* Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
        end();
    }
}
