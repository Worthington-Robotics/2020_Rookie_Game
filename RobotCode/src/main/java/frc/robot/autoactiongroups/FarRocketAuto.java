package frc.robot.autoactiongroups;

import frc.lib.statemachine.StateMachineDescriptor;
import frc.robot.Actions.DoorActions;
import frc.robot.Actions.DriveTra;
import frc.robot.Actions.ElevatorAction;
import frc.robot.Actions.ManipulatorAction;
import frc.robot.planners.DriveTrajectoryGenerator;

public class FarRocketAuto extends StateMachineDescriptor {
    public FarRocketAuto() {
        addSequential(new DriveTra(DriveTrajectoryGenerator.getInstance().StartToRocket()), 30);
        addSequential(new ElevatorAction(true), 1000);
        addSequential(new DoorActions(), 2000);
    }
}

//(173, 0) (0, 0) (296, 132)