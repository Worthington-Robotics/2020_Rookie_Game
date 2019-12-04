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


    public void set_demand(double w){
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

    private Spark manipulator;
    private PeriodicIO periodic1;

    public Manipulator() {
        manipulator = new Spark(Constants.MANIPULATOR_ID_1);
        periodic1 = new PeriodicIO();
    }

    /**
     * Outputs all logging information to the SmartDashboard
     */
    @Override
    public void outputTelemetry() {

    }


    public void writePeriodicOutputs() {
        manipulator.set(periodic1.manipulator_demand);
    }

    public void setDemand(double demand1) {
        periodic1.manipulator_demand = demand1;
    }
    public static Manipulator getInstance(){
        return new Manipulator();
    }
    /**
     * Called to reset and configure the subsystem
     */
    @Override
    public void reset() {

    }

    private static class PeriodicIO {
        double motor_demand = 0;
    public void registerEnabledLoops(ILooper enabledLooper) {

    }

    public static class PeriodicIO {
        public double manipulator_demand = 0;
    }
}
