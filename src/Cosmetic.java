public abstract class Cosmetic extends Item {
    protected String manufacturer;
    protected String brand;
    protected boolean isOrganic;
    protected int expirationYear;
    protected int weight; // Gram cinsinden

    public Cosmetic(double cost, int stock, String manufacturer, String brand, boolean isOrganic, int expirationYear, int weight) {
        super(cost, stock); // Fiyat ve stoğu Item'a gönder
        this.manufacturer = manufacturer;
        this.brand = brand;
        this.isOrganic = isOrganic;
        this.expirationYear = expirationYear;
        this.weight = weight;
    }

    @Override
    public String toString() {
        String organicStr = isOrganic ? "Yes" : "No";
        // Item'ın toString'ini çağır, altına kozmetik özelliklerini ekle
        return super.toString() + 
               "\nManufacturer: " + manufacturer + 
               "\nBrand: " + brand + 
               "\nOrganic: " + organicStr + 
               "\nExpiration Date: " + expirationYear + 
               "\nWeight: " + weight + " g.";
    }
}