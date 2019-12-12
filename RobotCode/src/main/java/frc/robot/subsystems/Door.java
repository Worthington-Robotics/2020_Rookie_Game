package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Door extends Subsystem{

    /**
     * Outputs all logging information to the SmartDashboard
     */
    @Override
    public void outputTelemetry() {

    }
    public static Door getInstance(){return null;}

    /**
     * Called to reset and configure the subsystem
     */
    @Override
    public void reset() {

    }

    private DoubleSolenoid door;

    public Door() {
        door = new DoubleSolenoid(1, 2);
    }
    public void writePeriodicOutputs() {
        //door.set();
    }
    private static class PeriodicIO {
        public DoubleSolenoid.Value demand = DoubleSolenoid.Value.kOff;
    }
}
