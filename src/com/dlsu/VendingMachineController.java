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
    * Buy an item from the slot. This is called when the user clicks the buy button. The view will display the change to the view and reset the balance after buying the item
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
        // Display the change and reset the balance after giving change
        if (change != null) {
            view.displayDispensing(item);
            view.displayChange(change); // Display the change
            model.setBalance(0); // Reset the balance after giving change
        }
    }

    /**
    * Restock a slot. This does not check if the restock is valid or not. If it is the view will be updated with a message indicating that the restock was successful
    * 
    * @param slotNumber - The slot number to restock
    * @param quantity - The quantity of the restock to be made
    */
    public void restock(int slotNumber, int quantity) {
        // If the slot number is invalid display invalid slot number.
        if (slotNumber < 1 || slotNumber > model.getSlots().size()) {
            view.displayInvalidSlotNumber();
            return;
        }
        model.restock(slotNumber, quantity);
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
    * Collects payment from payment manager and sends it to the server. This method is called when user presses enter
    */
    public void collectPayment() {
        model.collectPayment();
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
    * Returns the vending machine model. Note that this is a copy of the model passed to #create ( String ) and not a copy of the original model that was passed to #create ( String ).
    * 
    * 
    * @return the vending machine model or null if there is no model to be created or it is not possible to
    */
    public VendingMachineModel getModel() {
        return model;
    }
}

