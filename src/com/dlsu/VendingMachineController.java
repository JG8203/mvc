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

    public void displayItems() {
        view.displayItems(model.getSlots());
    }

    public void insertMoney(List<Integer> bills) {
        model.getCoinBox().addFunds(bills);
        model.setBalance(model.getBalance() + bills.stream().mapToInt(Integer::intValue).sum());
        view.displayBalance(model.getBalance());
    }
    
    public void buyItem(int slotNumber) {
        if (slotNumber < 1 || slotNumber > model.getSlots().size()) {
            view.displayInvalidSlotNumber();
            return;
        }
        Slot slot = model.getSlots().get(slotNumber - 1);
        Item item = slot.getItem();
        if (model.getBalance() < item.getPrice() || model.getBalance() == 0) { // Check if balance is 0
            view.displayInsufficientBalance();
            return;
        }
        if (slot.getQuantity() <= 0) {
            view.displayOutOfStock();
            return;
        }

        Map<Integer, Integer> change = model.buyItem(slotNumber); // Get the change from the model
        if (change != null) {
            view.displayDispensing(item);
            view.displayChange(change); // Display the change
        }
    }

    public void restock(int slotNumber, int quantity) {
        if (slotNumber < 1 || slotNumber > model.getSlots().size()) {
            view.displayInvalidSlotNumber();
            return;
        }
        model.restock(slotNumber, quantity);
    }

    public void setItemPrice(int slotNumber, int price) {
        if (slotNumber < 1 || slotNumber > model.getSlots().size()) {
            view.displayInvalidSlotNumber();
            return;
        }
        model.setItemPrice(slotNumber, price);
    }

    public void collectPayment() {
        model.collectPayment();
    }

    public void replenishMoney(int denomination, int quantity) {
        if (!model.getCoinBox().getChange().containsKey(denomination)) {
            view.displayInvalidDenomination();
            return;
        }
        model.replenishMoney(denomination, quantity);
    }

    public void printTransactionSummary() {
        view.displayTransactionSummary(model.getTransactionHistory());
    }
}

