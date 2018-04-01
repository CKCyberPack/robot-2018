package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team5689.ck2018.RMap;

public class AutoDriveTurnSwitchLeft extends CommandGroup {
    public AutoDriveTurnSwitchLeft() {
        //Starting on the LEFT, going for LEFT switch.
        addSequential(new DriveBackwardsCommand(2600));
        addSequential(new DriveTurnCommand(90));
        addSequential(new DriveBackwardsCommand(1000));
        addSequential(new AngleSetCommand(RMap.intakeAngle));
        addSequential(new TargetLowCommand());
        addSequential(new ShootCommandGroup());
    }
}
