package frc.robot.Actions;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.lib.statemachine.Action;
import frc.robot.subsystems.Door;

public class DoorActions extends Action {

    @Override
    public void onStart() {
        Door.getInstance().something(DoubleSolenoid.Value.kForward);
    }

    @Override
    public void onLoop() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void onStop() {
        Door.getInstance().something(DoubleSolenoid.Value.kReverse);
    }
}
