package frc.team5689.ck2018;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5689.ck2018.Subsystems.ExampleSubsystem;

public class Robot extends IterativeRobot {
    //Subsystems
    public static ExampleSubsystem exampleSubsystem;


    //Auto variables
    private final static String START_LEFT = "left";//Left side of the field
    private final static String START_RIGHT = "right";//Right side of the field
    private final static String START_MIDDLE = "mid";//Middle starting position
    private String gameData;
    private DriveTrain ckDrive;
    private int driveRobot = 0;
    private Blower ckBlower;

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
        ckDrive = new DriveTrain();
        ckBlower = new Blower();


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
    public void disabledPeriodic() {}

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

        System.out.println(driveRobot);
        switch (driveRobot) {
            case 0:
                SmartDashboard.putString(RMap.driveMode, "Right Y - Right X - Left X");
                ckDrive.teleDriveCartesian(-ckController.getY(GenericHID.Hand.kRight), ckController.getX(GenericHID.Hand.kRight), ckController.getX(GenericHID.Hand.kLeft));
                break;
            case 1:
                //TODO - Replace this with Right Y - Left X - Right X - Don't forget negative
                SmartDashboard.putString(RMap.driveMode, "Left Y - Right X - Trigger");
                ckDrive.teleDriveCartesian(-ckController.getY(GenericHID.Hand.kLeft), ckController.getX(GenericHID.Hand.kRight), ckController.getTriggerAxis(GenericHID.Hand.kRight) - ckController.getTriggerAxis(GenericHID.Hand.kLeft));
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
        ckBlower.smt.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public void testPeriodic() {
        SmartDashboard.putNumber("POS",   ckBlower.smt.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("VEL",    ckBlower.smt.getSelectedSensorVelocity(0));
        SmartDashboard.putNumber("POW", ckBlower.smt.getMotorOutputPercent());
        ckBlower.smt.config_kF(0, SmartDashboard.getNumber("kF",0.01), 10);
        ckBlower.smt.config_kP(0, SmartDashboard.getNumber("kP",0.05), 10);
        ckBlower.smt.config_kI(0, SmartDashboard.getNumber("kI",0.0005), 10);
        ckBlower.smt.config_kD(0, SmartDashboard.getNumber("kD",0.0001), 10);

        if (ckController.getAButtonPressed()){
            ckBlower.smt.set(ControlMode.PercentOutput, -0.1);
        }

        if (ckController.getYButtonPressed()){
            ckBlower.smt.set(ControlMode.PercentOutput, 0);
        }

        if (ckController.getStartButtonPressed()){
            ckBlower.smt.setSelectedSensorPosition(0,0,10);
        }

        if (ckController.getXButtonPressed()){
            ckBlower.smt.set(ControlMode.Position, 0);
        }

        if (ckController.getBButtonPressed()){
            //ckBlower.shootMotorTest.chan
            ckBlower.smt.set(ControlMode.Velocity , 2000);
        }

    }

}