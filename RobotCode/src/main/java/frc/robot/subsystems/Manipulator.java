package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import frc.lib.loops.ILooper;
import frc.robot.Constants;

public class Manipulator extends Subsystem {

    private PeriodicIO periodicIO;
    private Spark motor;

    public Manipulator() {
        motor = new Spark(Constants.MOTOR_ID);
        periodicIO = new PeriodicIO();
    }

    @Override
    public void writePeriodicOutputs() {
        motor.set(periodicIO.motor_demand);
    }

    public void reset(){}

    public void something(double w){
        periodicIO.motor_demand = w;
    }
    public static Manipulator getInstance() {
        return null;
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
