public class SkinCare extends Cosmetic {
    private boolean babySensitive;

    public SkinCare(double cost, int stock, String manufacturer, String brand, boolean isOrganic, int expirationYear, int weight, boolean babySensitive) {
        super(cost, stock, manufacturer, brand, isOrganic, expirationYear, weight);
        this.babySensitive = babySensitive;
    }

    @Override
    public String toString() {
        String sensitiveStr = babySensitive ? "Yes" : "No";
        return "Type: SkinCare\n" + super.toString() + "\nBaby Sensitive: " + sensitiveStr;
    }
}