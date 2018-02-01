package frc.team5689.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

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

    public Blower(){
        //Initialize Motors
        shootMotor1 = new VictorSPX(RobotMap.preshootLeft);
        shootMotor2 = new VictorSPX(RobotMap.preshootRight);
        shootMotor3 = new TalonSRX(RobotMap.shooterLeft);
        shootMotor4 = new TalonSRX(RobotMap.shooterRight);
        highPiston = new Solenoid(RobotMap.pcmShooterHigh);
        lowPiston = new Solenoid(RobotMap.pcmShooterLow);
    }

    public void Position(){
        switch(shooterPosition){
            case 1:

            case 2:

            case 3:

        }
    }

    public void FireSequence() {

    }
}
