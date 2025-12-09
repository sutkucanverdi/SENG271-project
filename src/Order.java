import java.util.List;
import java.util.Date;
import java.util.ArrayList;

public class Order {
    private Date orderDate;
    private double totalCost;
    private int customerId;
    private List<Item> purchasedItems; // Satın alınan ürünlerin kopyası

    public Order(int customerId, double totalCost, List<Item> items) {
        this.orderDate = new Date(); // Şu anki tarih ve saati alır
        this.customerId = customerId;
        this.totalCost = totalCost;
        // Sepetteki ürünlerin o anki halini sipariş geçmişine kopyalıyoruz
        this.purchasedItems = new ArrayList<>(items); 
    }

    public int getCustomerId() {
        return customerId;
    }
    
    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "Order date: " + orderDate + 
               "\nCustomer ID: " + customerId + 
               "\nTotal Cost: " + totalCost + 
               "\nNumber of purchased items: " + purchasedItems.size();
    }
}