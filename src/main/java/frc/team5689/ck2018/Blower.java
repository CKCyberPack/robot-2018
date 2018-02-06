package frc.team5689.ck2018;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Blower Class
public class Blower {
    private VictorSPX shootMotor1;
    private VictorSPX shootMotor2;
    private TalonSRX shootMotor3;
    private TalonSRX shootMotor4;
    private DoubleSolenoid launcherPiston;
    private Solenoid highPiston;
    private Solenoid lowPiston;
    private int shooterPosition;
    private Thread shootThread;

    public TalonSRX smt;

    public Blower() {
        //Initialize Motors
    /*    shootMotor1 = new VictorSPX(RobotMap.preshootLeft);
        shootMotor2 = new VictorSPX(RobotMap.preshootRight);
        shootMotor3 = new TalonSRX(RobotMap.shooterLeft);
        shootMotor4 = new TalonSRX(RobotMap.shooterRight);
        */
/*        highPiston = new Solenoid(RobotMap.pcmShooterHigh);
        lowPiston = new Solenoid(RobotMap.pcmShooterLow);*/


        //Shooter Test Motor
        smt = new TalonSRX(RobotMap.shooterLeft);
        smt.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        smt.setSensorPhase(true);
        smt.configNominalOutputForward(0, 10);
        smt.configNominalOutputReverse(0, 10);
        smt.configPeakOutputForward(0.5, 10);
        smt.configPeakOutputReverse(-0.5, 10);

        //.putData("Test Motor", shootMotorTest.get);
    }

/*    public void Position() {
        switch (shooterPosition) {
            case 1:
                highPiston.set(false);
                lowPiston.set(false);
            case 2:
                lowPiston.set(true);
                highPiston.set(false);
            case 3:
                highPiston.set(true);
                lowPiston.set(true);
        }
    }*/

    public void ShootHigh () {
        highPiston.set(true);
        lowPiston.set(true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Shoot();
    }

    public void ShootLow () {
        lowPiston.set(true);
        highPiston.set(false);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Shoot();
    }

    private void Shoot() {
        shootThread = new Thread();
        try {
            shootMotor1.set(ControlMode.PercentOutput,1);
            shootMotor2.set(ControlMode.PercentOutput,1);
            shootMotor3.set(ControlMode.PercentOutput,1);
            shootMotor4.set(ControlMode.PercentOutput,1);
            Thread.sleep(1000);
            launcherPiston.set(RobotMap.pistonLaunch);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}