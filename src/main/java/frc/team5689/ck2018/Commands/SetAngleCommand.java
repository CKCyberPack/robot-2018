package frc.team5689.ck2018.Commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5689.ck2018.RMap;
import frc.team5689.ck2018.Robot;
import frc.team5689.ck2018.Subsystems.InArm;
import frc.team5689.ck2018.Subsystems.InMotor;

public class SetAngleCommand extends Command {

    public SetAngleCommand() {
        super();
        //List Subsystems required to run this command
        requires(Robot.ckInArm);
    }

    /*
     *	This method sets up the command and is called immediately before the command
     *	is executed for the first time and every subsequent time it is started .
     */
    protected void initialize() {
        System.out.println("INIT IS BROKEN?");
    }

    /*
     * This method is called periodically (about every 20ms)
     */
    protected void execute() {
        System.out.println("EXEC IS BROKEN?");
        Robot.ckInArm.setAngle(RMap.intakeAngle);
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

        Robot.ckInArm.stopMotor();

    }
}
