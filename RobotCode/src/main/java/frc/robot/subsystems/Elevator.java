package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import frc.lib.loops.ILooper;
import frc.robot.Constants;

public class Elevator extends Subsystem{
private static Elevator up_down_thing = new Elevator();
    private PeriodicIO periodicIO;
    private Spark elevator;

    public Elevator() {
        elevator = new Spark(Constants.MOTOR_ID);
        periodicIO = new Elevator.PeriodicIO();
    }

    @Override
    public void writePeriodicOutputs() {
        elevator.set(periodicIO.elevator_demand);
    }


    public void set_demand(double w){
        periodicIO.elevator_demand = w;
    }
    public static Elevator getInstance() {
        return new Elevator();
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

    public void onStop() {
        Elevator.getInstance().set_demand(0);
    }

    private static class PeriodicIO {
        double elevator_demand = 0;
    }
}

