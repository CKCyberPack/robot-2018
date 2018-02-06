package frc.team5689.ck2018;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Solenoid;

public class Sucker {
    private VictorSPX suckMotorLeft;
    private VictorSPX suckMotorRight;
    private Solenoid leftPiston;
    private Solenoid rightPiston;

    public void Sucker(){
        //Initialize motors and pistons
        suckMotorLeft = new VictorSPX(RMap.suckerLeft);
        suckMotorRight = new VictorSPX(RMap.suckerRight);
        leftPiston = new Solenoid(RMap.pcmSuckerLeft);
        rightPiston = new Solenoid(RMap.pcmSuckerRight);
    }

    public void open(){
        //Opens the pistons
        leftPiston.set(true);
        rightPiston.set(true);
    }

    public void close(){
        //Closes the pistons
        leftPiston.set(false);
        rightPiston.set(false);
    }

    public void suck (double speed){
        //Sets the motors' speeds
        suckMotorLeft.set(ControlMode.PercentOutput, 0.5);
        suckMotorRight.set(ControlMode.PercentOutput, 0.5);
    }
}