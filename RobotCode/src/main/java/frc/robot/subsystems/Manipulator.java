package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import frc.lib.loops.ILooper;
import frc.robot.Constants;

public class Manipulator extends Subsystem {

    private PeriodicIO periodicIO;
    private Spark motor;

    private Manipulator() {
        motor = new Spark(Constants.MANIPULATOR);
        periodicIO = new PeriodicIO();
    }
    private static Manipulator mManip = new Manipulator();
    @Override
    public void writePeriodicOutputs() {
        motor.set(periodicIO.motor_demand);
    }

    public void reset(){}
    public void setManip(double demand){
        periodicIO.motor_demand = demand;
    }
    public static Manipulator getInstance() {
        return mManip;
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
