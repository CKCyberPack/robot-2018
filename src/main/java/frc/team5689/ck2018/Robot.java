package frc.team5689.ck2018;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team5689.ck2018.Subsystems.BMotor;
import frc.team5689.ck2018.Subsystems.BPiston;
import frc.team5689.ck2018.Subsystems.DriveTrain;
import frc.team5689.ck2018.Subsystems.ExampleSubsystem;

public class Robot extends IterativeRobot {
    //Subsystems
    public static ExampleSubsystem exampleSubsystem;
    public static DriveTrain ckDrive;
    public static BMotor ckBMotor;
    public static BPiston ckBPiston;


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
        ckBMotor.smt.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public void testPeriodic() {
        SmartDashboard.putNumber("POS",   ckBMotor.smt.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("VEL",    ckBMotor.smt.getSelectedSensorVelocity(0));
        SmartDashboard.putNumber("POW", ckBMotor.smt.getMotorOutputPercent());
        ckBMotor.smt.config_kF(0, SmartDashboard.getNumber("kF",0.01), 10);
        ckBMotor.smt.config_kP(0, SmartDashboard.getNumber("kP",0.05), 10);
        ckBMotor.smt.config_kI(0, SmartDashboard.getNumber("kI",0.0005), 10);
        ckBMotor.smt.config_kD(0, SmartDashboard.getNumber("kD",0.0001), 10);

        if (ckController.getAButtonPressed()){
            ckBMotor.smt.set(ControlMode.PercentOutput, -0.1);
        }

        if (ckController.getYButtonPressed()){
            ckBMotor.smt.set(ControlMode.PercentOutput, 0);
        }

        if (ckController.getStartButtonPressed()){
            ckBMotor.smt.setSelectedSensorPosition(0,0,10);
        }

        if (ckController.getXButtonPressed()){
            ckBMotor.smt.set(ControlMode.Position, 0);
        }

        if (ckController.getBButtonPressed()){
            //ckBMotor.shootMotorTest.chan
            ckBMotor.smt.set(ControlMode.Velocity , 2000);
        }

    }

}