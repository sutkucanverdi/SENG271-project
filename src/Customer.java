import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private int customerID;
    private String password;
    private double balance;
    private String status;  // CLASSIC, SILVER, GOLDEN
    private ShoppingCart cart;
    private List<Order> orderHistory;

    // Statik Sabitler
    public static final String CLASSIC = "CLASSIC";
    public static final String SILVER  = "SILVER";
    public static final String GOLDEN  = "GOLDEN";

    public static final double SILVER_LIMIT   = 1000;
    public static final double GOLDEN_LIMIT   = 5000;
    public static final double SILVER_DISCOUNT  = 0.10; // %10
    public static final double GOLDEN_DISCOUNT  = 0.15; // %15

    // Müşteri ID'lerini otomatik artırmak için sayaç
    private static int customerCount = 0;

    // Toplam harcama
    private double totalSpent = 0;

    public Customer(String name, String email, String dateOfBirth,
                    double balance, String password) {
        super(name, email, dateOfBirth);
        this.customerID = ++customerCount;   // 1'den başla
        this.balance = balance;
        this.password = password;
        this.status = CLASSIC;               // herkes CLASSIC başlar
        this.cart = new ShoppingCart();
        this.orderHistory = new ArrayList<>();
    }

    // --- Getter / Setter ---

    public int getCustomerID() {
        return customerID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    // Ödemeden sonra harcamayı arttır
    public void addToSpent(double amount) {
        this.totalSpent += amount;
        updateStatus();   // her harcamada statüyü kontrol et
    }

    // Statü güncelleme ve gerekli tebrik mesajları
    private void updateStatus() {
        if (this.status.equals(CLASSIC)
                && totalSpent >= SILVER_LIMIT
                && totalSpent < GOLDEN_LIMIT) {

            this.status = SILVER;
            System.out.println(
                "Congratulations! You have been upgraded to a SILVER MEMBER! " +
                "You have earned a discount of 10% on all purchases."
            );

        } else if ((this.status.equals(CLASSIC) || this.status.equals(SILVER))
                && totalSpent >= GOLDEN_LIMIT) {

            this.status = GOLDEN;
            System.out.println(
                "Congratulations! You have been upgraded to a GOLDEN MEMBER! " +
                "You have earned a discount of 15% on all purchases."
            );
        }
    }

    // Ödev formatına uygun çıktı
    @Override
    public String toString() {
        return "Customer name: " + name +
               " ID: " + customerID +
               " e-mail: " + email +
               " Date of Birth: " + getFormattedDateOfBirth() +
               " Status: " + status;
    }
}
