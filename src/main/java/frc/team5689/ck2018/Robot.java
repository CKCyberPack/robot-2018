package frc.team5689.ck2018;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5689.ck2018.Commands.*;
import frc.team5689.ck2018.Subsystems.*;

public class Robot extends IterativeRobot {

    //ENUMS
    private enum Position {
        MIDDLE,
        LEFT,
        RIGHT
    }

    private enum Auto {
        NOTHING,
        FORWARD,
        SWITCH
    }

    //Auto variables
    private String gameData;
    private SendableChooser<Position> sideChooser;
    private SendableChooser<Auto> autoChooser;

    //Variables
    private int driveRobot = 0;

    //Components
    private XboxController ckController;
    private DriveTrain ckDrive;
    private PowerDistributionPanel ckPDP;
    private Compressor ckCompressor;
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
        ckPDP = new PowerDistributionPanel();
        ckCompressor = new Compressor();

        //Do this once To get in SmartDashbord
        SmartDashboard.putString(RMap.shootPosition, "START");

        //Auto Side
        sideChooser = new SendableChooser<>();
        sideChooser.addDefault("Middle", Position.MIDDLE);
        sideChooser.addObject("Left", Position.LEFT);
        sideChooser.addObject("Right", Position.RIGHT);
        SmartDashboard.putData("Auto Side", sideChooser);

        //Auto Mode
        autoChooser = new SendableChooser<>();
        autoChooser.addDefault("Do Nothing", Auto.NOTHING);
        autoChooser.addObject("Drive Forward", Auto.FORWARD);
        autoChooser.addObject("Switch or Forward", Auto.SWITCH);
        SmartDashboard.putData("Auto Mode", autoChooser);
    }

    @Override
    public void robotPeriodic() {
        gameData = DriverStation.getInstance().getGameSpecificMessage();//Gets the sides of the switches and scales.

        /////////////////////
        // Smart Dashboard //
        /////////////////////
        InArm.getInstance().smartDashboard();
        BMotor.getInstance().smartDashboard();
        SmartDashboard.putString(RMap.gameData, gameData);
    }

    @Override
    public void autonomousInit() {
        SmartDashboard.putString(RMap.robotMode, "Auto");

        Position autoSide = sideChooser.getSelected();
        Auto autoMode = autoChooser.getSelected();

        switch (autoMode) {
            case NOTHING:
                //DO NOTHING
                break;
            case FORWARD:
                new AutoDriveBackwardsStop().start();
                break;
            case SWITCH:
                if (autoSide == Position.LEFT && gameData.charAt(0) == 'L') {
                    new AutoDriveSwitch().start();
                } else if (autoSide == Position.RIGHT && gameData.charAt(0) == 'R') {
                    new AutoDriveSwitch().start();
                } else {
                    //Just drive forward then since its on the wrong side
                    new AutoDriveBackwardsStop().start();
                }
                break;
        }
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

        ///////////////
        // Arm Angle //
        ///////////////

        //Close Arms (Hold)
        if (ckController.getAButtonPressed()) {
            increaseAngle = new AngleIncreaseCommand();
            increaseAngle.start();
        } else if (ckController.getAButtonReleased()) {
            if (increaseAngle != null) {
                increaseAngle.cancel();
            }
        }

        //Open Arms (Hold)
        if (ckController.getXButtonPressed()) {
            decreaseAngle = new AngleDecreaseCommand();
            decreaseAngle.start();
        } else if (ckController.getXButtonReleased()) {
            if (decreaseAngle != null) {
                decreaseAngle.cancel();
            }
        }

        //Loading Position
        if (ckController.getBButtonPressed()) {
            new AngleSetCommand(RMap.intakeAngle).start();
        }

        //Free Arms
        if (ckController.getYButtonPressed()) {
            new AngleStopCommand().start();
        }

        //Reset Encoder
        if (ckController.getStartButtonPressed()) {
         //   InArm.getInstance().resetAngle();
        }

        /////////////////
        // Aim Shooter //
        /////////////////

        //Left Bumper Aim UP
        if (ckController.getBumperPressed(GenericHID.Hand.kLeft)) {
            BPiston.getInstance().nextPostion().start();
        }

        //Left Trigger Aim Down
        if (ckController.getTriggerAxis(GenericHID.Hand.kLeft) >= 0.5 && shooterReleased) {
            shooterReleased = false;
            BPiston.getInstance().prevPostion().start();
        }
        if (ckController.getTriggerAxis(GenericHID.Hand.kLeft) < 0.5) {
            //Release the shooter variable
            shooterReleased = true;
        }

        ///////////////////
        // Shoot Shooter //
        ///////////////////
        if (ckController.getBumperPressed(GenericHID.Hand.kRight)) {
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


//            driveRobot++;
//            if (driveRobot >= 2) {
//                driveRobot = 0;
//            }
        }
    }

    @Override
    public void disabledInit() {
        SmartDashboard.putString(RMap.robotMode, "Disabled");
    }


    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void testInit() {
        SmartDashboard.putString(RMap.robotMode, "Test");
        Scheduler.getInstance().removeAll(); //TODO Test that this clears them
    }

    @Override
    public void testPeriodic() {



        //Run Teleop Periodic?
        teleopPeriodic();
    }

}