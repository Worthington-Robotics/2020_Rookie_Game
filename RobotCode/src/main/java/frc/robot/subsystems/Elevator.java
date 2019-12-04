package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import frc.lib.loops.ILooper;
import frc.robot.Constants;

public class Elevator extends Subsystem {

    private static Elevator
    Elevator = new Elevator();

    private Elevator.PeriodicIO periodicIO;
    private Spark motor;

    public Elevator() {
        motor = new Spark(Constants.MOTOR_ID);
        periodicIO = new Elevator.PeriodicIO();
    }

    @Override
    public void writePeriodicOutputs() {
        motor.set(periodicIO.motor_demand);
    }


    public void setDemand(double w){
        periodicIO.motor_demand = w;
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

    private static class PeriodicIO {
        double motor_demand = 0;
    }

    public void onStop(){
        Elevator.getInstance().setDemand(0);
    }

}