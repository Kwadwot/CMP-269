public class SmartThermostat extends SmartDevice implements Adjustable {
    private int temperature; // ranging from 60 to 80 degrees

    SmartThermostat(String deviceName) {
        super(deviceName);
    }

    @Override
    void performSelfDiagnostic() {
        System.out.println("Check Thermostat health..."); // Arbitrary print statement for checking polymorphism
    }

    @Override
    public void setLevel(int level) {
        if (level >= 60 && level <= 80) {
            temperature = level;
        }
    }

    @Override
    public void turnOn() {
        System.out.println("HVAC System starting...");
        super.turnOn();
    }
}
