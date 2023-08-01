/**
 * The Slot class represents a slot in a vending machine, capable of holding an item and its quantity.
 */
public class Slot {
    private Item item;
    private int quantity;

    /**
     * Constructs a Slot with the specified item and quantity.
     *
     * @param item     The item to be stored in the slot.
     * @param quantity The quantity of the item available in the slot.
     */
    public Slot(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Gets the item stored in the slot.
     *
     * @return The item stored in the slot.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets the item stored in the slot.
     *
     * @param item The new item to be stored in the slot.
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Gets the quantity of the item available in the slot.
     *
     * @return The quantity of the item available in the slot.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item available in the slot.
     *
     * @param quantity The new quantity of the item available in the slot.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
