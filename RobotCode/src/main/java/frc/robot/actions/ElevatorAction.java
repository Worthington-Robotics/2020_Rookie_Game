package frc.robot.actions;

import frc.lib.statemachine.Action;
import frc.robot.subsystems.Elevator;

public class ElevatorAction extends Action {
    private boolean down = false;

    public ElevatorAction(boolean down) {
        this.down = down;
    }
    /**
     * code to run on action starte
     */
    @Override
    public void onStart() {
        if (down) {
            Elevator.getInstance().setElevator(-.5);
        } else {
            Elevator.getInstance().setElevator(.5);
        }
    }

    /**
     * code to run while action loops
     * <p>approx every 20 miliseconds
     */
    @Override
    public void onLoop() {

    }

    /**
     * method that tells the state machine the action is finished earlier than the scheduler
     *
     * @return true when action is ready to self terminate
     */
    @Override
    public boolean isFinished() {
        return true;
    }

    /**
     * code to run when the action has ben called by the state machine to stop
     */
    @Override
    public void onStop() {
        Elevator.getInstance().setElevator(0);
    }
}
