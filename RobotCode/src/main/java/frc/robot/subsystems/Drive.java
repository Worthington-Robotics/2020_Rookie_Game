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
    private TalonSRX frontRight, middleLeft, backRight, frontLeft, backLeft;
    private VictorSPX middleRight;
    private static Drive mDrive = new Drive();
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
        frontRight = new TalonSRX(Constants.DRIVE_FRONT_RIGHT_ID);
        frontLeft = new TalonSRX(Constants.DRIVE_FRONT_LEFT_ID);
        middleRight = new VictorSPX(Constants.DRIVE_MIDDLE_RIGHT_ID);
        middleLeft = new TalonSRX(Constants.DRIVE_MIDDLE_LEFT_ID);
        backRight = new TalonSRX(Constants.DRIVE_BACK_RIGHT_ID);
        backLeft = new TalonSRX(Constants.DRIVE_BACK_LEFT_ID);
        configTalons();
    }

    public synchronized void setOpenLoop(DriveSignal signal) {
        if (mDriveControlState != DriveControlState.OPEN_LOOP) {
            System.out.println("Switching to open loop");
            frontLeft.set(ControlMode.PercentOutput, 0);
            frontRight.set(ControlMode.PercentOutput, 0);
            mDriveControlState = DriveControlState.OPEN_LOOP;
        }
        periodic.left_demand = signal.getLeft();
        periodic.right_demand = signal.getRight();
    }

    private void configTalons() {
        frontLeft.setSensorPhase(true);
        frontLeft.selectProfileSlot(0, 0);
        frontLeft.config_kF(0, Constants.DRIVE_LEFT_KF, 0);
        frontLeft.config_kP(0, Constants.DRIVE_LEFT_KP, 0);
        frontLeft.config_kI(0, Constants.DRIVE_LEFT_KI, 0);
        frontLeft.config_kD(0, Constants.DRIVE_LEFT_KD, 0);
        frontLeft.config_IntegralZone(0, 300);
        frontLeft.setInverted(false);
        frontLeft.configVoltageCompSaturation(Constants.DRIVE_VCOMP);
        frontLeft.enableVoltageCompensation(true);

        middleLeft.setInverted(false);
        middleLeft.configVoltageCompSaturation(Constants.DRIVE_VCOMP);
        middleLeft.enableVoltageCompensation(true);
        middleLeft.follow(frontLeft);

        backLeft.setInverted(false);
        backLeft.configVoltageCompSaturation(Constants.DRIVE_VCOMP);
        backLeft.enableVoltageCompensation(true);
        backLeft.follow(frontLeft);


        frontRight.setSensorPhase(true);
        frontRight.selectProfileSlot(0, 0);
        frontRight.config_kF(0, Constants.DRIVE_RIGHT_KF, 0);
        frontRight.config_kP(0, Constants.DRIVE_RIGHT_KP, 0);
        frontRight.config_kI(0, Constants.DRIVE_RIGHT_KI, 0);
        frontRight.config_kD(0, Constants.DRIVE_RIGHT_KD, 0);
        frontRight.config_IntegralZone(0, 300);
        frontRight.setInverted(true);
        frontRight.configVoltageCompSaturation(Constants.DRIVE_VCOMP);
        frontRight.enableVoltageCompensation(true);

        middleRight.setInverted(true);
        middleRight.configVoltageCompSaturation(Constants.DRIVE_VCOMP);
        middleRight.enableVoltageCompensation(true);
        middleRight.follow(frontRight);

        backRight.setInverted(true);
        backRight.configVoltageCompSaturation(Constants.DRIVE_VCOMP);
        backRight.enableVoltageCompensation(true);
        backRight.follow(frontRight);

    }

   
    private DriveControlState mDriveControlState;

    public synchronized void writePeriodicOutputs() {
        frontRight.set(ControlMode.PercentOutput, periodic.right_demand);
        //middleRight.set(ControlMode.Follower, frontRight.getDeviceID());
        backRight.set(ControlMode.Follower, frontRight.getDeviceID());
        frontLeft.set(ControlMode.PercentOutput, periodic.left_demand);
        middleLeft.set(ControlMode.Follower, frontLeft. getDeviceID());
        backLeft.set(ControlMode.Follower, frontLeft.getDeviceID());
    }

    /**
     * Outputs all logging information to the SmartDashboard
     */
    @Override
    public void outputTelemetry() {

    }

    /**
     * Called to reset and configure the subsystem
     */
    @Override
    public void reset() {

    }

    public void registerEnabledLoops(ILooper enabledLooper) {
        enabledLooper.register(mloop);
    }
    public static Drive getInstance() {
        return mDrive;
    }

    @Override
    public void outputTelemetry() {
        SmartDashboard.putNumberArray("stick", operatorInput);
    }

    @Override
    public void reset() {

    }


    private DriveSignal arcadeDrive(double xSpeed, double zRotation) {
        double leftMotorOutput;
        double rightMotorOutput;

        double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);

        if (xSpeed >= 0.0) {
            // First quadrant, else second quadrant
            if (zRotation >= 0.0) {
                leftMotorOutput = maxInput;
                rightMotorOutput = xSpeed - zRotation;
            } else {
                leftMotorOutput = xSpeed + zRotation;
                rightMotorOutput = maxInput;
            }
        } else {
            // Third quadrant, else fourth quadrant
            if (zRotation >= 0.0) {
                leftMotorOutput = xSpeed + zRotation;
                rightMotorOutput = maxInput;
            } else {
                leftMotorOutput = maxInput;
                rightMotorOutput = xSpeed - zRotation;
            }
        }
        return new DriveSignal(rightMotorOutput, leftMotorOutput);
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


    class PeriodicIO {
        public double left_demand = 0;
        public double right_demand = 0;
    }
}