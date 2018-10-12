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
        SWITCH,
        TURNLEFT,
        TURNRIGHT,
        STRAFEMID,
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


        /*Auto Mode Descriptions
        Drive Forward - Only drives forward and does not release the cube
        Switch or Forward - If the robot is placed on the correct side of the switch, it will release the cube, if not then it only drives forward
        SWITCH RIGHT - If the switch is on the right, the robot will drive forward and turn to the left to deploy cube
        SWITCH LEFT - If the switch is on the left, the robot will drive forward and turn to the right to deploy cube
        STRAFE - The robot starts in the middle, and will strafe either left or right depending on the side of the switch to deploy the cube
         */

        //Auto Mode Chooosers
        autoChooser = new SendableChooser<>();
        autoChooser.addDefault("Drive Forward", Auto.FORWARD);//Only drives forward
        autoChooser.addObject("Switch or Forward", Auto.SWITCH);
        autoChooser.addObject("SWITCH RIGHT", Auto.TURNRIGHT);//on right
        autoChooser.addObject("SWITCH LEFT", Auto.TURNLEFT);//on left
        autoChooser.addObject("STRAFE", Auto.STRAFEMID);
        autoChooser.addObject("Do Nothing", Auto.NOTHING);

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
                    //System.out.println("LEFT SIDE AND THE GAMEDATA IS LEFT, SHOULD BE SHOOTING");//Outputs what the robot SHOULD be doing
                } else if (autoSide == Position.RIGHT && gameData.charAt(0) == 'R') {
                    new AutoDriveSwitch().start();
                    //System.out.println("RIGHT SIDE AND THE GAMEDATA IS RIGHT, SHOULD BE SHOOTING");//Outputs what the robot SHOULD be doing
                } else {
                    //Just drive forward then since its on the wrong side
                    new AutoDriveBackwardsStop().start();
                    //System.out.println("SWITCH failed or you selected the wrong one");//Console output when the command fails
                }
                break;
            case TURNLEFT:
                if (autoSide == Position.LEFT && gameData.charAt(0) == 'L') {
                    new AutoDriveTurnSwitchLeft().start();//TODO LEFT/RIGHT
                    // System.out.println("LEFT SIDE AND THE GAMEDATA IS LEFT, SHOULD BE TURNING AND SHOOTING");//Outputs what the robot SHOULD be doing
                } else {
                    //Just drive forward then since its on the wrong side
                    new AutoDriveBackwardsStop().start();
                    // System.out.println("TURNLEFT failed or you selected the wrong one");//Console output when the command fails
                }
                break;
            case TURNRIGHT:
                if (autoSide == Position.RIGHT && gameData.charAt(0) == 'R') {
                    new AutoDriveTurnSwitchRight().start();//TODO LEFT/RIGHTv
                    //System.out.println("RIGHT SIDE AND THE GAMEDATA IS RIGHT, SHOULD BE TURNING AND SHOOTING");//Outputs what the robot SHOULD be doing
                } else {
                    //Just drive forward then since its on the wrong side
                    new AutoDriveBackwardsStop().start();
                    // System.out.println("TURNRIGHT failed or you selected the wrong one");//Console output when the command fails
                }
                break;
            case STRAFEMID:
                if (autoSide == Position.MIDDLE && gameData.charAt(0) == 'L') {
                    new AutoDriveStrafeLeft().start();
                } else if (autoSide == Position.MIDDLE && gameData.charAt(0) == 'R') {
                    new AutoDriveStrafeRight().start();
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
        //This should clear them, don't think we had an issue, will consult with Brian to see if this TO-DO can be removed.
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();

        ///////////////
        // Arm Angle //
        ///////////////

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

        //Loading Position(180 Degrees)
        if (ckController2.getBButtonPressed()) {
            new AngleSetCommand(RMap.intakeAngle).start();
        }

        //Free Arms/Wobbly Arms
        if (ckController2.getYButtonPressed()) {
            new AngleStopCommand().start();
        }
        //Send the arms back to the HOME positon
        if (ckController2.getBackButtonPressed()) {
            new AngleSetCommand(RMap.intakeReset).start();
        }

        //Reset Encoder
        if (ckController2.getStartButtonPressed()) {
            //  InArm.getInstance().resetAngle();
            new PortalCommandGroup().start();
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
        if (ckController1.getYButtonPressed()) {
            new ChrisShootCommandGroup().start();
        }
        ////////////
        // Intake //
        ////////////
        InMotor.getInstance().setspeed(ckController2.getTriggerAxis(GenericHID.Hand.kRight));

        /////////////////
        // Drive Modes //
        /////////////////
        switch (driveRobot) {
            case 0:                                     //forward - rotate  - strafe
                SmartDashboard.putString(RMap.driveMode, "Right Y - Right X - Left X");
                ckDrive.teleDriveCartesian(-ckController1.getY(GenericHID.Hand.kLeft), ckController1.getX(GenericHID.Hand.kRight), ckController1.getX(GenericHID.Hand.kLeft));
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
        SmartDashboard.putString(RMap.robotMode, "Disabled");//Disabled State for robot
    }


    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void testInit() {
        SmartDashboard.putString(RMap.robotMode, "Test");//Testing initialization
    }

    @Override
    public void testPeriodic() {
        //Scheduler.getInstance().run(); //Scheduler won't run in test
    }

}