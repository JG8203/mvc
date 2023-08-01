import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The TransactionHistory class represents a collection of transactions made during vending machine operations.
 * It allows adding new transactions, retrieving transactions by index, and filtering transactions based on date ranges.
 */
public class TransactionHistory {
    private List<Transaction> transactions;

    /**
     * Constructs a TransactionHistory object with an empty list of transactions.
     */
    public TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    /**
     * Adds a new transaction to the transaction history.
     *
     * @param transaction The Transaction object to be added to the history.
     */
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    /**
     * Retrieves a transaction at the specified index from the transaction history.
     *
     * @param index The index of the transaction to be retrieved.
     * @return The Transaction object at the specified index.
     */
    public Transaction getTransaction(int index) {
        return this.transactions.get(index);
    }

    /**
     * Filters and retrieves a list of transactions that fall within the specified date range.
     * Transactions with timestamps after the start date (exclusive) and before the end date (exclusive) are included.
     *
     * @param start The start date of the date range.
     * @param end   The end date of the date range.
     * @return A list of transactions within the specified date range.
     */
    public List<Transaction> getTransactionsInDateRange(LocalDateTime start, LocalDateTime end) {
        return this.transactions.stream()
                .filter(transaction -> transaction.getTimestamp().isAfter(start) && transaction.getTimestamp().isBefore(end))
                .collect(Collectors.toList());
    }

    /**
     * Gets the list of all transactions in the transaction history.
     *
     * @return A list containing all transactions in the history.
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }
}
