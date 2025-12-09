public class Perfume extends Cosmetic {
    private int lastingDuration; // minutes

    public Perfume(double cost, int stock, String manufacturer, String brand,
                   boolean isOrganic, int expirationYear, int weight, int lastingDuration) {

        super(cost, stock, manufacturer, brand, isOrganic, expirationYear, weight);
        this.lastingDuration = lastingDuration;
    }

    @Override
    public String toString() {
        return "Type: Perfume\n" +
               super.toString() + "\n" +
               "Lasting Duration: " + lastingDuration + " min.";
    }
}
