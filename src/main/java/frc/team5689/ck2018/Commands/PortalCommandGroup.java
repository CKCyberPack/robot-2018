package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PortalCommandGroup extends CommandGroup {
    public PortalCommandGroup(){
        addSequential(new CubeFlingDownCommand(1000));
        addSequential(new TargetHighCommand());

    }
}
