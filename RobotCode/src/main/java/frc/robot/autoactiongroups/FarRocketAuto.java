package frc.robot.autoactiongroups;

import frc.lib.statemachine.StateMachineDescriptor;
import frc.robot.Actions.DoorActions;
import frc.robot.Actions.ElevatorAction;
import frc.robot.Actions.ManipulatorAction;

public class FarRocketAuto extends StateMachineDescriptor {
    public FarRocketAuto() {
    addSequential(new ElevatorAction(true),1000);
    addSequential(new DoorActions(), 2000);
    }
}

/**(173,0) (0, 0) (296,132)*/