public class Desktop extends Computer {
    private String boxColor;

    public Desktop(double cost, int stock, String manufacturer, String brand, int maxVolt, int maxWatt,
                   String operatingSystem, String cpuType, int ramCapacity, int hddCapacity, String boxColor) {

        super(cost, stock, manufacturer, brand, maxVolt, maxWatt,
              operatingSystem, cpuType, ramCapacity, hddCapacity);
        this.boxColor = boxColor;
    }

    @Override
    public String toString() {

        return "Type: Desktop\n" +
               "Item ID: " + id + "\n" +
               "Price: " + cost + " $\n" +
               "Manufacturer: " + manufacturer + "\n" +
               "Brand: " + brand + "\n" +
               "Max Volt: " + maxVolt + " V.\n" +
               "Max Watt: " + maxWatt + " W.\n" +
               "Operating System: " + operatingSystem + "\n" +
               "CPU Type: " + cpuType + "\n" +
               "RAM Capacity: " + ramCapacity + " GB\n" +
               "HDD Capacity: " + hddCapacity + " GB\n" +
               "Box Color: " + boxColor;
    }
}
