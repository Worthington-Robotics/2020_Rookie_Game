package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.lib.loops.ILooper;
import frc.lib.loops.Loop;
import frc.lib.util.DriveSignal;
import frc.lib.util.HIDHelper;
import frc.robot.Constants;


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
    private static Drive mDrive = new Drive();
    private DriveControlState mDriveControlState;
    private PeriodicIO periodic = new PeriodicIO();
    private double[] operatorInput = {0, 0, 0};
    private final Loop mloop = new Loop() {

        /**
         * what the loop runs when started by the subsystem manager
         *
         * @param timestamp handled by subsystem manager
         */
        public void onStart(double timestamp) {
            synchronized (Drive.this) {
            }

        }

        /**
         * what the loop runs while run by the subsystem manager
         *
         * @param timestamp handled by subsystem manager
         */
        public void onLoop(double timestamp) {
            operatorInput = HIDHelper.getAdjStick(Constants.MASTER_STICK);
            SmartDashboard.putNumberArray("stick", operatorInput);
            setOpenLoop(arcadeDrive(operatorInput[1], operatorInput[2]));
        }

        /**
         * what the loop runs when ended by the subsystem manager
         *
         * @param timestamp handled by subsystem manager
         */
        public void onStop(double timestamp) {

        }
    };

    public synchronized void writePeriodicOutputs() {
        x.set(ControlMode.PercentOutput, periodic.left_demand);
        y.set(ControlMode.PercentOutput, periodic.right_demand);
        a.set(ControlMode.Follower, x.getDeviceID());
        b.set(ControlMode.Follower, x.getDeviceID());
        c.set(ControlMode.Follower, y.getDeviceID());
        d.set(ControlMode.Follower, y.getDeviceID());
    }

    public void registerEnabledLoops(ILooper enabledLooper) {
        enabledLooper.register(mloop);
    }

    public static Drive getInstance() {
        return mDrive;
    }

    @Override
    public void outputTelemetry() {

    }

    @Override
    public void reset() {

    }

    private DriveSignal arcadeDrive(double x, double y) {
        double left = 0;
        double right = 0;

        double maxInput = Math.copySign(Math.max(Math.abs(x), Math.abs(y)), x);

        if (x >= 0 && y >= 0) {
            left = maxInput;
            right = x - y;
        }
        if (x < 0 && y >= 0) {
            left = x + y;
            right = maxInput;
        }
        if (x < 0 && y < 0) {
            left = x + y;
            right = maxInput;
        }
        if (x >= 0 && y > 0) {
            left = maxInput;
            right = x - y;
        }
        return new DriveSignal(left, right);
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