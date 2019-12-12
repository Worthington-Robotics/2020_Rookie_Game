package frc.robot.actions;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.lib.statemachine.Action;
import frc.robot.subsystems.Door;

public class DoorActions extends Action {

    @Override
    public void onStart() {
        Door.getInstance().setDoor(DoubleSolenoid.Value.kForward);
    }

    @Override
    public void onLoop() {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void onStop() {
        Door.getInstance().setDoor(DoubleSolenoid.Value.kReverse);
    }
}
