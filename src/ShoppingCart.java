import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> items; // Sepetteki ürünler listesi

    public ShoppingCart() {
        this.items = new ArrayList<>(); // Sepet boş başlar
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void emptyCart() {
        items.clear();
    }

    public List<Item> getItems() {
        return items;
    }

    // Sepet boş mu kontrolü (Sipariş verirken lazım olacak)
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    @Override
    public String toString() {
        // Sepetteki ürünlerin kısa bir özetini döndürebiliriz
        String result = "Cart Contents:\n";
        for (Item item : items) {
            result += item.toString() + "\n---\n";
        }
        return result;
    }
}