import java.util.*;

public class SpecialVendingMachineModel extends VendingMachineModel {

    public SpecialVendingMachineModel(List<Slot> slots) {
        super(slots);
    }


    /**
    * Checks if it is possible to customize this item. This is used to prevent an item from being added to the shopping cart and to make sure it is indeed customizable
    * 
    * @param baseItemSlot - the slot that we are going to add to the shopping cart
    * 
    * @return true if the item can be customized false if it can't be customized or not at all ( in which case we don't have to
    */
    public boolean checkIfCanCustomize(Slot baseItemSlot) {
        Item baseItem = baseItemSlot.getItem();
        // Check if the base item is less than the base item s price or if the base item s balance is less than the base item s price.
        if (this.getBalance() < baseItem.getPrice() || this.getBalance() == 0) {
            return false;
        }
        // Check if base item is a base item
        if (!baseItem.getType().equals("base")) {
            return false;
        }
        return true;
    }

    /**
    * Handles the choice of an add - on. This is called by the check method to determine if it is possible to add a new add - on
    * 
    * @param choice - The index of the add - on
    * @param baseItem - The item that will be deducted from the slot
    * @param totalCalories - The total calories of the item
    * @param totalPrice - The total price of the item ( can be negative )
    * @param actions - The list of actions that will be added to the
    */
    public void handleChoice(int choice, Item baseItem, double totalCalories, double totalPrice, List<String> actions) {
        choice -= 1;
        // Returns true if the choice is within the list of slots.
        if (choice < 0 || choice >= this.getSlots().size()) {
            return;
        }
        Slot addOnSlot = this.getSlots().get(choice);
        Item addOn = addOnSlot.getItem();
        // Returns true if the amount of balance is less than the price of the add on.
        if (this.getBalance() < addOn.getPrice()) {
            return;
        }
        addOnSlot.setQuantity(addOnSlot.getQuantity() - 1); // Deduct the quantity of the item immediately after the checks

        baseItem.getIngredients().add(addOn);
        totalCalories += addOn.getCalories();
        totalPrice += addOn.getPrice();
        actions.add(addOn.getAction() + " " + addOn.getName());
    }

    /**
    * Customize the product by decreasing the quantity of the base item. This is done in two steps : 1. If the user chooses to customize the product we set the quantity to - 1 2. If the user chooses to customize the product we call handleChoice ( Integer List String ) with the list of actions that are to be taken in the order of the items in the list
    * 
    * @param slotNumber - The slot number of the product
    * @param choices - The list of actions that are to be taken in the order of the items in the list
    * 
    * @return The balance of the shopping cart after the customization has been completed. Note that it is up to the user to make sure that the cart is not out of date
    */
    public int customizeProduct(int slotNumber, List<String> choices) {
        Slot baseItemSlot = this.getSlots().get(slotNumber - 1);
        // Returns the balance of the item.
        if (!this.checkIfCanCustomize(baseItemSlot)) {
            return this.getBalance();
        }
        Item baseItem = baseItemSlot.getItem();
        baseItemSlot.setQuantity(baseItemSlot.getQuantity() - 1); // Decrement the quantity of the base item
        double totalCalories = baseItem.getCalories();
        double totalPrice = baseItem.getPrice();
        List<String> actions = new ArrayList<>();
        for (String choice : choices) {
            this.handleChoice(Integer.parseInt(choice), baseItem, totalCalories, totalPrice, actions);
        }
        this.setBalance(this.getBalance() - (int) totalPrice);
        return this.getBalance(); // Return the remaining balance
    }
}
