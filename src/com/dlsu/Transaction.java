import java.time.LocalDateTime;

/**
 * The Transaction class represents a transaction made during a vending machine operation.
 * It records the item purchased, the quantity bought, the total price, and the timestamp of the transaction.
 */
public class Transaction {
    private Item item;
    private int quantity;
    private double totalPrice;
    private LocalDateTime timestamp;

    /**
     * Constructs a Transaction with the specified item, quantity, and total price.
     * The timestamp of the transaction is automatically set to the current date and time.
     *
     * @param item       The item purchased in the transaction.
     * @param quantity   The quantity of the item bought.
     * @param totalPrice The total price of the items purchased.
     */
    public Transaction(Item item, int quantity, double totalPrice) {
        this.item = item;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Gets the item involved in the transaction.
     *
     * @return The item purchased in the transaction.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets the item involved in the transaction.
     *
     * @param item The new item purchased in the transaction.
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Gets the quantity of the item bought in the transaction.
     *
     * @return The quantity of the item bought.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item bought in the transaction.
     *
     * @param quantity The new quantity of the item bought.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the total price of the items purchased in the transaction.
     *
     * @return The total price of the items purchased.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the total price of the items purchased in the transaction.
     *
     * @param totalPrice The new total price of the items purchased.
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets the timestamp of the transaction.
     *
     * @return The timestamp of the transaction.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the transaction.
     *
     * @param timestamp The new timestamp of the transaction.
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}