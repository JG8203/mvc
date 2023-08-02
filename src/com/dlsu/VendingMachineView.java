import java.util.List;
import java.util.Map;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.security.auth.login.CredentialExpiredException;
import javax.swing.*;

public class VendingMachineView {
    protected static final int CENTER = 0;
    JFrame items;
    JFrame transactions;
    JFrame dispensing;
    JFrame displayChange;

    public void displayItems(List<Slot> slots) {
        items = new JFrame();
        items.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        items.setSize(new Dimension(500,400));
        items.setResizable(false);
        items.setLayout(new GridLayout(slots.size(), 2, 5, 5));
        items.setTitle("Display Items");
        
        for (int i = 0; i < slots.size(); i++) {

            Slot slot = slots.get(i);
            Item item = slot.getItem();
            JPanel info = new JPanel();
            //info.setLayout(new GridLayout(4, 1, 5, 5));
            //info.setBackground(Color.gray);

            JPanel name = new JPanel();
            JLabel nameLabel = new JLabel(i+1 +". "+item.getName());
            nameLabel.setHorizontalAlignment(CENTER);
            name.add(nameLabel);
            

            JPanel price = new JPanel();
            JLabel priceLabel = new JLabel("Price: PHP"+item.getPrice());
            priceLabel.setHorizontalAlignment(CENTER);
            price.add(priceLabel);

            JPanel calories = new JPanel();
            JLabel caloriesLabel = new JLabel("Calories: "+item.getCalories());
            caloriesLabel.setHorizontalAlignment(CENTER);
            calories.add(caloriesLabel);

            JPanel slotAmount = new JPanel();
            JLabel slotAmountLabel = new JLabel("Quantity: "+slot.getQuantity());
            slotAmountLabel.setHorizontalAlignment(CENTER);
            slotAmount.add(slotAmountLabel);

            info.add(name);
            info.add(price);
            info.add(calories);
            info.add(slotAmount);

            items.add(info);

        }
        items.setVisible(true);
    }

    public void displayBalance(int balance) {
        JOptionPane.showMessageDialog(null, "Total balance: PHP"+balance, "Vending Machine", JOptionPane.INFORMATION_MESSAGE, null);
    }

    public void displayInsufficientBalance() {
        JOptionPane.showMessageDialog(null, "Insufficient balance.", "Vending Machine", JOptionPane.ERROR_MESSAGE, null);
    }

    public void displayOutOfStock() {
        JOptionPane.showMessageDialog(null, "Sorry, this item is out stock.", "Vending Machine", JOptionPane.ERROR_MESSAGE, null);
    }

    public void displayDispensing(Item item) {
        JOptionPane.showMessageDialog(null, "Dispensing "+item.getName(), "Vending Machine", JOptionPane.INFORMATION_MESSAGE, null);
    }

    public void displayTransactionSummary(TransactionHistory transactionHistory) {
        transactions = new JFrame();
        transactions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        transactions.setSize(new Dimension(800,400));
        transactions.setResizable(false);
        transactions.setLayout(new GridLayout(transactionHistory.getTransactions().size(), 2, 5, 5));
        transactions.setTitle("Items");

        System.out.println("Items:");
        for (Transaction transaction : transactionHistory.getTransactions()) {

            JPanel item = new JPanel();

            JPanel name = new JPanel();
            JLabel nameLabel = new JLabel("Item: "+transaction.getItem().toString()+
            ", Quantity: "+transaction.getQuantity()+", Total Price: "+transaction.getTotalPrice()+", Timestamp: "+transaction.getTimestamp());
            nameLabel.setHorizontalAlignment(CENTER);
            name.add(nameLabel);
            
            item.add(name);
            transactions.add(item);
        }
        transactions.setVisible(true);
    }

    public void displayInvalidSlotNumber() {
        JOptionPane.showMessageDialog(null, "Invalid slot number.", "Vending Machine", JOptionPane.ERROR_MESSAGE, null);
    }

    public void displayInvalidDenomination() {
        JOptionPane.showMessageDialog(null, "Invalid denomination.", "Vending Machine", JOptionPane.ERROR_MESSAGE, null);
    }
    
    public void displayChange(Map<Integer, Integer> change) {
        System.out.println("Your change:");
        displayChange = new JFrame();
        displayChange.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayChange.setSize(new Dimension(500,200));
        displayChange.setResizable(false);
        displayChange.setLayout(new GridLayout(change.entrySet().size(), 2, 5, 5));
        displayChange.setTitle("Your Change");
        for (Map.Entry<Integer, Integer> entry : change.entrySet()) {
            JPanel name = new JPanel();
            JLabel nameLabel = new JLabel("Denomination: " + entry.getKey() + ", Quantity: " + entry.getValue());
            nameLabel.setHorizontalAlignment(CENTER);
            name.add(nameLabel);
            displayChange.add(name);

        }
        displayChange.setVisible(true);
    }
}


