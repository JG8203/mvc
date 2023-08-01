import java.util.*;

/**
 * The SpecialVendingMachineModel class represents a special vending machine model that extends the basic
 * VendingMachineModel. It adds additional features for customizing products with add-ons.
 */
public class SpecialVendingMachineModel extends VendingMachineModel {

    /**
     * Constructs a SpecialVendingMachineModel with the given list of slots.
     *
     * @param slots A list of Slot instances representing the slots in the special vending machine.
     */
    public SpecialVendingMachineModel(List<Slot> slots) {
        super(slots);
    }

    /**
     * Checks if it is possible to customize a product with add-ons based on the current balance and item type.
     *
     * @param baseItemSlot The Slot representing the base item to be customized.
     * @return True if customization is possible; otherwise, false.
     */
    public boolean checkIfCanCustomize(Slot baseItemSlot) {
        Item baseItem = baseItemSlot.getItem();
        if (this.getBalance() < baseItem.getPrice() || this.getBalance() == 0) {
            return false;
        }
        if (!baseItem.getType().equals("base")) {
            return false;
        }
        return true;
    }

    /**
     * Handles the user's choice of an add-on to customize the base item.
     *
     * @param choice       The user's choice of add-on slot number.
     * @param baseItem     The base item to be customized.
     * @param totalCalories The total calorie count after adding the chosen add-on(s).
     * @param totalPrice   The total price after adding the chosen add-on(s).
     * @param actions      A list of actions to be performed while customizing the base item.
     */
    public void handleChoice(int choice, Item baseItem, double totalCalories, double totalPrice, List<String> actions) {
        choice -= 1;
        if (choice < 0 || choice >= this.getSlots().size()) {
            return;
        }
        Slot addOnSlot = this.getSlots().get(choice);
        Item addOn = addOnSlot.getItem();
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
     * Customizes a product by adding selected add-ons to the base item.
     *
     * @param slotNumber The slot number of the base item to be customized.
     * @param choices    A list of user choices representing the selected add-on slots.
     * @return The remaining balance after customizing the product.
     */
    public int customizeProduct(int slotNumber, List<String> choices) {
        Slot baseItemSlot = this.getSlots().get(slotNumber - 1);
        if (!this.checkIfCanCustomize(baseItemSlot)) {
            return this.getBalance();
        }
        Item baseItem = baseItemSlot.getItem();
        baseItemSlot.setQuantity(baseItemSlot.getQuantity() - 1); // Decrement the quantity of the base item
        double totalCalories = baseItem.getCalories();
        double totalPrice = baseItem.getPrice();
        List<String> actions = new ArrayList<>();
        for(String choice : choices){
            this.handleChoice(Integer.parseInt(choice), baseItem, totalCalories, totalPrice, actions);
        }
        this.setBalance(this.getBalance() - (int)totalPrice);
        int remainingBalance = this.getBalance();
        this.setBalance(0);
        return remainingBalance;
    }
}