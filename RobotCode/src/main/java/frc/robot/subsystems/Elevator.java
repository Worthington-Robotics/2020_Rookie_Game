package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import frc.lib.loops.ILooper;
import frc.robot.Constants;

public class Elevator extends Subsystem {

    private PeriodicIO periodicIO;
    private Spark motor;

    private Elevator() {
        motor = new Spark(Constants.ELEVATOR);
        periodicIO = new PeriodicIO();
    }
    private static Elevator mElevator = new Elevator();
    @Override
    public void writePeriodicOutputs() {
        motor.set(periodicIO.motor_demand);
    }

    public void reset(){}

    public void setElevator(double demand){
        periodicIO.motor_demand = demand;
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

    public static class PeriodicIO {
        public double motor_demand = 0;
    }
}
