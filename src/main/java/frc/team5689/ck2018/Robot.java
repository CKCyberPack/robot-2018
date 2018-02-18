package frc.team5689.ck2018;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5689.ck2018.Commands.SetAngleCommand;
import frc.team5689.ck2018.Commands.StopAngleCommand;
import frc.team5689.ck2018.Subsystems.*;

import java.util.Set;

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
        ckController = new XboxController (0);
        //ckPDP = new PowerDistributionPanel();
        //ckDrive = DriveTrain.getInstance();
        //ckBMotor = BMotor.getInstance();
        //ckBPiston = BPiston.getInstance();
        ckInArm = InArm.getInstance();
        //ckInMotor = InMotor.getInstance();
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

        if (ckController.getAButtonPressed()){
            new SetAngleCommand().start();
        }

        if (ckController.getBButtonPressed()){
            new StopAngleCommand().start();
        }

        /////////////////
        // Drive Modes //
        /////////////////
        //System.out.println(driveRobot);
        switch (driveRobot) {
            case 0:                                     //forward - rotate  - strafe
                SmartDashboard.putString(RMap.driveMode, "Right Y - Right X - Left X");
          //      ckDrive.teleDriveCartesian(-ckController.getY(GenericHID.Hand.kRight), ckController.getX(GenericHID.Hand.kRight), ckController.getX(GenericHID.Hand.kLeft));
                break;
            case 1:
                SmartDashboard.putString(RMap.driveMode, "Right Y - Left X - Right X");
             //   ckDrive.teleDriveCartesian(-ckController.getY(GenericHID.Hand.kRight), ckController.getX(GenericHID.Hand.kLeft), ckController.getX(GenericHID.Hand.kRight));
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

//        //Putting the values
//        //TODO Delete
//        SmartDashboard.putNumber("kF", RMap.armKF);
//        SmartDashboard.putNumber("kP", RMap.armKP);
//        SmartDashboard.putNumber("kI", RMap.armKI);
//        SmartDashboard.putNumber("kD", RMap.armKD);
//
//        //intakeCommand = new SetAngleCommand();

    }

    @Override

    public void testPeriodic() {
        //Scheduler.getInstance().run(); //Scheduler won't run in test


//        if (ckController.getTriggerAxis(GenericHID.Hand.kRight) > 0){       // shoot
//
//            Thread x = new Thread(() -> {{//Might work?
//               // ckBMotor.setspeed(1);
//                try {
//                    ckBMotor.setspeed(1);
//                    Thread.sleep(1000);
//                    ckBPiston.shoot();
//                    Thread.sleep(1000);
//                    ckBPiston.setPosition(BPiston.Position.Flat);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            });
//            x.start();
//
//        }
//        if (ckController.getTriggerAxis(GenericHID.Hand.kLeft) > 0){        // intake
//            ckInMotor.setspeed(RMap.intakeSpeed);
//        }
//        if (ckController.getBumperPressed(GenericHID.Hand.kRight)){         // pos 1
//            ckBPiston.setPosition(BPiston.Position.Low);
//        }
//        if (ckController.getBumperPressed(GenericHID.Hand.kLeft)){          // pos 2
//            ckBPiston.setPosition(BPiston.Position.High);
//        }
//        if (ckController.getAButtonPressed()){                              // moving in
//            ckInArm.setLArmAngle(3200);         //TODO - Find Actual Angle
//            ckInArm.resetangle();
//        }
//        if (ckController.getAButton()) {
//            ckInArm.setAngle(0);
//        }
//        if (ckController.getBButton()) {
//            ckInArm.setAngle(RMap.intakeAngle);
//            System.out.print("Starting");
//        }
//        if (ckController.getStartButtonPressed())
//            ckInArm.resetangle();
//        if (ckController.getXButtonPressed()) {                  // reset back to encoder position 0 (spin backwards)
//            ckInArm.setAngle(RMap.intakeAngle -300);
//        }
//        if (ckController.getYButtonPressed()) {                  // stop
//            ckInArm.stopMotor();
//        }

    }

}