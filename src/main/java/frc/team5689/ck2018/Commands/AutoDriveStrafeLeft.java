package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team5689.ck2018.RMap;

public class AutoDriveStrafeLeft extends CommandGroup {
    public AutoDriveStrafeLeft() {
        //Starting on the RIGHT, going for RIGHT switch
        addSequential(new DriveBackwardsCommand(500));
        addSequential(new DriveStrafeRightCommand(2100));
        addSequential(new DriveBackwardsCommand(3200));
        addSequential(new AngleSetCommand(RMap.intakeAngle));
        addSequential(new TargetLowCommand());
        addSequential(new ShootCommandGroup());
    }
}
