import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionHistory {
    private List<Transaction> transactions;

    public TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    /**
    * Adds a transaction to the transaction list. This is a convenience method for adding transactions to the list of transactions that are to be included in the order they are added.
    * 
    * @param transaction - The transaction to add to the transaction list. It must be an instance of Transaction
    */
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    /**
    * Get transaction at index. This is used to determine if there is a transaction in the transaction list or not
    * 
    * @param index - index of the transaction to get
    * 
    * @return transaction at index or null if not found in the list ( index is out of range or invalid index
    */
    public Transaction getTransaction(int index) {
        return this.transactions.get(index);
    }

    /**
    * Returns a list of transactions in the specified date range. This is useful for debugging purposes. If you want to know which transactions are in a specific date range use #getTransactionsInDateRange ( java. util. Date )
    * 
    * @param start - the start date of the range
    * @param end - the end date of the range ( inclusive )
    * 
    * @return a list of transactions in the specified date range or an empty list if there are no transactions in the
    */
    public List<Transaction> getTransactionsInDateRange(LocalDateTime start, LocalDateTime end) {
        return this.transactions.stream()
                .filter(transaction -> transaction.getTimestamp().isAfter(start) && transaction.getTimestamp().isBefore(end))
                .collect(Collectors.toList());
    }

    /**
    * Returns a list of transactions. The list is sorted by date and with the most recent transactions appearing first.
    * 
    * 
    * @return a list of transactions or null if there are no transactions in the order they were added to the account
    */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
    * Sets the transactions to be included in the report. This is a fluent API and should not be used by third - party code.
    * 
    * @param transactions - a list of transactions to be included in the
    */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
