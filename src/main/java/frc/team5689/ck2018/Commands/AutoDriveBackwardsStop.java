package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoDriveBackwardsStop extends CommandGroup {
    public AutoDriveBackwardsStop() {
        //addSequential
        addSequential(new AutoWaitCommand(10000));//TODO
        addSequential(new DriveBackwardsCommand(3000));
    }
}
