/**
 * The Slot class represents a vending machine slot that holds a specific item along with its quantity.
 * It is typically used in an vending machine to manage the available quantity of an item in a specific slot.
 *
 * Each slot can hold an item and the maximum quantity allowed for that item in the slot.
 * When creating a slot, you can specify the initial item and quantity, and the maximum quantity allowed in the slot (default is 10).
 */
 public class Slot {
    private Item item;
    private int quantity;
    private int maxQuantity;

    public Slot(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.maxQuantity = 10;
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
    * Returns the maximum quantity of this order. This is used to determine how many items can be added to the order without violating the limit set by #setLimit ( int ).
    * 
    * 
    * @return the maximum quantity of this order or - 1 if there is no limit set by #setLimit ( int
    */
    public int getMaxQuantity() {
        return maxQuantity;
    }
    public Slot clone() {
        Item clonedItem = new Item(item.getName(), item.getPrice(), item.getCalories(), item.getType(), item.getAction()); // Assuming constructor of Item class
        return new Slot(clonedItem, this.quantity);
    }
}