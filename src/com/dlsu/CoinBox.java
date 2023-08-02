import java.util.*;

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

    public void addFunds(List<Integer> bills) {
        for (int bill : bills) {
            this.funds += bill;
            this.change.put(bill, this.change.get(bill) + 1);
        }
    }

    public void deductFunds(int amount) {
        this.funds -= amount;
    }

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
            // Add the deducted coins back to the change map
            for (Map.Entry<Integer, Integer> entry : change.entrySet()) {
                this.change.put(entry.getKey(), this.change.get(entry.getKey()) + entry.getValue());
            }
            return null;
        }

        this.funds -= amount;
        return change;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }

    public Map<Integer, Integer> getChange() {
        return change;
    }

    public void setChange(Map<Integer, Integer> change) {
        this.change = change;
    }
}
