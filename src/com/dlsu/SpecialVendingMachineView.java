import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

public class SpecialVendingMachineView extends VendingMachineView {
    private static final float CENTER_ALIGNMENT = 0;

    JFrame added;

    JFrame select;
    JTextField selectField;
    JButton submitSelect;

    JFrame additional;

    /**
    * Displays add - ons in a JFrame. It is used to add a new add - on
    *
    * @param addOns - List of items to
    */
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

    /**
    * Displays the select add - on prompt to select the number of the add - on you want to include
    */
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

    /**
    * Sets the action that is performed when the user clicks on the submit button. This is useful for adding actions to the list of actions that are performed when the user clicks on the " Select " button
    *
    * @param e - The action that is
    */
    public void setSubmitSelectAction(ActionListener e){
        submitSelect.addActionListener(e);
    }

    /**
    * Returns the text that should be displayed in the customize field. This is a convenience method for #selectField.
    *
    *
    * @return the text that should be displayed in the customize field or null if there is no text to be displayed
    */
    public String getCustomizeField(){
        return selectField.getText();
    }

    /**
    * Disposes the CustomizePrompt and frees the resources. This is called when the user clicks the Cancel
    */
    public void disposeCustomizePrompt(){
        select.dispose();
    }

    /**
    * Disposes the add - ons added by this object. This is called when the object is no longer needed
    */
    public void disposeAddOns(){
        added.dispose();
    }

    /**
    * Displays a JFrame containing the list of Add - ons that were added to the application. It is assumed that the items are in the correct order
    *
    * @param selectedAddOns - a list of Item
    */
    public void displayAdded(List<Item> selectedAddOns) {
        additional = new JFrame();
        additional.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        additional.setSize(new Dimension(500, 400));
        additional.setResizable(false);
        additional.setLayout(new FlowLayout());
        additional.setTitle("Added Items");
        additional.setVisible(true);

        for (Item item : selectedAddOns) {
            addAddOn(item);
            try {
                // Wait for the previous progress bar to finish
                Thread.sleep(determineDelay(item.getAction()) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    /**
    * Adds an item to the add - on. This is a GUI and should be used in conjunction with #add ( Item )
    *
    * @param item - The item to add
    */
    public void addAddOn(Item item) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(1, 1));

        // Use the action attribute from the item to create the label
        JLabel itemLabel = new JLabel("<html>" + item.getAction() + " " + item.getName() + "<br/></html>");
        itemLabel.setFont(new Font("Arial", Font.BOLD, 12));
        itemPanel.add(itemLabel);

        // Create a progress bar
        JProgressBar progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(0);
        itemPanel.add(progressBar);

        // Determine the delay based on the action
        int delay = determineDelay(item.getAction());  // You need to implement this method

        // Create a timer that updates the progress bar every 100 ms
        Timer timer = new Timer(100, new ActionListener() {
            private int counter = 0;

            /**
            * Increments the progress bar and stops the timer if the counter reaches 100. This is called when the user clicks on the button
            *
            * @param e - The ActionEvent that notified
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;
                progressBar.setValue(counter);

                // When the counter reaches 100, stop the timer
                if (counter >= 100) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.setInitialDelay(delay);
        timer.start();

        additional.add(itemPanel);

        // Refresh the JFrame to reflect changes
        additional.revalidate();
        additional.repaint();
    }

    /**
    * Determines the delay based on the action. This is used to determine how long to wait before an action is executed
    *
    * @param action - The action to determine the delay for
    *
    * @return The delay in miliseconds to wait before the action is executed 0 if the action is not
    */
    private int determineDelay(String action) {
        switch (action) {
            case "Slicing":
                return 1000;  // 1 second delay
            case "Adding":
                return 2000;  // 2 seconds delay
            case "Squeezing":
                return 3000;
            default:
                return 0;
        }
    }
}
