package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootCommand extends CommandGroup {
    public ShootCommand(){
        addSequential(new PreShootCommand());
        addSequential(new ShootPistonCommand());
        addSequential(new PreShootStopCommand());
    }
}
