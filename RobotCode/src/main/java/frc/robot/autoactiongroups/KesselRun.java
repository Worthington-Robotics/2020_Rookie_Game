package frc.robot.autoactiongroups;

import frc.lib.statemachine.StateMachineDescriptor;
import frc.robot.actions.DoorActions;
import frc.robot.actions.DriveTra;
import frc.robot.actions.ElevatorAction;
import frc.robot.planners.DriveTrajectoryGenerator;

public class KesselRun extends StateMachineDescriptor {
    public KesselRun () {
        addSequential(new DriveTra(DriveTrajectoryGenerator.getInstance().routeKesselRun(), false), 30);
        addSequential(new ElevatorAction(false), 1000);
        addSequential(new DoorActions(), 22000);
    }
}
