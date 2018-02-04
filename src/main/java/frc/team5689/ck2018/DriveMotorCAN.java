package frc.team5689.ck2018;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.PWMSpeedController;

public class DriveMotorCAN extends VictorSPX implements PWMSpeedController {
    private boolean motorisInverted = false;

    public DriveMotorCAN(int deviceNumber) {//This was imported by default
        super(deviceNumber);

    }


    //The following is from the PWMSpeedController class
    @Override
    public void set(double speed) {
        setSpeed(motorisInverted ? -speed : speed);
        feed();
    }

    @Override
    public double get() {
        return getSpeed();
    }

    @Override
    public void setInverted(boolean isInverted) {
        motorisInverted = isInverted;
    }

    @Override
    public boolean getInverted() {
        motorisInverted = true;
        return motorisInverted;
    }

    @Override
    public void pidWrite(double output) {
        set(output);
    }
}

