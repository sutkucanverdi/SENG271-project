public abstract class Computer extends Electronic {
    protected String operatingSystem;
    protected String cpuType;
    protected int ramCapacity;
    protected int hddCapacity;

    public Computer(double cost, int stock, String manufacturer, String brand, int maxVolt, int maxWatt,
                    String operatingSystem, String cpuType, int ramCapacity, int hddCapacity) {

        super(cost, stock, manufacturer, brand, maxVolt, maxWatt);
        this.operatingSystem = operatingSystem;
        this.cpuType = cpuType;
        this.ramCapacity = ramCapacity;
        this.hddCapacity = hddCapacity;
    }

    @Override
    public String toString() {

        return "Item ID: " + id + "\n" +
               "Price: " + cost + " $\n" +
               "Manufacturer: " + manufacturer + "\n" +
               "Brand: " + brand + "\n" +
               "Max Volt: " + maxVolt + " V.\n" +
               "Max Watt: " + maxWatt + " W.\n" +
               "Operating System: " + operatingSystem + "\n" +
               "CPU Type: " + cpuType + "\n" +
               "RAM Capacity: " + ramCapacity + " GB\n" +
               "HDD Capacity: " + hddCapacity + " GB";
    }
}
