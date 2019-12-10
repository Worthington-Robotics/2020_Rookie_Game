package frc.robot.AutoActionGroup;

import frc.lib.statemachine.StateMachineDescriptor;
import frc.robot.Actions.DoorAction;
import frc.robot.Actions.ElevatorAction;

public class FarRocketAuto extends StateMachineDescriptor {
    public FarRocketAuto() {
        addSequential(new ElevatorAction(true), 1000);
        addSequential(new DoorAction(), 2500);
    }
}
(173, 0) (123.5+173, 132)
