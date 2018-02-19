package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoDriveForwardStop extends CommandGroup {
    public AutoDriveForwardStop() {
        addSequential(new DriveForwardCommand(2));
    }
}
