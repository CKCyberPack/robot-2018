package frc.team5689.ck2018;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI extends XboxController {

    public JoystickButton bumperLeft;
    public JoystickButton bumperRight;
    public JoystickButton stickLeft;
    public JoystickButton stickRight;
    public JoystickButton a;
    public JoystickButton b;
    public JoystickButton x;
    public JoystickButton y;
    public JoystickButton start;
    public JoystickButton back;


    private enum Button {
        kBumperLeft(5),
        kBumperRight(6),
        kStickLeft(9),
        kStickRight(10),
        kA(1),
        kB(2),
        kX(3),
        kY(4),
        kBack(7),
        kStart(8);

        @SuppressWarnings("MemberName")
        private int value;

        Button(int value) {
            this.value = value;
        }
    }


    /**
     * Construct an instance of a joystick. The joystick index is the USB port on the drivers
     * station.
     *
     * @param port The port on the Driver Station that the joystick is plugged into.
     */
    public OI(int port) {
        super(port);

         bumperLeft = new JoystickButton(this, Button.kBumperLeft.value);
          bumperRight= new JoystickButton(this, Button.kBumperRight.value);
          stickLeft= new JoystickButton(this, Button.kStickLeft.value);
          stickRight= new JoystickButton(this, Button.kStickRight.value);
          a= new JoystickButton(this, Button.kA.value);
          b= new JoystickButton(this, Button.kB.value);
          x= new JoystickButton(this, Button.kX.value);
          y= new JoystickButton(this, Button.kY.value);
          start= new JoystickButton(this, Button.kStart.value);
          back= new JoystickButton(this, Button.kBack.value);

    }




}
