package frc.team5689.ck2018;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.SpeedController;

public class DriveVictorSPX extends VictorSPX implements SpeedController {
    /**
     * Constructor
     *
     * @param deviceNumber [0,62]
     */
    public DriveVictorSPX(int deviceNumber) {
        super(deviceNumber);
    }

    /**
     * Set the Speed value assuming basic PWM Inputs.
     * <p>
     * <p>The PWM value is set using a range of -1.0 to 1.0, appropriately scaling the value for the
     * FPGA.
     *
     * @param speed The speed value between -1.0 and 1.0 to set.
     */
    @Override
    public void set(double speed) {
        super.set(ControlMode.PercentOutput, speed);
    }

    /**
     * Get the recently set value of the PWM.
     *
     * @return The most recently set value for the PWM between -1.0 and 1.0.
     */
    @Override
    public double get() {
        return super.getMotorOutputPercent();
    }

    /**
     * Disable the speed controller.
     */
    @Override
    public void disable() {
        super.set(ControlMode.Disabled, 0); //Speed is never read
    }

    /**
     * Stops motor movement. Motor can be moved again by calling set without having to re-enable the
     * motor.
     */
    @Override
    public void stopMotor() {
        disable();
    }

    /**
     * Write out the PID value as seen in the PIDOutput base object.
     *
     * @param output Write out the PWM value as was found in the PIDController
     */
    @Override
    public void pidWrite(double output) {
        set(output);
    }
}
