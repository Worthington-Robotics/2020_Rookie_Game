package frc.robot.autoactiongroups;

import frc.lib.statemachine.StateMachineDescriptor;
import frc.robot.Actions.DriveTra;
import frc.robot.actions.DoorActions;
import frc.robot.actions.ElevatorAction;
import frc.robot.planners.DriveTrajectoryGenerator;

public class starttorocket extends StateMachineDescriptor {
    public starttorocket() {
        addSequential( new DriveTra(DriveTrajectoryGenerator.getInstance().elevator()), 25);
        addSequential( new ElevatorAction(false), 1000);
        addSequential( new DoorActions(), 14000);
    }
}
