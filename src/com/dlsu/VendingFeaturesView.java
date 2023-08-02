import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;

import javax.swing.*;

public class VendingFeaturesView {
    private static final float CENTER_ALIGNMENT = 0;
    private static final int CENTER = 0;
    JFrame features;
    //JButton displayItems;
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

    JFrame customizePrompt;
    JTextField customizeField;
    JButton submitCustomize;



    public void displayMenu(List<Slot> slots) {
        features = new JFrame("Vending Machine Factory");
        features.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        features.setSize(650, 800);
        features.setResizable(false);
        features.setLayout(new FlowLayout());

        /*displayItems = new JButton();
        displayItems.setText("Display Items");
        displayItems.setBorder(null);
        displayItems.setBackground(Color.gray);
        displayItems.setFocusable(false);
        displayItems.setBounds(450,400,200,50);*/

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
        customize.setBounds(0,600,400,50);

        back = new JButton();
        back.setText("Back");
        back.setBorder(null);
        back.setBackground(Color.gray);
        back.setFocusable(false);
        back.setBounds(450,725,200,50);

        JPanel stock = new JPanel();
        stock.setLayout(new GridLayout(slots.size(), 2, 5, 5));
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

            info.add(name, BorderLayout.NORTH);
            info.add(price);
            info.add(calories);
            info.add(slotAmount);

            stock.add(info);

        }
        features.add(stock);
        //features.add(displayItems);
        features.add(insert);
        features.add(buyItem);
        features.add(customize);
        features.add(back);


        features.setVisible(true);
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
        insertPrompt.setResizable(false);
        insertPrompt.setLayout(new BorderLayout());

        JPanel insertHeader = new JPanel();
        JLabel insertText = new JLabel("Enter the bills you want to insert by clicking the buttons:");
        insertText.setFont(new Font("Arial", Font.BOLD, 12));
        insertHeader.add(insertText);
        insertHeader.setAlignmentX(CENTER_ALIGNMENT);

        insertField = new JTextField();
        insertField.setPreferredSize(new Dimension(300, 50));
        insertField.setEditable(false);
        insertField.setVisible(false);

        JPanel buttonsPanel = new JPanel();
        int[] denominations = {1, 5, 10, 20, 100, 200, 500, 1000};
        StringBuilder stringBuilder = new StringBuilder();

        JPanel denominationsPanel = new JPanel();
        denominationsPanel.setLayout(new GridLayout(denominations.length, 1));
        JLabel[] denominationLabels = new JLabel[denominations.length];
        int[] denominationCounts = new int[denominations.length];

        for (int i = 0; i < denominations.length; i++) {
            int denomination = denominations[i];
            JButton button = new JButton(String.valueOf(denomination));
            button.setPreferredSize(new Dimension(100, 50));
            final int index = i;
            button.addActionListener(e -> {
                denominationCounts[index]++;
                denominationLabels[index].setText(denomination + " x " + denominationCounts[index]);
                stringBuilder.append(denomination).append(" ");
                insertField.setText(stringBuilder.toString().trim());
            });
            buttonsPanel.add(button);

            denominationLabels[i] = new JLabel(denomination + " x " + 0);
            denominationsPanel.add(denominationLabels[i]);
        }

        submitInsert = new JButton();
        submitInsert.setText("Submit");
        submitInsert.setBorder(null);
        submitInsert.setBackground(Color.gray);
        submitInsert.setFocusable(false);
        submitInsert.setPreferredSize(new Dimension(250, 50));

        insertPrompt.add(insertHeader, BorderLayout.NORTH);
        insertPrompt.add(insertField, BorderLayout.CENTER);
        insertPrompt.add(buttonsPanel, BorderLayout.WEST);
        insertPrompt.add(denominationsPanel, BorderLayout.EAST);
        insertPrompt.add(submitInsert, BorderLayout.SOUTH);

        insertPrompt.pack(); // Adjusts the frame size to fit the content
        insertPrompt.setLocationRelativeTo(null); // Centers the frame on the screen
        insertPrompt.setVisible(true);
        System.out.println("Enter the bills you want to insert by clicking the buttons:");
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
    
    public void disposeCustomizeProductPrompt(){
        customizePrompt.dispose();
    }
    public void displayCustomizeProductPrompt() {
        customizePrompt = new JFrame("Customize Item");
        customizePrompt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customizePrompt.setSize(600, 150);
        customizePrompt.setResizable(false);
        customizePrompt.setLayout(new BorderLayout());

        JPanel customizeHeader = new JPanel();
        JLabel customizeText = new JLabel("Enter the number of the base item you want to customize:");
        customizeText.setFont(new Font("Arial", Font.BOLD, 12));
        customizeHeader.add(customizeText);
        customizeHeader.setAlignmentX(CENTER_ALIGNMENT);

        customizeField = new JTextField();
        customizeField.setPreferredSize(new Dimension(300,50));

        submitCustomize = new JButton();
        submitCustomize.setText("Submit");
        submitCustomize.setBorder(null);
        submitCustomize.setBackground(Color.gray);
        submitCustomize.setFocusable(false);
        submitCustomize.setPreferredSize(new Dimension(250, 50));

        customizePrompt.add(customizeHeader, BorderLayout.NORTH);
        customizePrompt.add(customizeField, BorderLayout.CENTER);
        customizePrompt.add(submitCustomize, BorderLayout.SOUTH);

        customizePrompt.setVisible(true);
    }

    public void setSubmitCustomizeAction(ActionListener e){
        submitCustomize.addActionListener(e);
    }

    public int getCustomizeField(){
        return Integer.valueOf(customizeField.getText());
    }

    public void disposeCustomizePrompt(){
        customizePrompt.dispose();
    }

    public void disposeFeatures(){
        features.dispose();
    }

    public void displayInvalidChoice(){
        JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.", "Vending Machine", JOptionPane.ERROR_MESSAGE, null);
    }
}
