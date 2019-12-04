package frc.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.lib.statemachine.Action;
import frc.robot.Actions.ManipulatorAction;


public class OI {
    public OI() {
    JoystickButton x = new JoystickButton(Constants.MASTER, 1);
    JoystickButton y = new JoystickButton(Constants.MASTER, 3);
    JoystickButton z = new JoystickButton(Constants.MASTER, 4);
    x.whileHeld(Action.toCommand(new ManipulatorAction()));
    }
}