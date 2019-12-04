package frc.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.lib.statemachine.Action;
import frc.robot.Actions.ManipulatorAction;
import frc.robot.subsystems.Manipulator;


public class OI {
    public OI() {
        JoystickButton button_one = new JoystickButton(Constants.MASTER, 1);
        JoystickButton button_three = new JoystickButton(Constants.MASTER, 3);
        JoystickButton button_four = new JoystickButton(Constants.MASTER, 4);
        button_one.whileHeld(Action.toCommand(new ManipulatorAction()));
    }
}
