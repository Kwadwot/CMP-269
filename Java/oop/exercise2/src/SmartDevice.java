abstract class SmartDevice implements Powerable {
    protected String deviceName;
    protected boolean isOn;

    protected static int activeDeviceCount;

    SmartDevice (String deviceName) {
        this.deviceName = deviceName;
        isOn = false;
    }

    abstract void performSelfDiagnostic();

    /*
        Base on task #4 of the exercise instructions, I have opted to implement
        the overridden turnOn and turnOff methods in this class. This is because
        the instruction requires that SmartThermostat's turnOn method must call
        super.turnOn(), which requires SmartDevice to have the method already
        implemented. Also, I thought I might as well implement the turnOff here too.
     */
    @Override
    public void turnOff() {
        this.isOn = false;
        activeDeviceCount -= 1;
    }

    @Override
    public void turnOn() {
        this.isOn = false;
        activeDeviceCount += 1;
    }
}
