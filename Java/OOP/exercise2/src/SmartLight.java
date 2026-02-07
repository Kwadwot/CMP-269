class SmartLight extends SmartDevice implements Adjustable{
    private int brightness; // ranging from 0-100

    SmartLight(String deviceName) {
        super(deviceName);
    }

    @Override
    void performSelfDiagnostic() {
        System.out.println("Checking LED health...");
    }

    @Override
    public void setLevel(int level) {
        if (!this.isOn) {
            System.out.println("Cannot adjust: Device is OFF");
        }
        else {
            if (level >= 0 && level <= 100) {
                brightness = level;
            }
        }
    }
}
