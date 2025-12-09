public abstract class Item {
    protected int id;
    protected double cost;
    protected int stock;
    
    // ID'lerin otomatik artması için static sayaç
    protected static int itemCount = 0; 

    public Item(double cost, int stock) {
        this.id = ++itemCount;
        this.cost = cost;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public double getCost() {
        return cost;
    }
    
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public boolean decreaseStock(int amount) {
        if (stock >= amount) {
            stock -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Item ID: " + id + "\nPrice: " + cost + " $";
    }
}