package frc.robot.Actions;

import frc.lib.statemachine.Action;

public class ElevatorAction extends Action {
    /**
     * code to run on action start
     */
    @Override
    public void onStart() {
    }
private boolean up;

    /**
     * code to run while action loops
     * <p>approx every 20 miliseconds
     */
    @Override
    public void onLoop() {

    }
public ElevatorAction(boolean up){
    this.up = up;

}
    /**
     * method that tells the state machine the action is finished earlier than the scheduler
     *
     * @return true when action is ready to self terminate
     */
    @Override
    public boolean isFinished() {
        return false;
    }

    /**
     * code to run when the action has ben called by the state machine to stop
     */
    @Override
    public void onStop() {

    }
}
