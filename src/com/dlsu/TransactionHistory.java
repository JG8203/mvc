import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionHistory {
    private List<Transaction> transactions;

    public TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public Transaction getTransaction(int index) {
        return this.transactions.get(index);
    }

    public List<Transaction> getTransactionsInDateRange(LocalDateTime start, LocalDateTime end) {
        return this.transactions.stream()
                .filter(transaction -> transaction.getTimestamp().isAfter(start) && transaction.getTimestamp().isBefore(end))
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
