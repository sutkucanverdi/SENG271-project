public class TV extends Electronic {
    private int screenSize; // inch

    public TV(double cost, int stock, String manufacturer, String brand,
              int maxVolt, int maxWatt, int screenSize) {
        
        super(cost, stock, manufacturer, brand, maxVolt, maxWatt);
        this.screenSize = screenSize;
    }

    @Override
    public String toString() {
        return "Type: TV\n" +
               super.toString() + "\n" +
               "Screen size: " + screenSize + "\"";
    }
}
