import java.util.ArrayList;
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
    /**
    * Returns the list of slots. This is a copy of the list returned by #getSlots ().
    * 
    * 
    * @return a list of slots in this game's state sorted by position in the list of slots. Note that the order of the slots is undefined
    */
    public List<Slot> getSlots() {
        return slots;
    }

    /**
    * Returns the balance of the account. This is an integer in the range 0 to 100 inclusive. For example 0. 5 would be the balance of the bank account and 100 would be the balance of the bank account itself.
    * 
    * 
    * @return the balance of the account ( int ) or - 1 if there is no balance in the account ( negative
    */
    public int getBalance() {
        return balance;
    }

    /**
    * Returns the CoinBox associated with this account. Note that this does not include the coin itself but is used to determine the type of coin that is to be used in the account.
    * 
    * 
    * @return the CoinBox associated with this account or null if there is none in which case it is an error
    */
    public CoinBox getCoinBox() {
        return coinBox;
    }

    /**
    * Returns the transaction history. Note that this does not include transactions that have been committed or rolled back.
    * 
    * 
    * @return the transaction history never null but may be empty if there are no transactions in the transaction history. If the transaction history is empty it will return
    */
    public TransactionHistory getTransactionHistory() {
        return transactionHistory;
    }

    // Setters
    /**
    * Sets the slots to be used. This is a convenience method for overriding the slots in the slot manager
    * 
    * @param slots - List of slots to
    */
    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    /**
    * Sets the balance of the user. This is used to determine how much to pay per day for the user
    * 
    * @param balance - the balance of the
    */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
    * Sets the coin box. Note that this can be different from the value set when creating the Coin object
    * 
    * @param coinBox - the coin box to
    */
    public void setCoinBox(CoinBox coinBox) {
        this.coinBox = coinBox;
    }

    /**
    * Sets the transaction history. This is used to track changes to the state of the transaction and not to update the history of the transaction that is in progress
    * 
    * @param transactionHistory - the transaction history to
    */
    public void setTransactionHistory(TransactionHistory transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    /**
    * Buy an item from a slot. This is a two step process. First the item is deducted from the quantity of the item immediately after the checks are performed. If there is a change it is added to the transaction history.
    * 
    * @param slotNumber - the slot number of the item to be bought
    * 
    * @return a map of the change that took place or null if nothing was done. The key is the amount of money that was purchased
    */
    public Map<Integer,Integer> buyItem(int slotNumber) {
        Slot slot = this.slots.get(slotNumber - 1);
        Item item = slot.getItem();
        // Returns null if the item is not balance or if the item is less than the price or if the item is not balance 0.
        if (this.balance < item.getPrice() || this.balance == 0) {
            return null;
        }
        // Returns null if the slot is not empty.
        if (slot.getQuantity() <= 0) {
            return null;
        }

        slot.setQuantity(slot.getQuantity() - 1); // Deduct the quantity of the item immediately after the checks

        int remainingBalanceAfterPurchase = this.balance - item.getPrice();
        Map<Integer, Integer> change = this.coinBox.returnChange(remainingBalanceAfterPurchase);

        // If change is null refund the item price back to the history.
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
    /**
    * Restocks the given quantity of the slot. This is useful for resizing an item that is in the middle of a ship or to re - arrange the item in the middle of a shooting.
    * 
    * @param slotNumber - the slot number of the item to restock
    * @param quantity - the quantity to restock
    */
    public void restock(int slotNumber, int quantity) {
        // Checks if the slot number and quantity are valid.
        if (!isValidSlot(slotNumber) || !isValidQuantity(quantity)) {
            return;
        }

        Slot slot = slots.get(slotNumber - 1);
        int currentQuantity = slot.getQuantity();
        int maxQuantity = slot.getMaxQuantity();

        // Set the quantity of the slot to the maxQuantity.
        if (currentQuantity < maxQuantity) {
            int availableSpace = maxQuantity - currentQuantity;
            int restockQuantity = Math.min(availableSpace, quantity);
            slot.setQuantity(currentQuantity + restockQuantity);
        }
    }

    /**
    * Checks if the slot number is valid. This is used to determine if we are going to insert a new slot or not
    * 
    * @param slotNumber - The slot number to check
    * 
    * @return whether or not the slot number is valid or not ( 1 < = slotNumber < = slots. size
    */
    private boolean isValidSlot(int slotNumber) {
        return slotNumber >= 1 && slotNumber <= slots.size();
    }

    /**
    * Checks if the quantity is valid. This is used to determine if we are dealing with a non - negative quantity.
    * 
    * @param quantity - The quantity to check. Must be greater than 0.
    * 
    * @return True if the quantity is valid false otherwise. Note that the quantity is valid if it is greater than 0
    */
    private boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }

    /**
    * Sets the price of the item at the given slot. This does not update the position in the game.
    * 
    * @param slotNumber - The slot to set the price of.
    * @param price - The price to set the item at the given slot
    */
    public void setItemPrice(int slotNumber, int price) {
        // Check if the slot number is within the slot number.
        if (slotNumber < 1 || slotNumber > this.slots.size()) {
            return;
        }
        Slot slot = this.slots.get(slotNumber - 1);
        Item item = slot.getItem();
        item.setPrice(price);
    }

    /**
     * Collects funds from coin box and sets them to 0. This is useful for testing purposes to ensure that payments are collected
     *
     * @return
     */
    public int collectPayment() {
        int collectedFunds = this.coinBox.getFunds();
        this.coinBox.setFunds(0);
        return collectedFunds;
    }

    /**
    * Replenish money by denomination. Does nothing if there is no money to replenish.
    * 
    * @param denomination - denomination of money to replenish
    * @param quantity - amount of money to replenish ( must be positive
    */
    public void replenishMoney(int denomination, int quantity) {
        // Adds a change to the change list.
        if (this.coinBox.getChange().containsKey(denomination)) {
            this.coinBox.getChange().put(denomination, this.coinBox.getChange().get(denomination) + quantity);
        }
    }
    public List<Slot> captureState() {
        List<Slot> state = new ArrayList<>();
        for (Slot slot : this.slots) {
            state.add(slot.clone()); // Assuming Slot class has a clone method to create a deep copy
        }
        return state;
    }

}
