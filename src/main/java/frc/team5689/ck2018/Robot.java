package frc.team5689.ck2018;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5689.ck2018.Commands.SetAngleCommand;
import frc.team5689.ck2018.Subsystems.*;

public class Robot extends IterativeRobot {
    //Subsystems
    public static ExampleSubsystem exampleSubsystem;
    public static DriveTrain ckDrive;
    public static BMotor ckBMotor;
    public static BPiston ckBPiston;
    public static InArm ckInArm;
    public static InMotor ckInMotor;

    //Commands
    private Command intakeCommand;


    //Auto variables
    private final static String START_LEFT = "left";//Left side of the field
    private final static String START_RIGHT = "right";//Right side of the field
    private final static String START_MIDDLE = "mid";//Middle starting position
    private String gameData;
    private int driveRobot = 0;


    //Components
    private XboxController ckController;
    private PowerDistributionPanel ckPDP;

    //Variables
    private boolean overrideSafety = false;
    private boolean ledOn = false;//LED


    @Override
    public void robotInit() {
        SmartDashboard.putString(RMap.robotMode, "Start Up");
        ckController = new XboxController(0);
        ckPDP = new PowerDistributionPanel();
        ckDrive = DriveTrain.getInstance();
        ckBMotor = BMotor.getInstance();
        ckBPiston = BPiston.getInstance();
        ckInArm = InArm.getInstance();
        ckInMotor = InMotor.getInstance();
    }

    @Override
    public void robotPeriodic() {
        gameData = DriverStation.getInstance().getGameSpecificMessage();//Gets the sides of the switches and scales.
        SmartDashboard.putString(RMap.gameData, gameData);
    }

    @Override
    public void disabledInit() {
        SmartDashboard.putString(RMap.robotMode, "Disabled");
    }


    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void autonomousInit() {
        SmartDashboard.putString(RMap.robotMode, "Auto");
        //Load Auto Commands
    }


    @Override
    public void autonomousPeriodic() {
        //Schedler.getInstance().run()
    }

    @Override
    public void teleopInit() {
        SmartDashboard.putString(RMap.robotMode, "Teleop");
        //Cancel Auto Commans command.cancel()
    }

    @Override
    public void teleopPeriodic() {
        //Schedler.getInstance().run()

        /////////////////
        // Drive Modes //
        /////////////////
        //System.out.println(driveRobot);
        switch (driveRobot) {
            case 0:                                     //forward - rotate  - strafe
                SmartDashboard.putString(RMap.driveMode, "Right Y - Right X - Left X");
                ckDrive.teleDriveCartesian(-ckController.getY(GenericHID.Hand.kRight), ckController.getX(GenericHID.Hand.kRight), ckController.getX(GenericHID.Hand.kLeft));
                break;
            case 1:
                SmartDashboard.putString(RMap.driveMode, "Right Y - Left X - Right X");
                ckDrive.teleDriveCartesian(-ckController.getY(GenericHID.Hand.kRight), ckController.getX(GenericHID.Hand.kLeft), ckController.getX(GenericHID.Hand.kRight));
                break;
            default:
                System.out.println("ERROR - No more drive modes");

        }
        if (ckController.getAButtonPressed()) {
            driveRobot++;
            if (driveRobot >= 2) {
                driveRobot = 0;
            }
        }
    }

    @Override
    public void testInit() {
        SmartDashboard.putString(RMap.robotMode, "Test");

        //Putting the values
        //TODO Delete
        SmartDashboard.putNumber("kF", RMap.armKF);
        SmartDashboard.putNumber("kP", RMap.armKP);
        SmartDashboard.putNumber("kI", RMap.armKI);
        SmartDashboard.putNumber("kD", RMap.armKD);

        intakeCommand = new SetAngleCommand();
    }

    @Override

    public void testPeriodic() {
        Scheduler.getInstance().run();

        if (ckController.getAButton()) {

            ckInArm.setAngle(0);
//            if (intakeCommand != null)
//                intakeCommand.cancel();
        }

        if (ckController.getBButton()) {
            ckInArm.setAngle(RMap.intakeAngle);
            System.out.print("Starting");
//            intakeCommand.start();
        }

        if (ckController.getStartButtonPressed())
            ckInArm.resetangle();


        if (ckController.getXButtonPressed()) {                  // reset back to encoder position 0 (spin backwards)
//            ckInArm.smt.set(ControlMode.Position, 0);
            ckInArm.setAngle(RMap.intakeAngle -300);
        }
        if (ckController.getYButtonPressed()) {                  // stop
//            ckInArm.smt.set(ControlMode.PercentOutput, 0);
            ckInArm.stopMotor();
        }


//        if (ckController.getBButtonPressed()){                  // sets speed to 2000rpm
//            //ckBMotor.shootMotorTest.chan
//            ckInArm.smt.set(ControlMode.Velocity , 2000);
//        }

    }

}