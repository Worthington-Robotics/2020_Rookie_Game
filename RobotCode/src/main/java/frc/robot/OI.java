package frc.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.lib.statemachine.Action;
import frc.robot.Actions.ManipulatorAction;

public class OI {
    public OI() {
        JoystickButton greyButtonOne = new JoystickButton(Constants.MASTER, 1);
        JoystickButton greyButtonThree = new JoystickButton(Constants.MASTER, 3);
        JoystickButton greyButtonFour = new JoystickButton(Constants.MASTER, 4);
        JoystickButton greyButtonFive = new JoystickButton(Constants.MASTER, 5);
    greyButtonOne.whileHeld(Action.toCommand(new ManipulatorAction()));
    }


}

