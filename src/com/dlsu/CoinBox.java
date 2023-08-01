import java.util.*;

/**
 * The CoinBox class represents a box for storing coins and managing funds. It keeps track of the available funds
 * and the change (coins) available in the box. This class allows adding funds, deducting funds, and returning
 * change for a given amount.
 */
public class CoinBox {
    private int funds;
    private Map<Integer, Integer> change = new HashMap<>();

    /**
     * Constructs a new CoinBox with default funds set to 0 and initializes the available change denominations.
     * The default change denominations and their initial quantities are 1, 5, 10, 20, 100, 200, 500, and 1000.
     */
    public CoinBox() {
        this.funds = 0;
        this.change.put(1, 100);
        this.change.put(5, 100);
        this.change.put(10, 100);
        this.change.put(20, 100);
        this.change.put(0, 100);
        this.change.put(200, 100);
        this.change.put(500, 100);
        this.change.put(00, 100);
        this.change.put(00, 100);
    }

    /**
     * Adds funds to the CoinBox and increments the quantity of the corresponding change denomination.
     *
     * @param bills A list of integers representing the bills to be added as funds.
     */
    public void addFunds(List<Integer> bills) {
        try {
            for (int bill : bills) {
                this.funds += bill;
                this.change.put(bill, this.change.get(bill) + 1);
            }
        } catch (Exception e){
            throw e;
        }
    }

    /**
     * Deducts a specific amount from the available funds in the CoinBox.
     *
     * @param amount The amount to be deducted from the available funds.
     */
    public void deductFunds(int amount) {
        this.funds -= amount;
    }

    /**
     * Returns the change (coins) for a given amount if available in the CoinBox.
     *
     * @param amount The amount for which change needs to be returned.
     * @return A map containing the change denominations as keys and their corresponding quantities as values,
     *         or null if there is not enough change to fulfill the request.
     */
    public Map<Integer, Integer> returnChange(int amount) {
        List<Integer> denominations = new ArrayList<>(this.change.keySet());
        Collections.sort(denominations, Collections.reverseOrder());
        Map<Integer, Integer> change = new HashMap<>();
        int remaining = amount;

        for (int denom : denominations) {
            while (remaining >= denom && this.change.get(denom) > 0) {
                change.put(denom, change.getOrDefault(denom, 0) + 1);
                this.change.put(denom, this.change.get(denom) - 1);
                remaining -= denom;
            }
        }

        if (remaining > 0) {
            System.out.println("Sorry, not enough change. Transaction cancelled.");
            this.funds -= amount;
            return null;
        }

        this.funds -= amount;
        return change;
    }

    /**
     * Gets the available funds in the CoinBox.
     *
     * @return The available funds in the CoinBox.
     */
    public int getFunds() {
        return funds;
    }

    /**
     * Sets the available funds in the CoinBox.
     *
     * @param funds The new value for the available funds.
     */
    public void setFunds(int funds) {
        this.funds = funds;
    }

    /**
     * Gets the map representing the available change denominations and their quantities in the CoinBox.
     *
     * @return A map containing the change denominations as keys and their corresponding quantities as values.
     */
    public Map<Integer, Integer> getChange() {
        return change;
    }

    /**
     * Sets the available change denominations and their quantities in the CoinBox.
     *
     * @param change A map containing the change denominations as keys and their corresponding quantities as values.
     */
    public void setChange(Map<Integer, Integer> change) {
        this.change = change;
    }
}
