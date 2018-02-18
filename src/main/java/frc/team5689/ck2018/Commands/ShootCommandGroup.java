package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootCommandGroup extends CommandGroup {
    public ShootCommandGroup(){
        addSequential(new PreShootCommand());
        addSequential(new ShootPistonCommand());
        addSequential(new PreShootStopCommand());
    }
}
