package frc.robot.subsystems;


public class Drive extends Subsystem {
    private TalonSRX
    Drive mDrive = new Drive();

    public Drive getInstance()
    {
        return mDrive;
    } 
    @Override
    public void outputTelemetry() {
        
    }
    @Override
    public void reset() {
        
    }

}