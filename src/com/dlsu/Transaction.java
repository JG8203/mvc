import java.time.LocalDateTime;

public class Transaction {
    private Item item;
    private int quantity;
    private double totalPrice;
    private LocalDateTime timestamp;

    public Transaction(Item item, int quantity, double totalPrice) {
        this.item = item;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.timestamp = LocalDateTime.now();
    }

    /**
    * Returns the item associated with this item. This is a copy of the item that was passed to the constructor but with the exception of the Item#getId () which can be used to retrieve the item's id.
    * 
    * 
    * @return the item associated with this item or null if there is no item associated with this item ( for example if the item was removed
    */
    public Item getItem() {
        return item;
    }

    /**
    * Sets the item to be displayed. This is a convenience method for #setItem ( Item ).
    * 
    * @param item - The item to display in the list of items
    */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
    * Returns the quantity of this order. This is the number of items in the order that have been added to this order.
    * 
    * 
    * @return the quantity of this order which is the number of items in the order that have been added to this
    */
    public int getQuantity() {
        return quantity;
    }

    /**
    * Sets the quantity of this Order. This can be used to determine how many orders are contained in the order.
    * 
    * @param quantity - The quantity of this Order in units of the
    */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
    * Returns the total price of the order. This is the price that has been paid to the customer and can be used to calculate the price of the order in the customer's currency.
    * 
    * 
    * @return the total price of the order as a double value in the customer's currency with decimal places rounded to
    */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
    * Sets the total price of the order. Note that this is a parameter for performance reasons and should not be used in production code.
    * 
    * @param totalPrice - The total price of the order in units
    */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
    * Returns the timestamp associated with this event. This method is useful for converting events that are in the process of being sent to a service such as an Ambient Harmony or other system.
    * 
    * 
    * @return the timestamp associated with this event or null if there is no timestamp associated with this event ( in which case the event's timestamp is returned
    */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
    * Sets the timestamp of this event. This is used to determine when the event was created in the event source.
    * 
    * @param timestamp - the timestamp to set as a LocalDateTime object ( may be null
    */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}