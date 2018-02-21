package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoDriveBackwardsStop extends CommandGroup {
    public AutoDriveBackwardsStop() {
        addSequential(new DriveBackwardsCommand(3));
    }
}
