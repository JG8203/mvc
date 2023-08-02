import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SpecialVendingMachineView extends VendingMachineView {
    private static final float CENTER_ALIGNMENT = 0;

    JFrame added;

    JFrame select;
    JTextField selectField;
    JButton submitSelect;

    public void displayAddOns(List<Item> addOns) {
        added = new JFrame();
        added.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        added.setSize(new Dimension(500,400));
        added.setResizable(false);
        added.setLayout(new GridLayout(addOns.size(), 2, 5, 5));
        added.setTitle("Available Add-Ons");
        
        added.setVisible(true);
        System.out.println("Available add-ons:");
        for (int i = 0; i < addOns.size(); i++) {
            Item addOn = addOns.get(i);
            JPanel info = new JPanel();

            JPanel name = new JPanel();
            JLabel nameLabel = new JLabel(i+1 +". "+addOn.getName());
            nameLabel.setHorizontalAlignment(CENTER);
            name.add(nameLabel);
            

            JPanel price = new JPanel();
            JLabel priceLabel = new JLabel("Price: PHP"+addOn.getPrice());
            priceLabel.setHorizontalAlignment(CENTER);
            price.add(priceLabel);

            JPanel calories = new JPanel();
            JLabel caloriesLabel = new JLabel("Calories: "+addOn.getCalories());
            caloriesLabel.setHorizontalAlignment(CENTER);
            calories.add(caloriesLabel);

            info.add(name);
            info.add(price);
            info.add(calories);
            

            added.add(info);
        }
    }

    public void displaySelectAddOnPrompt() {
        select = new JFrame("Select Add-On");
        select.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        select.setSize(600, 150);
        select.setResizable(false);
        select.setLayout(new BorderLayout());

        JPanel selectHeader = new JPanel();
        JLabel selectText = new JLabel("Enter the number of the add-on you want to include, or 'done' to finish customization.");
        selectText.setFont(new Font("Arial", Font.BOLD, 12));
        selectHeader.add(selectText);
        selectHeader.setAlignmentX(CENTER_ALIGNMENT);

        selectField = new JTextField();
        selectField.setPreferredSize(new Dimension(300,50));

        submitSelect = new JButton();
        submitSelect.setText("Submit");
        submitSelect.setBorder(null);
        submitSelect.setBackground(Color.gray);
        submitSelect.setFocusable(false);
        submitSelect.setPreferredSize(new Dimension(250, 50));

        select.add(selectHeader, BorderLayout.NORTH);
        select.add(selectField, BorderLayout.CENTER);
        select.add(submitSelect, BorderLayout.SOUTH);

        select.setVisible(true);
    }

    public void setSubmitSelectAction(ActionListener e){
        submitSelect.addActionListener(e);
    }

    public String getCustomizeField(){
        return selectField.getText();
    }

    public void disposeCustomizePrompt(){
        select.dispose();
    }

    public void disposeAddOns(){
        added.dispose();
    }

}
