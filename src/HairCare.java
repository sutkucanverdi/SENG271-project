public class HairCare extends Cosmetic {
    private boolean isMedicated;

    public HairCare(double cost, int stock, String manufacturer, String brand,
                    boolean isOrganic, int expirationYear, int weight, boolean isMedicated) {

        super(cost, stock, manufacturer, brand, isOrganic, expirationYear, weight);
        this.isMedicated = isMedicated;
    }

    @Override
    public String toString() {
        String medicatedStr = isMedicated ? "Yes" : "No";
        return "Type: HairCare\n" +
               super.toString() + "\n" +
               "Medicated: " + medicatedStr;
    }
}
