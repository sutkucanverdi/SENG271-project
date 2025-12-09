public class Laptop extends Computer {
    private boolean hasHdmiSupport;

    public Laptop(double cost, int stock, String manufacturer, String brand, int maxVolt, int maxWatt,
                  String operatingSystem, String cpuType, int ramCapacity, int hddCapacity, boolean hasHdmiSupport) {

        super(cost, stock, manufacturer, brand, maxVolt, maxWatt,
              operatingSystem, cpuType, ramCapacity, hddCapacity);
        this.hasHdmiSupport = hasHdmiSupport;
    }

    @Override
    public String toString() {
        String hdmiStr = hasHdmiSupport ? "Yes" : "No";

        return "Type: Laptop\n" +
               super.toString() + "\n" +
               "HDMI Support: " + hdmiStr;
    }
}
