import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class VendingFeaturesView {
    private static final float CENTER_ALIGNMENT = 0;
    JFrame features;
    JButton displayItems;
    JButton insert;
    JButton buyItem;
    JButton customize;
    JButton back;

    JFrame insertPrompt;
    JTextField insertField;
    JButton submitInsert;

    JFrame buyPrompt;
    JTextField buyField;
    JButton submitBuy;



    public void displayMenu() {
        features = new JFrame("Vending Machine Factory");
        features.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        features.setSize(650, 800);
        features.setResizable(false);
        features.setLayout(null);

        displayItems = new JButton();
        displayItems.setText("Display Items");
        displayItems.setBorder(null);
        displayItems.setBackground(Color.gray);
        displayItems.setFocusable(false);
        displayItems.setBounds(450,400,200,50);

        insert = new JButton();
        insert.setText("Insert Money");
        insert.setBorder(null);
        insert.setBackground(Color.gray);
        insert.setFocusable(false);
        insert.setBounds(200,400,200,50);

        buyItem = new JButton();
        buyItem.setText("Buy Item");
        buyItem.setBorder(null);
        buyItem.setBackground(Color.gray);
        buyItem.setFocusable(false);
        buyItem.setBounds(0,400,200,50);

        customize = new JButton();
        customize.setText("Customize a product (Special Vending Machines only)");
        customize.setBorder(null);
        customize.setBackground(Color.gray);
        customize.setFocusable(false);
        customize.setBounds(0,400,200,50);

        back = new JButton();
        back.setText("Back");
        back.setBorder(null);
        back.setBackground(Color.gray);
        back.setFocusable(false);
        back.setBounds(450,725,200,50);

        features.add(displayItems);
        features.add(insert);
        features.add(buyItem);
        features.add(back);


        features.setVisible(true);

        System.out.println("==== Vending Features ====");
        System.out.println("[1] Display items");
        System.out.println("[2] Insert money");
        System.out.println("[3] Buy an item");
        System.out.println("[4] Customize a product (Special Vending Machines only)");
        System.out.println("[5] Back");
        System.out.print("Enter choice: ");
    }

    public void setDisplayItemsAction(ActionListener e){
        displayItems.addActionListener(e);
    }

    public void setInsertAction(ActionListener e){
        insert.addActionListener(e);
    }

    public void setBuyItemAction(ActionListener e){
        buyItem.addActionListener(e);
    }

    public void setCustomizeAction(ActionListener e){
        customize.addActionListener(e);
    }

    public void setBackAction(ActionListener e){
        back.addActionListener(e);
    }
    
    public void disposeInsertMoney(){
        insertPrompt.dispose();
    }
    public void displayInsertMoneyPrompt() {
        insertPrompt = new JFrame("Insert Bills");
        insertPrompt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        insertPrompt.setSize(600, 150);
        insertPrompt.setResizable(false);
        insertPrompt.setLayout(new BorderLayout());

        JPanel insertHeader = new JPanel();
        JLabel insertText = new JLabel("Enter the bills you want to insert, separated by spaces (e.g., '100 100 20'):");
        insertText.setFont(new Font("Arial", Font.BOLD, 12));
        insertHeader.add(insertText);
        insertHeader.setAlignmentX(CENTER_ALIGNMENT);

        insertField = new JTextField();
        insertField.setPreferredSize(new Dimension(300,50));

        submitInsert = new JButton();
        submitInsert.setText("Submit");
        submitInsert.setBorder(null);
        submitInsert.setBackground(Color.gray);
        submitInsert.setFocusable(false);
        submitInsert.setPreferredSize(new Dimension(250, 50));

        insertPrompt.add(insertHeader, BorderLayout.NORTH);
        insertPrompt.add(insertField, BorderLayout.CENTER);
        insertPrompt.add(submitInsert, BorderLayout.SOUTH);

        insertPrompt.setVisible(true);
        System.out.println("Enter the bills you want to insert, separated by spaces (e.g., '100 100 20'):");
    }

    public void setSubmitInsertAction(ActionListener e){
        submitInsert.addActionListener(e);
    }

    public String getInsertField(){
        return insertField.getText();
    }

    public void disposeItemPrompt(){
        insertPrompt.dispose();
    }
    
    public void displayBuyItemPrompt() {
        buyPrompt = new JFrame("Buy Item");
        buyPrompt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buyPrompt.setSize(600, 150);
        buyPrompt.setResizable(false);
        buyPrompt.setLayout(new BorderLayout());

        JPanel buyHeader = new JPanel();
        JLabel buyText = new JLabel("Enter the number of the item you want to buy:");
        buyText.setFont(new Font("Arial", Font.BOLD, 12));
        buyHeader.add(buyText);
        buyHeader.setAlignmentX(CENTER_ALIGNMENT);

        buyField = new JTextField();
        buyField.setPreferredSize(new Dimension(300,50));

        submitBuy = new JButton();
        submitBuy.setText("Submit");
        submitBuy.setBorder(null);
        submitBuy.setBackground(Color.gray);
        submitBuy.setFocusable(false);
        submitBuy.setPreferredSize(new Dimension(250, 50));

        buyPrompt.add(buyHeader, BorderLayout.NORTH);
        buyPrompt.add(buyField, BorderLayout.CENTER);
        buyPrompt.add(submitBuy, BorderLayout.SOUTH);

        buyPrompt.setVisible(true);
        System.out.println("Enter the number of the item you want to buy:");
    }

    public void setSubmitBuyAction(ActionListener e){
        submitBuy.addActionListener(e);
    }

    public int getBuyField(){
        return Integer.valueOf(buyField.getText());
    }

    public void disposeBuyPrompt(){
        buyPrompt.dispose();
    }
    
    public void displayCustomizeProductPrompt() {
        System.out.println("Enter the number of the base item you want to customize:");
    }
}
