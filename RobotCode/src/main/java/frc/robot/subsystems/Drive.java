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

    private TalonSRX x, y;
    private VictorSPX a, b, c, d;
    private DriveControlState mDriveControlState;
    private PeriodicIO periodic = new PeriodicIO();
    private double[] operatorInput = {0, 0, 0};
    private static Drive mDrive = new Drive();
    private final Loop mLoop = new Loop() {
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

    public Drive() {
        x = new TalonSRX(1);
        y = new TalonSRX(2);
        a = new VictorSPX(3);
        b = new VictorSPX(4);
        c = new VictorSPX(5);
        d = new VictorSPX(6);
    }

    public void registerEnabledLoops(ILooper enabledLooper) {
        enabledLooper.register(mLoop);
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

    public synchronized void writePeriodicOutputs() {
        x.set(ControlMode.PercentOutput, periodic.left_demand);
        y.set(ControlMode.PercentOutput, periodic.right_demand);
        a.set(ControlMode.PercentOutput, x.getDeviceID());
        b.set(ControlMode.PercentOutput, x.getDeviceID());
        c.set(ControlMode.PercentOutput, y.getDeviceID());
        d.set(ControlMode.PercentOutput, y.getDeviceID());
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
        double left;
        double right;
        double maxInput = Math.copySign(Math.max(Math.abs(x), Math.abs(y)), x);
        if (x >= 0 && y >= 0) {
            left = maxInput;
            right = x - y;
        } else if (x < 0 && y >= 0) {
            left = x + y;
            right = y;
        } else if (x < 0 && y < 0) {
            left = x + y;
            right = maxInput;
        } else {
            left = maxInput;
            right = x - y;
        }
        return new DriveSignal(left, right);
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
}
