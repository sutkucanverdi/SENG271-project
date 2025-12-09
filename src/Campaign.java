import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Campaign {
    private LocalDate startDate;
    private LocalDate endDate;
    private String itemType; // normalized upper-case type (e.g. "BOOK")
    private int discountRate; // percent

    public static final int MAX_DISCOUNT_RATE = 50;

    // input: commands.txt → 23.03.2017 gibi
    private static final DateTimeFormatter PARSER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    // output: SHOWCAMPAIGNS → 01/09/2017 gibi
    private static final DateTimeFormatter OUTPUT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Campaign(String startDateStr, String endDateStr, String itemType, int discountRate) {
        this.itemType = itemType == null ? null : itemType.trim().toUpperCase();
        this.discountRate = discountRate;
        try {
            this.startDate = LocalDate.parse(startDateStr, PARSER);
            this.endDate = LocalDate.parse(endDateStr, PARSER);
        } catch (DateTimeParseException e) {
            // Invalid dates: set to far past / far future to avoid accidental activation
            this.startDate = LocalDate.MIN;
            this.endDate = LocalDate.MAX;
        }
    }

    public String getItemType() {
        return itemType;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    // Check if campaign is active on the given date (inclusive)
    public boolean isActive(LocalDate date) {
        if (date == null) date = LocalDate.now();
        return (date.isEqual(startDate) || date.isAfter(startDate)) &&
               (date.isEqual(endDate)   || date.isBefore(endDate));
    }

    @Override
    public String toString() {
        // Örnek: 20% sale of PERFUME until 01/09/2017
        String endStr = endDate.format(OUTPUT);
        return discountRate + "% sale of " + itemType + " until " + endStr;
    }
}
