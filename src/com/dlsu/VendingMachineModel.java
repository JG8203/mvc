import java.util.List;
import java.util.Map;

public class VendingMachineModel {
    private List<Slot> slots;
    private int balance;
    private CoinBox coinBox;
    private TransactionHistory transactionHistory;

    public VendingMachineModel(List<Slot> slots) {
        this.slots = slots;
        this.balance = 0;
        this.coinBox = new CoinBox();
        this.transactionHistory = new TransactionHistory();
    }

    // Getters
    public List<Slot> getSlots() {
        return slots;
    }

    public int getBalance() {
        return balance;
    }

    public CoinBox getCoinBox() {
        return coinBox;
    }

    public TransactionHistory getTransactionHistory() {
        return transactionHistory;
    }

    // Setters
    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setCoinBox(CoinBox coinBox) {
        this.coinBox = coinBox;
    }

    public void setTransactionHistory(TransactionHistory transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public Map<Integer,Integer> buyItem(int slotNumber) {
        Slot slot = this.slots.get(slotNumber - 1);
        Item item = slot.getItem();
        if (this.balance < item.getPrice() || this.balance == 0) {
            return null;
        }
        if (slot.getQuantity() <= 0) {
            return null;
        }

        slot.setQuantity(slot.getQuantity() - 1); // Deduct the quantity of the item immediately after the checks

        int remainingBalanceAfterPurchase = this.balance - item.getPrice();
        Map<Integer, Integer> change = this.coinBox.returnChange(remainingBalanceAfterPurchase);

        if (change != null) {
            this.balance = remainingBalanceAfterPurchase;

            // After a successful transaction, add it to the history
            Transaction transaction = new Transaction(item, 1, item.getPrice());
            this.transactionHistory.addTransaction(transaction);
        } else {
            // If change can't be given, refund the item price and add the item quantity back
            this.balance += item.getPrice();
            slot.setQuantity(slot.getQuantity() + 1);
        }
        return change;
    }
    public void restock(int slotNumber, int quantity) {
        if (!isValidSlot(slotNumber) || !isValidQuantity(quantity)) {
            return;
        }

        Slot slot = slots.get(slotNumber - 1);
        int currentQuantity = slot.getQuantity();
        int maxQuantity = slot.getMaxQuantity();

        if (currentQuantity < maxQuantity) {
            int availableSpace = maxQuantity - currentQuantity;
            int restockQuantity = Math.min(availableSpace, quantity);
            slot.setQuantity(currentQuantity + restockQuantity);
        }
    }

    private boolean isValidSlot(int slotNumber) {
        return slotNumber >= 1 && slotNumber <= slots.size();
    }

    private boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }

    public void setItemPrice(int slotNumber, int price) {
        if (slotNumber < 1 || slotNumber > this.slots.size()) {
            return;
        }
        Slot slot = this.slots.get(slotNumber - 1);
        Item item = slot.getItem();
        item.setPrice(price);
    }

    public void collectPayment() {
        int collectedFunds = this.coinBox.getFunds();
        this.coinBox.setFunds(0);
    }

    public void replenishMoney(int denomination, int quantity) {
        if (this.coinBox.getChange().containsKey(denomination)) {
            this.coinBox.getChange().put(denomination, this.coinBox.getChange().get(denomination) + quantity);
        }
    }
}
