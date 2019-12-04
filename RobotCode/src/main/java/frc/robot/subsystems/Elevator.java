package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import frc.lib.loops.ILooper;
import frc.robot.Constants;

public class Elevator extends Subsystem {
    private static Elevator mElevator = new Elevator();
    private PeriodicIO periodicIO;
    private Spark elevator1;

    public Elevator() {
        elevator1 = new Spark(Constants.ELEVATOR_ID);
        periodicIO = new PeriodicIO();
    }

    @Override
    public void writePeriodicOutputs() {
        elevator1.set(periodicIO.elevator_demand);
    }


    public void setdemand(double w) {
        periodicIO.elevator_demand = w;
    }

    public static Elevator getInstance() {
        return mElevator;
    }

    /**
     * Outputs all logging information to the SmartDashboard
     */

    public void registerEnabledLoops(ILooper enabledLooper) {

    }

    @Override
    public void outputTelemetry() {

    }

    /**
     * Called to reset and configure the subsystem
     */
    @Override
    public void reset() {

    }

    private static class PeriodicIO {
        double elevator_demand = 0;
    }
}