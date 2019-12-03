package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.lib.loops.ILooper;
import frc.robot.Constants;

public class Door extends Subsystem {

    private PeriodicIO periodicIO;
    private DoubleSolenoid solenoid;

    public Door() {
        solenoid = new DoubleSolenoid(Constants.CLIMB_FRONT_LOW_ID, Constants.CLIMB_FRONT_HIGH_ID);
        periodicIO = new PeriodicIO();
    }

    @Override
    public void writePeriodicOutputs() {
        solenoid.set(periodicIO.demand);
    }

    public void something(DoubleSolenoid.Value w){
        periodicIO.demand= w;
    }
    public static Door getInstance() {
        return new Door();
    }
/**
 * Outputs all logging information to the SmartDashboard
 */

private static class PeriodicIO {
    public DoubleSolenoid.Value demand = DoubleSolenoid.Value.kOff;
}

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

}