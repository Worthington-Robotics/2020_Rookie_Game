package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.lib.util.DriveSignal;


public class Drive extends Subsystem {
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

    private TalonSRX x, y;
    private VictorSPX a, b, c, d;
    private Drive mDrive = new Drive();
    private DriveControlState mDriveControlState;
private PeriodicIO periodic = new PeriodicIO();

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
        OPEN_LOOP,
        PATH_FOLLOWING,
        PROFILING_TEST,
        GYRO_LOCK,
        ANGLE_PID;

        @Override
        public String toString() {
            return super.toString();
        }
    }


    public static class PeriodicIO {
        public double left_demand = 0;
        public double right_demand = 0;
    }
}