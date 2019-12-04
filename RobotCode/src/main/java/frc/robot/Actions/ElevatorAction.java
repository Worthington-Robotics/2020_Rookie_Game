package frc.robot.Actions;

import frc.lib.statemachine.Action;
import frc.robot.subsystems.Elevator;

public class ElevatorAction extends Action {
    private boolean mup;

    public ElevatorAction(boolean up) {
        mup = up;
    }

    /**
     * code to run on action start
     */
    @Override
    public void onStart() {
        if (mup) {
            
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
        Elevator.getInstance().setdemand(0);
    }
}
