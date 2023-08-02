import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JPanel;

import java.util.Map;
public class VendingMachineController {
    private VendingMachineModel model;
    private VendingMachineView view;
    private Scanner scanner;

    public VendingMachineController(VendingMachineModel model, VendingMachineView view, Scanner scanner) {
        this.model = model;
        this.view = view;
        this.scanner = scanner;
    }

    /**
    * Returns the vending machine model. Note that this is a copy of the model that was set when the instance was created.
    * 
    * 
    * @return the vending machine model or null if there is no model set before the instance was created ( in which case the instance will be returned
    */
    public VendingMachineModel getModel(){
        return this.model;
    }

    /**
    * Displays the items in the view. This is called when the user clicks the button to select a list
    */
    public void displayItems() {
        view.displayItems(model.getSlots());
    }

    /**
    * Inserts funds into the balance box and updates the view. This is a convenience method for insertMoney
    * 
    * @param bills - List of Integer bill
    */
    public void insertMoney(List<Integer> bills) {
        model.getCoinBox().addFunds(bills);
        model.setBalance(model.getBalance() + bills.stream().mapToInt(Integer::intValue).sum());
        view.displayBalance(model.getBalance());
    }
    
    /**
    * Buy an item from the slot. This is called when the user clicks the buy button. The view will display the change that was made to the item
    * 
    * @param slotNumber - The slot number of the
    */
    public void buyItem(int slotNumber) {
        // If the slot number is invalid display invalid slot number.
        if (slotNumber < 1 || slotNumber > model.getSlots().size()) {
            view.displayInvalidSlotNumber();
            return;
        }
        Slot slot = model.getSlots().get(slotNumber - 1);
        Item item = slot.getItem();
        // Check if balance is less than the item price or not
        if (model.getBalance() < item.getPrice() || model.getBalance() == 0) { // Check if balance is 0
            view.displayInsufficientBalance();
            return;
        }
        // If the slot is out of stock display the view.
        if (slot.getQuantity() <= 0) {
            view.displayOutOfStock();
            return;
        }

        Map<Integer, Integer> change = model.buyItem(slotNumber); // Get the change from the model
        // Display the change in the view
        if (change != null) {
            view.displayDispensing(item);
            view.displayChange(change); // Display the change
        }
    }

    /**
    * Restock a slot and display the past and current states. This is useful for changing the position of an item
    * 
    * @param slotNumber - The slot to be restocked
    * @param quantity - The quantity of items to be restocked
    */
    public void restock(int slotNumber, int quantity) {
        // If the slot number is invalid display invalid slot number.
        if (slotNumber < 1 || slotNumber > model.getSlots().size()) {
            view.displayInvalidSlotNumber();
            return;
        }

        // Clone the current slots to keep the past state
        List<Slot> pastState = cloneSlots(model.getSlots());

        // Restock the slot
        model.restock(slotNumber, quantity);

        // Get the current state
        List<Slot> currentState = model.getSlots();

        // Display both the past state and the current state
        view.displayState(pastState, "Past State");
        view.displayState(currentState, "Current State");
    }

    /**
    * Clones slots to avoid memory leaks. This is used to ensure that we don't accidentally get slots that are in the middle of a game or the user's hands.
    * 
    * @param slots - The slots to clone. Must not be null.
    * 
    * @return A list of cloned slots. Never null but may be empty if there are no slots in the list
    */
    private List<Slot> cloneSlots(List<Slot> slots) {
        List<Slot> clonedSlots = new ArrayList<>();
        for (Slot slot : slots) {
            Item item = new Item(slot.getItem().getName(), slot.getItem().getPrice(), slot.getItem().getCalories(), slot.getItem().getType(), slot.getItem().getAction());
            Slot clonedSlot = new Slot(item, slot.getQuantity());
            clonedSlots.add(clonedSlot);
        }
        return clonedSlots;
    }

    /**
    * Sets the price of the item at the given slot. If the slot number is invalid the view will be notified and no action will be taken
    * 
    * @param slotNumber - The slot number of the item to set
    * @param price - The price in cents
    */
    public void setItemPrice(int slotNumber, int price) {
        // If the slot number is invalid display invalid slot number.
        if (slotNumber < 1 || slotNumber > model.getSlots().size()) {
            view.displayInvalidSlotNumber();
            return;
        }
        model.setItemPrice(slotNumber, price);
    }

    /**
    * Displays the collected payment in the view. It is called by the UI when the user clicks the collect
    */
    public void collectPayment() {
        view.displayCollected(model.collectPayment());
    }

    /**
    * Replenishes the denomination of the money. This is a no - op if the denomination is invalid.
    * 
    * @param denomination - the denomination to replenish.
    * @param quantity - the quantity to replenish ( must be positive )
    */
    public void replenishMoney(int denomination, int quantity) {
        // if the user has a change with the denomination key
        if (!model.getCoinBox().getChange().containsKey(denomination)) {
            view.displayInvalidDenomination();
            return;
        }
        model.replenishMoney(denomination, quantity);
    }

    /**
    * Displays a summary of transactions in the view. This is useful for debugging purposes as it will be displayed on the UI
    */
    public void printTransactionSummary() {
        view.displayTransactionSummary(model.getTransactionHistory());
    }
    /**
    * Displays the current machine stats in the view. This is called when the user clicks on the " Display " button
    */
    public void displayCurrentMachineStats() {
        view.displayMachineStats(model.getSlots(), model.getCoinBox());
    }
}

