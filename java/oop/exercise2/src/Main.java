import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<SmartDevice> homeHub = new ArrayList<>();

        SmartLight sd1 = new SmartLight("Living Room");
        SmartLight sd2 = new SmartLight("Kitchen");
        SmartDevice sd3 = new SmartThermostat("Hallway");

        homeHub.add(sd1);
        homeHub.add(sd3);
        homeHub.add(sd2);


        sd1.turnOn();
        sd3.turnOn();
        sd2.setLevel(75);

        for (SmartDevice device: homeHub) {
            device.performSelfDiagnostic();
        }

    }
}
