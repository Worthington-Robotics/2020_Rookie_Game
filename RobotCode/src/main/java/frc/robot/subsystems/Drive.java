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
        frontRight = new TalonSRX(Constants.DRIVE_FRONT_RIGHT_ID);
        frontLeft = new TalonSRX(Constants.DRIVE_FRONT_LEFT_ID);
        middleRight = new VictorSPX(Constants.DRIVE_MIDDLE_RIGHT_ID);
        middleLeft = new TalonSRX(Constants.DRIVE_MIDDLE_LEFT_ID);
        backRight = new TalonSRX(Constants.DRIVE_BACK_RIGHT_ID);
        backLeft = new TalonSRX(Constants.DRIVE_BACK_LEFT_ID);
    }

    public synchronized void setOpenLoop(DriveSignal signal) {
        if (mDriveControlState != DriveControlState.OPEN_LOOP) {
            System.out.println("Switching to open loop");
            frontRight.set(ControlMode.PercentOutput, 0);
            frontLeft.set(ControlMode.PercentOutput, 0);
            mDriveControlState = DriveControlState.OPEN_LOOP;
        }
        periodic.left_demand = signal.getLeft();
        periodic.right_demand = signal.getRight();
    }

    private TalonSRX frontRight, middleLeft, backRight, frontLeft, backLeft;
    private VictorSPX middleRight;
    private static Drive mDrive = new Drive();
    private DriveControlState mDriveControlState;
private PeriodicIO periodic = new PeriodicIO();
private double[] operatorInput = {0, 0, 0};
private final Loop mloop = new Loop(){

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
        frontRight.set(ControlMode.PercentOutput, periodic.left_demand);
        frontLeft.set(ControlMode.PercentOutput, periodic.right_demand);
        middleRight.set(ControlMode.Follower, frontRight.getDeviceID());
        middleLeft.set(ControlMode.Follower, frontRight.getDeviceID());
        backRight.set(ControlMode.Follower, frontLeft. getDeviceID());
        backLeft.set(ControlMode.Follower, frontLeft.getDeviceID());
    }
    public void registerEnabledLoops(ILooper enabledLooper) {
        enabledLooper.register(mloop);
    }
<<<<<<< Updated upstream

=======
    public static Drive getInstance() {
        return mDrive;
    }

    @Override
    public void outputTelemetry() {
        SmartDashboard.putNumberArray("Axis", operatorInput);
    }

    @Override
    public void reset() {

    }
    public void readPeriodicInputs() {
        operatorInput[1] = Constants.MASTER.getRawAxis(1);
        operatorInput[2] = Constants.MASTER.getRawAxis(2);
    }


    private DriveSignal arcadeDrive(double frontRight, double frontLeft) {
        double left = 0;
        double right = 0;

        double maxInput = Math.copySign(Math.max(Math.abs(frontRight), Math.abs(frontLeft)), frontRight);

        if(frontRight >= 0 && frontLeft >= 0) {
            left = maxInput;
            right = frontRight - frontLeft;
        }
        if(frontRight < 0 && frontLeft >= 0) {
            left = frontRight + frontLeft;
            right = maxInput;
        }
        if(frontRight < 0 && frontLeft < 0) {
            left = frontRight + frontLeft;
            right = maxInput;
        }
        if(frontRight >= 0 && frontLeft > 0) {
            left = maxInput;
            right = frontRight - frontLeft;
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
>>>>>>> Stashed changes
            return super.toString();
        }
    }


    public static class PeriodicIO {
        public double left_demand = 0;
        public double right_demand = 0;
    }
}

