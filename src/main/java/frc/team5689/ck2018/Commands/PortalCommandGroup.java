package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PortalCommandGroup extends CommandGroup {
    public PortalCommandGroup(){
        addSequential(new CubeFlingUpCommand(1000));
        addSequential(new TargetFlatCommand());

    }
}
