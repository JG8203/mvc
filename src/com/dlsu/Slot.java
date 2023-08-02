public class Slot {
    private Item item;
    private int quantity;
    private int maxQuantity;

    public Slot(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.maxQuantity = 10;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }
}