package frc.team5689.ck2018;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5689.ck2018.Commands.*;
import frc.team5689.ck2018.Subsystems.*;

public class Robot extends IterativeRobot {
    //Commands

    //Auto variables
    private final static String START_LEFT = "left";//Left side of the field
    private final static String START_RIGHT = "right";//Right side of the field
    private final static String START_MIDDLE = "mid";//Middle starting position
    private String gameData;
    private int driveRobot = 0;

    //Components
    public DriveTrain ckDrive;
    private XboxController ckController;
    private PowerDistributionPanel ckPDP;
    private Compressor ckCompressor;

    //Variables
    private boolean shooterReleased = true;
    private boolean overrideSafety = false;
    private boolean ledOn = false;

    //Commands
    private Command increaseAngle;
    private Command decreaseAngle;


    @Override
    public void robotInit() {
        SmartDashboard.putString(RMap.robotMode, "Start Up");
        ckController = new XboxController(0);
        ckDrive = DriveTrain.getInstance();
        ckCompressor = new Compressor();
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
        //TODO Load Auto Commands
    }


    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        SmartDashboard.putString(RMap.robotMode, "Teleop");
        Scheduler.getInstance().removeAll(); //TODO Test that this clears them
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();

        /////////////////////
        // Smart Dashboard //
        /////////////////////
        InArm.getInstance().smartDashboard();
        BMotor.getInstance().smartDashboard();

        ///////////////
        // Arm Angle //
        ///////////////
        if (ckController.getAButtonPressed()) { //Hold this to increase angle
            increaseAngle = new AngleIncreaseCommand();
            increaseAngle.start();
        }else if (ckController.getAButtonReleased()){
            if (increaseAngle != null){
                increaseAngle.cancel();
            }
        }

        if (ckController.getXButton()) { //Hold this to decrease angle
            decreaseAngle = new AngleDecreaseCommand();
            decreaseAngle.start();
        }else if (ckController.getXButtonReleased()){
            if (decreaseAngle != null){
                decreaseAngle.cancel();
            }
        }

        if (ckController.getBButtonPressed()) {
            new AngleSetCommand().start();
        }

        if (ckController.getYButtonPressed()) {
            new AngleStopCommand().start();
        }
        // Reset Encoder
        if (ckController.getStartButtonPressed()){
            InArm.getInstance().resetAngle();
        }

        /////////////////
        // Aim Shooter //
        /////////////////
        if (ckController.getBumperPressed(GenericHID.Hand.kLeft)){
            Command targetCommand = BPiston.getInstance().nextPostion();
            if (targetCommand != null){
                targetCommand.start();
            }
        }
        if (ckController.getTriggerAxis(GenericHID.Hand.kLeft) >= 0.5 && shooterReleased == true){
            shooterReleased = false;
            Command targetCommand = BPiston.getInstance().prevPostion();
            if (targetCommand != null){
                targetCommand.start();
            }
        }
        //Release the shooter variable
        if (ckController.getTriggerAxis(GenericHID.Hand.kLeft) < 0.5){
            shooterReleased = true;
        }

        ///////////////////
        // Shoot Shooter //
        ///////////////////
        if (ckController.getBumperPressed(GenericHID.Hand.kRight)) {
            //new PreShootCommand().start();
            new ShootCommandGroup().start();
        }

        ////////////
        // Intake //
        ////////////
        InMotor.getInstance().setspeed(ckController.getTriggerAxis(GenericHID.Hand.kRight));

        /////////////////
        // Drive Modes //
        /////////////////
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
        if (ckController.getBackButtonPressed()) {//Switches between drive moves
            driveRobot++;
            if (driveRobot >= 2) {
                driveRobot = 0;
            }
        }
    }

    @Override
    public void testInit() {
        SmartDashboard.putString(RMap.robotMode, "Test");
    }

    @Override
    public void testPeriodic() {
        //Scheduler.getInstance().run(); //Scheduler won't run in test
    }

}