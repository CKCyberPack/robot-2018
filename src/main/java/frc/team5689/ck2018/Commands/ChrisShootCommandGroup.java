package frc.team5689.ck2018.Commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ChrisShootCommandGroup extends CommandGroup {
    public ChrisShootCommandGroup(){
        //addSequential(new PreShootRPMCommand());
        addSequential(new ChrisPreShootSpeedCommand());
        addSequential(new ShootPistonCommand());
        addSequential(new PreShootStopCommand());
    }
}
