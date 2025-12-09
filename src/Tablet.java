public class Tablet extends Computer {
    private String dimension;

    public Tablet(double cost, int stock, String manufacturer, String brand, int maxVolt, int maxWatt,
                  String operatingSystem, String cpuType, int ramCapacity, int hddCapacity, String dimension) {

        super(cost, stock, manufacturer, brand, maxVolt, maxWatt,
              operatingSystem, cpuType, ramCapacity, hddCapacity);
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return "Type: Tablet\n" +
               super.toString() + "\n" +
               "Dimension: " + dimension;
    }
}
