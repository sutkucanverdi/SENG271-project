public abstract class Electronic extends Item {
    protected String manufacturer;
    protected String brand;
    protected int maxVolt;
    protected int maxWatt;

    public Electronic(double cost, int stock, String manufacturer, String brand, int maxVolt, int maxWatt) {
        super(cost, stock);
        this.manufacturer = manufacturer;
        this.brand = brand;
        this.maxVolt = maxVolt;
        this.maxWatt = maxWatt;
    }

    @Override
    public String toString() {
        return super.toString() + 
               "\nManufacturer: " + manufacturer + 
               "\nBrand: " + brand + 
               "\nMax Volt: " + maxVolt + " V." +
               "\nMax Watt: " + maxWatt + " W.";
    }
}