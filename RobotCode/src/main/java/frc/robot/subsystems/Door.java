package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Door extends Subsystem{
    
    private DoubleSolenoid door;
    private PeriodicIO periodicIO;
    public Door() {
        door = new DoubleSolenoid(1, 2);
        periodicIO = new PeriodicIO();
    }
    /**
     * Outputs all logging information to the SmartDashboard
     */
    @Override
    public void outputTelemetry() {

    }
    private static Door mDoor = new Door();
    public static Door getInstance(){
        return mDoor;
    }

    /**
     * Called to reset and configure the subsystem
     */
    @Override
    public void reset() {

    }

    

    public void setDoor(DoubleSolenoid.Value val){

    }

    public void writePeriodicOutputs() {
        door.set(periodicIO.demand);
    }
    private static class PeriodicIO {
        public DoubleSolenoid.Value demand = DoubleSolenoid.Value.kOff;
    }
}
