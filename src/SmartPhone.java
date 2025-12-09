public class SmartPhone extends Electronic {
    private String screenType;

    public SmartPhone(double cost, int stock, String manufacturer, String brand,
                      int maxVolt, int maxWatt, String screenType) {

        super(cost, stock, manufacturer, brand, maxVolt, maxWatt);
        this.screenType = screenType;
    }

    @Override
    public String toString() {
        return "Type: SmartPhone\n" +
               super.toString() + "\n" +
               "Screen Type: " + screenType;
    }
}
