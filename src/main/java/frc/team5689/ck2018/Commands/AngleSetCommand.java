package frc.team5689.ck2018.Commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5689.ck2018.RMap;
import frc.team5689.ck2018.Robot;
import frc.team5689.ck2018.Subsystems.InArm;
import frc.team5689.ck2018.Subsystems.InMotor;

public class AngleSetCommand extends Command {

    public AngleSetCommand() {
        //List Subsystems required to run this command
        requires(InArm.getInstance());
    }

    /*
     *	This method sets up the command and is called immediately before the command
     *	is executed for the first time and every subsequent time it is started .
     */
    protected void initialize() {}

    /*
     * This method is called periodically (about every 20ms)
     */
    protected void execute() {
        InArm.getInstance().setAngle(RMap.intakeAngle);
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
        InArm.getInstance().stopMotor();
    }

    /* Called when another command which requires one or more of the same
     * subsystems is scheduled to run
     */
    protected void interrupted() {
        end();
    }
}