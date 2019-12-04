package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Door extends Subsystem{

    /**
     * Outputs all logging information to the SmartDashboard
     */
    @Override
    public void outputTelemetry() {

    }

    /**
     * Called to reset and configure the subsystem
     */
    @Override
    public void reset() {

    }

    public static Door getInstance() {return new Door}

    private DoubleSolenoid door;
private PeriodicIO pistonStuff;
    public Door() {
        door = new DoubleSolenoid(1, 2);
        pistonStuff = new PeriodicIO();
    }
    public void writePeriodicOutputs() {
        door.set(pistonStuff.demand);
    }

    public void pistonPosition(DoubleSolenoid.Value x) {
        pistonStuff.demand = x;
    }

    private class PeriodicIO {
        public DoubleSolenoid.Value demand = DoubleSolenoid.Value.kOff;
    }

}
