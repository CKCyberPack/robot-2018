package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoDriveSwitch extends CommandGroup {
    public AutoDriveSwitch() {
        addParallel(new DriveForwardCommand(2));
        addParallel(new TargetLowCommand());
        addSequential(new ShootCommandGroup());
    }
}
