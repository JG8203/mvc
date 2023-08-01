import java.util.List;
import java.util.Map;
/**
 * Represents the Vending Machine's model that contains the core functionality and state.
 */
public class VendingMachineModel {
    private List<Slot> slots;
    private int balance;
    private CoinBox coinBox;
    private TransactionHistory transactionHistory;
    /**
     * Constructs a new VendingMachineModel with the given slots.
     *
     * @param slots The list of slots that holds items in the vending machine.
     */
    public VendingMachineModel(List<Slot> slots) {
        this.slots = slots;
        this.balance = 0;
        this.coinBox = new CoinBox();
        this.transactionHistory = new TransactionHistory();
    }

    /**
     * Returns the list of slots in the vending machine.
     *
     * @return The list of slots.
     */
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

    /**
    * Sets the balance of the user. This is used to determine how much to pay per day for the user
    * 
    * @param balance - the balance of the
    */
    public void setBalance(int balance) {
        this.balance = balance;
    }
    /**
     * Attempts to buy an item from a specified slot number.
     * Deducts balance, updates slot quantity, and returns change if applicable.
     *
     * @param slotNumber The number of the slot to buy from.
     * @return A map representing the denominations and quantity of change, or null if purchase is not possible.
     */
    /**
    * Buy an item from a slot. This is a two step process. First the item is deducted from the quantity of the item immediately after the checks are performed. If there is a change it is added to the transaction history.
    * 
    * @param slotNumber - The slot number of the item to be bought
    * 
    * @return Map with the change or null if nothing
    */
    public Map<Integer,Integer> buyItem(int slotNumber) {
        try {
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

            // This method is called when a transaction is received.
            if (change != null) {
                this.balance -= item.getPrice();

                // After a successful transaction, add it to the history
                Transaction transaction = new Transaction(item, 1, item.getPrice());
                this.transactionHistory.addTransaction(transaction);
            } else {
                // If change can't be given, refund the item price and add the item quantity back
                this.balance += item.getPrice();
                slot.setQuantity(slot.getQuantity() + 1);
            }
            return change;
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Invalid slot number: " + slotNumber);
            return null;
        }
    }
    /**
    * Restock a slot by a given amount. This is useful for reserving an item in the inventory
    * 
    * @param slotNumber - the slot number to restock
    * @param quantity - the amount to restock ( negative for unreal
    */
    public void restock(int slotNumber, int quantity) {
        try {
            Slot slot = this.slots.get(slotNumber - 1);
            slot.setQuantity(slot.getQuantity() + quantity);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Invalid slot number: " + slotNumber);
        }
    }

    /**
    * Sets the price of an item in a slot. This is useful for making items with different price but not all of them have the same price
    * 
    * @param slotNumber - The slot number to set the price of
    * @param price - The price to set the item to in the
    */
    public void setItemPrice(int slotNumber, int price) {
        try {
            Slot slot = this.slots.get(slotNumber - 1);
            Item item = slot.getItem();
            item.setPrice(price);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Invalid slot number: " + slotNumber);
        }
    }
    /**
     * Collects payment from the coin box, resetting its funds to zero.
     */
    /**
    * Collects funds from coins box and resets them to 0. This is useful for testing and to ensure that payments are collected in a consistent order
    * 
    * 
    * @return the amount of funds
    */
    public int collectPayment() {
        int collectedFunds = this.coinBox.getFunds();
        this.coinBox.setFunds(0);
        return collectedFunds;
    }
    /**
     * Replenishes money in the coin box by adding a specified quantity of a particular denomination.
     *
     * @param denomination The denomination of the money to add.
     * @param quantity     The quantity of the money to add.
     */
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
}
