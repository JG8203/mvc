import java.util.*;
/**
 * The CoinBox class represents a virtual coin box that manages funds and change denominations.
 * Users can add funds, deduct funds, and request change from the coin box.
 * The coin box maintains a collection of change denominations and their respective quantities.
 * It also keeps track of the total funds in the account.
 *
 * The class provides methods to:
 * - Add funds to the account by incrementing the change counters for each denomination.
 * - Deduct funds from the account to prevent unauthorized access to money.
 * - Return change by calculating the denominations to be deducted from the account based on the requested amount.
 * - Get the current amount of funds in the account, which may be negative in case of charges or debits.
 * - Set the amount of funds in the account, which is used to determine the money transferred to the bank during purchase.
 * - Get and set the map of change denominations and their respective quantities used for making change.
 *
 * Note: The class assumes the availability of certain fixed denominations (1, 5, 10, 20, 100, 200, 500, and 1000),
 * with their respective initial quantities set to 100 in the coin box.
 *
 */
public class CoinBox {
    private int funds;
    private Map<Integer, Integer> change = new HashMap<>();

    public CoinBox() {
        this.funds = 0;
        this.change.put(1, 100);
        this.change.put(5, 100);
        this.change.put(10, 100);
        this.change.put(20, 100);
        this.change.put(100, 100);
        this.change.put(200, 100);
        this.change.put(500, 100);
        this.change.put(1000, 100);
    }

    /**
    * Adds funds to the account. This is done by incrementing the change counter. It's a bit tricky because we don't need to know the amount of bills that have been added since the last update.
    * 
    * @param bills - The list of bills to add to the account
    */
    public void addFunds(List<Integer> bills) {
        for (int bill : bills) {
            this.funds += bill;
            this.change.put(bill, this.change.get(bill) + 1);
        }
    }

    /**
    * Deduct funds from the account. This is used to prevent an attacker from stealing money in the account
    * 
    * @param amount - Amount of funds to
    */
    public void deductFunds(int amount) {
        this.funds -= amount;
    }

    /**
    * Returns the change that was made to the account. This is done by taking the denominations of coins in the account and adding them back to the change
    * 
    * @param amount - The amount of coins to return
    * 
    * @return A map that contains the amount of coins that were deducted back to the account or null if there was
    */
    public Map<Integer, Integer> returnChange(int amount) {
        List<Integer> denominations = new ArrayList<>(this.change.keySet());
        Collections.sort(denominations, Collections.reverseOrder());
        Map<Integer, Integer> change = new HashMap<>();
        int remaining = amount;

        for (int denom : denominations) {
            // This method is used to move the current change to the next change.
            while (remaining >= denom && this.change.get(denom) > 0) {
                change.put(denom, change.getOrDefault(denom, 0) + 1);
                this.change.put(denom, this.change.get(denom) - 1);
                remaining -= denom;
            }
        }

        // This method will remove the amount of coins from the transaction.
        if (remaining > 0) {
            System.out.println("Sorry, not enough change. Transaction cancelled.");
            this.funds -= amount;
            // Add the deducted coins back to the change map
            for (Map.Entry<Integer, Integer> entry : change.entrySet()) {
                this.change.put(entry.getKey(), this.change.get(entry.getKey()) + entry.getValue());
            }
            return null;
        }

        this.funds -= amount;
        return change;
    }

    /**
    * Returns the funds of the account. This is an integer and can be negative to indicate that the account has been charged for some reason.
    * 
    * 
    * @return the amount of funds of the account or - 1 if there is no funds in the account
    */
    public int getFunds() {
        return funds;
    }

    /**
    * Sets the funds of the contract. This is used to determine how much money will be transferred to the bank when it is purchased.
    * 
    * @param funds - The amount of funds to transfer to the bank
    */
    public void setFunds(int funds) {
        this.funds = funds;
    }

    /**
    * Returns the map of changes. The key is the index of the change and the value is the index of the change that occurred.
    * 
    * 
    * @return a map of changes indexed by the change number and the value is the index of the change that occurred
    */
    public Map<Integer, Integer> getChange() {
        return change;
    }

    /**
    * Sets the change to be used in #get ( int ). Note that this is a map of key - value pairs that are used to determine the order of the change ( in order ).
    * 
    * @param change - a map of key - value pairs that are used to determine the order of the
    */
    public void setChange(Map<Integer, Integer> change) {
        this.change = change;
    }
}
