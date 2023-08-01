import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class VendingMachineView {


    public void displayItems(List<Slot> slots) {
        for (int i = 0; i < slots.size(); i++) {
            Slot slot = slots.get(i);
            Item item = slot.getItem();
            String itemDesc = i+1+". "+item.getName()+" - Price: PHP"+item.getPrice()+", Calories: "+item.getCalories()+", Quantity: "+slot.getQuantity();
            //System.out.printf("%d. %s - Price: PHP%d, Calories: %d, Quantity: %d%n",
                    //i + 1, item.getName(), item.getPrice(), item.getCalories(), slot.getQuantity());
            System.out.println(itemDesc);

        }
    }

    public void displayBalance(int balance) {
        JOptionPane.showMessageDialog(null, "Total balance: PHP"+balance, "Vending Machine", JOptionPane.INFORMATION_MESSAGE, null);
        System.out.printf("Total balance: PHP%d%n", balance);
    }

    public void displayInsufficientBalance() {
        JOptionPane.showMessageDialog(null, "Insufficient balance.", "Vending Machine", JOptionPane.ERROR_MESSAGE, null);
        System.out.println("Insufficient balance.");
    }

    public void displayOutOfStock() {
        JOptionPane.showMessageDialog(null, "Sorry, this item is out stock.", "Vending Machine", JOptionPane.ERROR_MESSAGE, null);
        System.out.println("Sorry, this item is out of stock.");
    }

    public void displayDispensing(Item item) {
        System.out.printf("Dispensing %s...%n", item.getName());
    }

    public void displayTransactionSummary(List<Slot> slots) {
        System.out.println("Items:");
        for (Slot slot : slots) {
            Item item = slot.getItem();
            System.out.printf("%s: %d (price: %d)%n", item.getName(), slot.getQuantity(), item.getPrice());
        }
    }

    public void displayInvalidSlotNumber() {
        JOptionPane.showMessageDialog(null, "Invalid slot number.", "Vending Machine", JOptionPane.ERROR_MESSAGE, null);
        System.out.println("Invalid slot number.");
    }

    public void displayInvalidDenomination() {
        JOptionPane.showMessageDialog(null, "Invalid denomination.", "Vending Machine", JOptionPane.ERROR_MESSAGE, null);
        System.out.println("Invalid denomination.");
    }
    
    public void displayChange(Map<Integer, Integer> change) {
        System.out.println("Your change:");
        for (Map.Entry<Integer, Integer> entry : change.entrySet()) {
            System.out.println("Denomination: " + entry.getKey() + ", Quantity: " + entry.getValue());
        }
    }
}


