package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import frc.lib.loops.ILooper;
import frc.robot.Constants;

public class Elevator extends Subsystem {

    private PeriodicIO periodicIO;
    private Spark motor;

    private static Elevator mElevator = new Elevator();

    private Elevator() {
        motor = new Spark(Constants.ELEVATOR);
        periodicIO = new PeriodicIO();
    }
    @Override
    public void writePeriodicOutputs() {
        motor.set(periodicIO.motorDemand);
    }

    public void reset(){
        periodicIO.motorDemand = 0;
    }

    public void setElevator(double demand){
        periodicIO.motorDemand = demand;
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

    private static class PeriodicIO {
        public double motorDemand = 0;
    }
}
