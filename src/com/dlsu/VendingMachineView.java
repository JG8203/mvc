import java.util.List;
import java.util.Map;

public class VendingMachineView {

    public void displayItems(List<Slot> slots) {
        for (int i = 0; i < slots.size(); i++) {
            Slot slot = slots.get(i);
            Item item = slot.getItem();
            System.out.printf("%d. %s - Price: PHP%d, Calories: %d, Quantity: %d%n",
                    i + 1, item.getName(), item.getPrice(), item.getCalories(), slot.getQuantity());
        }
    }
    public void displayBalance(int balance) {
        System.out.printf("Total balance: PHP%d%n", balance);
    }

    public void displayInsufficientBalance() {
        System.out.println("Insufficient balance.");
    }

    public void displayOutOfStock() {
        System.out.println("Sorry, this item is out of stock.");
    }

    public void displayDispensing(Item item) {
        System.out.printf("Dispensing %s...%n", item.getName());
    }

    public void displayTransactionSummary(List<Slot> slots) {
        System.out.println("Items:");
        for (Slot slot : slots) {
            Item item = slot.getItem();
            System.out.printf("%s: %d (price: %d)%n", item.getName(), slot.getQuantity(), item.getPrice());
        }
    }

    public void displayInvalidSlotNumber() {
        System.out.println("Invalid slot number.");
    }

    public void displayInvalidDenomination() {
        System.out.println("Invalid denomination.");
    }
    public void displayChange(Map<Integer, Integer> change) {
        System.out.println("Your change:");
        for (Map.Entry<Integer, Integer> entry : change.entrySet()) {
            System.out.println("Denomination: " + entry.getKey() + ", Quantity: " + entry.getValue());
        }
    }
}


