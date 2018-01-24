package frc.team5689.robot;

import edu.wpi.first.wpilibj.*;

public class Robot extends IterativeRobot {

    //Auto variables
    private final static String START_LEFT = "left";//Left side of the field
    private final static String START_RIGHT = "right";//Right side of the field
    private final static String START_MIDDLE = "mid";//Middle starting position
    private static String gameData;
    private static DriveTrain ckDrive;
    private static int driveMode;

    //Components
    private XboxController ckController;
    private PowerDistributionPanel ckPDP;

    //Variables
    private boolean overrideSafety = false;
    private boolean ledOn = false;//LED


    @Override
    public void robotInit() {
        ckController = new XboxController(0);
        ckPDP = new PowerDistributionPanel();
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void autonomousInit() {
        gameData = DriverStation.getInstance().getGameSpecificMessage();//Gets the sides of the switches and scales.

    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void testInit() {
    }


    @Override
    public void disabledPeriodic() {
        gameData = DriverStation.getInstance().getGameSpecificMessage();//Gets the sides of the switches and scales.


    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopPeriodic() {

        switch (driveMode) {
            case 0:
                System.out.print("Drive Mode 0");
                //Speed/Forward = Left stick up an down
                //Strafing = Triggers
                //Rotation =  Right stick
                ckDrive.teleDriveCartesian(ckController.getY(GenericHID.Hand.kLeft), ckController.getTriggerAxis(GenericHID.Hand.kRight) - ckController.getTriggerAxis(GenericHID.Hand.kLeft), ckController.getX(GenericHID.Hand.kRight));
            case 1:
                System.out.print("Drive Mode 1");
                //Speed/Forward = Left stick up an down
                //Strafing = Left Stick left and right
                //Rotation =  Right stick left and right
                ckDrive.teleDriveCartesian(ckController.getY(GenericHID.Hand.kLeft), ckController.getX(GenericHID.Hand.kLeft), ckController.getX(GenericHID.Hand.kRight));
            case 2:
                System.out.print("Drive Mode 2");
                //Speed/Forward = Left stick up and down
                //Strafing = Right Stick left and right
                //Rotation =  Left stick left and right
                ckDrive.teleDriveCartesian(ckController.getY(GenericHID.Hand.kLeft), ckController.getX(GenericHID.Hand.kRight), ckController.getX(GenericHID.Hand.kLeft));
            default:
                System.out.println("ERROR - No more drive modes");
        }
        if (ckController.getAButtonPressed()) {
            driveMode++;
            if (driveMode >= 3) {
                driveMode = 0;
            }
        }
    }

    @Override
    public void testPeriodic() {
    }
}