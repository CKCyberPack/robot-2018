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
    private XboxController ckController1;
    private XboxController ckController2;
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
        ckController1 = new XboxController(0);
        ckController2 = new XboxController(1);
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

        //Checks to see if we have all of the field data available
        do{
          //  if (DriverStation.getInstance().getGameSpecificMessage()!=null) {
                gameData = DriverStation.getInstance().getGameSpecificMessage();
          //  }
        }while(gameData.length() != 3);

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

        //"Player 2" - Shooting, loading and more.
        //Close Arms (Hold)
        if (ckController2.getAButtonPressed()) {
            increaseAngle = new AngleIncreaseCommand();
            increaseAngle.start();
        } else if (ckController2.getAButtonReleased()) {
            if (increaseAngle != null) {
                increaseAngle.cancel();
            }
        }

        //Open Arms (Hold)
        if (ckController2.getXButtonPressed()) {
            decreaseAngle = new AngleDecreaseCommand();
            decreaseAngle.start();
        } else if (ckController2.getXButtonReleased()) {
            if (decreaseAngle != null) {
                decreaseAngle.cancel();
            }
        }

        //Loading Position
        if (ckController2.getBButtonPressed()) {
            new AngleSetCommand(RMap.intakeAngle).start();
        }

        //Free Arms
        if (ckController2.getYButtonPressed()) {
            new AngleStopCommand().start();
        }

        //Reset Encoder
        if (ckController2.getStartButtonPressed()) {
            InArm.getInstance().resetAngle();
        }

        /////////////////
        // Aim Shooter //
        /////////////////

        //Left Bumper Aim UP
        if (ckController2.getBumperPressed(GenericHID.Hand.kLeft)) {
            BPiston.getInstance().nextPostion().start();
        }

        //Left Trigger Aim Down
        if (ckController2.getTriggerAxis(GenericHID.Hand.kLeft) >= 0.5 && shooterReleased) {
            shooterReleased = false;
            BPiston.getInstance().prevPostion().start();
        }
        if (ckController2.getTriggerAxis(GenericHID.Hand.kLeft) < 0.5) {
            //Release the shooter variable
            shooterReleased = true;
        }

        ///////////////////
        // Shoot Shooter //
        ///////////////////
        if (ckController2.getBumperPressed(GenericHID.Hand.kRight)) {
            new ShootCommandGroup().start();
        }

        ////////////
        // Intake //
        ////////////
        InMotor.getInstance().setspeed(ckController2.getTriggerAxis(GenericHID.Hand.kRight));

        /////////////////
        // Drive Modes //
        /////////////////
        //"Player 1" - Driving
        switch (driveRobot) {
            case 0:                                     //forward - rotate  - strafe
                SmartDashboard.putString(RMap.driveMode, "Right Y - Right X - Left X");
                ckDrive.teleDriveCartesian(-ckController1.getY(GenericHID.Hand.kRight), ckController1.getX(GenericHID.Hand.kRight), ckController1.getX(GenericHID.Hand.kLeft));
                break;
            case 1:
                SmartDashboard.putString(RMap.driveMode, "Right Y - Left X - Right X");
                ckDrive.teleDriveCartesian(-ckController1.getY(GenericHID.Hand.kRight), ckController1.getX(GenericHID.Hand.kLeft), ckController1.getX(GenericHID.Hand.kRight));
                break;
            default:
                System.out.println("ERROR - No more drive modes");

        }
        if (ckController1.getBackButtonPressed()) {//Switches between drive moves
            driveRobot++;
            if (driveRobot >= 2) {
                driveRobot = 0;
            }
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
    }

    @Override
    public void testPeriodic() {
        //Scheduler.getInstance().run(); //Scheduler won't run in test
    }

}