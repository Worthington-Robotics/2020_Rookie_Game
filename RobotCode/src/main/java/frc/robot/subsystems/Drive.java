package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.lib.loops.Loop;
import frc.lib.util.DriveSignal;

public class Drive extends Subsystem {

    private TalonSRX x, y;
    private VictorSPX a, b, c, d;
    private DriveControlState mDriveControlState;
    private PeriodicIO periodic = new PeriodicIO();
    private final Loop mLoop = new Loop(){
        /**
         * what the loop runs when started by the subsystem manager
         *
         * @param timestamp handled by subsystem manager
         */
        public void onStart(double timestamp) {

        }

        /**
         * what the loop runs while run by the subsystem manager
         *
         * @param timestamp handled by subsystem manager
         */
        public void onLoop(double timestamp) {

        }

        /**
         * what the loop runs when ended by the subsystem manager
         *
         * @param timestamp handled by subsystem manager
         */
        public void onStop(double timestamp) {

        }
    };
}
    public Drive() {
        x = new TalonSRX(1);
        y = new TalonSRX(2);
        a = new VictorSPX(3);
        b = new VictorSPX(4);
        c = new VictorSPX(5);
        d = new VictorSPX(6);
    }

    public synchronized void setOpenLoop(DriveSignal signal) {
        if (mDriveControlState != DriveControlState.OPEN_LOOP) {
            System.out.println("Switching to open loop");
            x.set(ControlMode.PercentOutput, 0);
            y.set(ControlMode.PercentOutput, 0);
            mDriveControlState = DriveControlState.OPEN_LOOP;
        }
        periodic.left_demand = signal.getLeft();
        periodic.right_demand = signal.getRight();
    }

    private Drive mDrive = new Drive();

    public Drive getInstance() {
        return mDrive;
    }

    @Override
    public void outputTelemetry() {

    }

    @Override
    public void reset() {

    }

    enum DriveControlState {
        OPEN_LOOP;

        @Override
        public String toString() { /**this enables OPEN_LOOP to read OPEN_LOOP instead of its location in memory*/
            return super.toString();
        }
    }

    public static class PeriodicIO {
        public double left_demand = 0;
        public double right_demand = 0;
    }
