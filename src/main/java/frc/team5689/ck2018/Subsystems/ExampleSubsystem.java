package frc.team5689.ck2018.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ExampleSubsystem extends Subsystem {

    //Declare Motors Here




    //----- Make Singleton -----
    public static ExampleSubsystem instance;

    public static ExampleSubsystem getInstance()
    {
        if (instance == null)
            instance = new ExampleSubsystem();

        return instance;
    }

    private ExampleSubsystem()  //private so no duplicate Subsystem is created
    {
        //initializes variables such as SpeedControllers, Pneumatics, etc.

    }


    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {

    }


    public void doSomething(){
        //Put motor commands here
    }

}
