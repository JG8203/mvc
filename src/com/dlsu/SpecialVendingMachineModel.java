import java.util.*;

public class SpecialVendingMachineModel extends VendingMachineModel {

    public SpecialVendingMachineModel(List<Slot> slots) {
        super(slots);
    }


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
        for (String choice : choices) {
            this.handleChoice(Integer.parseInt(choice), baseItem, totalCalories, totalPrice, actions);
        }
        this.setBalance(this.getBalance() - (int) totalPrice);
        return this.getBalance(); // Return the remaining balance
    }
}
