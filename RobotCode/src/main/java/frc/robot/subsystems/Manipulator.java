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


    public void something(double w){
        periodicIO.motor_demand = w;
    }
    public static Manipulator getInstance() {
        return new Manipulator();
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
        double motor_demand = 0;
    }
}
