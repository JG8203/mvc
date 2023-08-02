import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class VendingMachineFactoryView {
    private static final float CENTER_ALIGNMENT = 0;
    JFrame mainMenu;
    JButton createMachine;
    JButton testMachine;
    JButton exit;

    JFrame createMenu;
    JButton createRegular;
    JButton createSpecial;
    JButton back;

    JFrame testMenu;
    JButton vendingFeatures;
    JButton maintenanceFeatures;
    JButton testBack;

    JFrame displayMaintenance;
    JButton restock;
    JButton setPrice;
    JButton collect;
    JButton replenish;
    JButton printSummary;
    JButton maintenanceBack;

    JFrame itemRestockPrompt;
    JTextField restockField;
    JButton submitRestock;

    JFrame quantityAddPrompt;
    JTextField quantityField;
    JButton submitQuantity;

    JFrame slotPricePrompt;
    JTextField slotField;
    JButton submitSlot;

    JFrame newPrice;
    JTextField priceField;
    JButton submitPrice;

    JFrame denominationPrompt;
    JTextField denominationField;
    JButton submitDenomination;
    JButton displayStatus;

    /**
     * Disposes and frees the main menu. This is called when the application is destroyed and can be re - used
     */
    public void disposeMainMenu() {
        mainMenu.dispose();
    }

    /**
     * Displays the main menu for Vending Machine Factory. It is used to create a machine and test it
     */
    public void displayMainMenu() {
        mainMenu = new JFrame("Vending Machine Factory");
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setSize(650, 300);
        mainMenu.setResizable(false);
        mainMenu.setLayout(new BorderLayout());

        JPanel factoryHeader = new JPanel();
        JLabel factoryText = new JLabel("Vending Machine Factory");
        factoryText.setFont(new Font("Arial", Font.BOLD, 50));
        factoryHeader.add(factoryText);
        factoryHeader.setAlignmentX(CENTER_ALIGNMENT);

        JPanel menuOptions = new JPanel();
        menuOptions.setLayout(new FlowLayout(FlowLayout.CENTER));

        createMachine = new JButton();
        createMachine.setText("Create a Vending Machine");
        createMachine.setBorder(null);
        createMachine.setBackground(Color.gray);
        createMachine.setFocusable(false);
        createMachine.setPreferredSize(new Dimension(250, 50));

        testMachine = new JButton();
        testMachine.setText("Test a Vending Machine");
        testMachine.setBorder(null);
        testMachine.setBackground(Color.gray);
        testMachine.setFocusable(false);
        testMachine.setPreferredSize(new Dimension(250, 50));

        exit = new JButton();
        exit.setText("Exit");
        exit.setBorder(null);
        exit.setBackground(Color.gray);
        exit.setFocusable(false);
        exit.setPreferredSize(new Dimension(250, 50));

        menuOptions.add(createMachine);
        menuOptions.add(testMachine);
        menuOptions.add(exit);

        mainMenu.add(factoryHeader, BorderLayout.NORTH);
        mainMenu.add(menuOptions, BorderLayout.CENTER);

        mainMenu.setVisible(true);
    }

    /**
     * Sets the action that is performed when a machine is created. This action will be performed on the UI thread
     * 
     * @param e - the action that is
     */
    public void setCreateMachineAction(ActionListener e) {
        createMachine.addActionListener(e);
    }

    /**
     * Sets the action listener for the test machine. This is used to notify the JUnit test machine that something has changed in the test machine
     * 
     * @param e - the action listener to
     */
    public void setTestMachineAction(ActionListener e) {
        testMachine.addActionListener(e);
    }

    /**
     * Sets the action that is performed when the user presses the exit button. This is useful for cases where you want to exit from a program without knowing the user's input.
     * 
     * @param e - The action that is performed when the user press
     */
    public void setExitAction(ActionListener e) {
        exit.addActionListener(e);
    }

    /**
     * Disposes the menu created by createMenu (). This is called when the user clicks the Create button
     */
    public void disposeCreateMenu() {
        createMenu.dispose();
    }

    /**
     * Displays the menu to create a vending machine. It is displayed in the center of the screen and can be clicked
     */
    public void displayCreateMenu() {
        createMenu = new JFrame("Create a Vending Machine");
        createMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createMenu.setSize(650, 300);
        createMenu.setResizable(false);
        createMenu.setLayout(new BorderLayout());

        JPanel createHeader = new JPanel();
        JLabel createText = new JLabel("Create a Vending Machine");
        createText.setFont(new Font("Arial", Font.BOLD, 50));
        createHeader.add(createText);
        createHeader.setAlignmentX(CENTER_ALIGNMENT);

        JPanel createOptions = new JPanel();
        createOptions.setLayout(new FlowLayout(FlowLayout.CENTER));

        createRegular = new JButton();
        createRegular.setText("Create a Regular Vending Machine");
        createRegular.setBorder(null);
        createRegular.setBackground(Color.gray);
        createRegular.setFocusable(false);
        createRegular.setPreferredSize(new Dimension(250, 50));

        createSpecial = new JButton();
        createSpecial.setText("Create a Special Vending Machine");
        createSpecial.setBorder(null);
        createSpecial.setBackground(Color.gray);
        createSpecial.setFocusable(false);
        createSpecial.setPreferredSize(new Dimension(250, 50));

        back = new JButton();
        back.setText("back");
        back.setBorder(null);
        back.setBackground(Color.gray);
        back.setFocusable(false);
        back.setPreferredSize(new Dimension(250, 50));

        createOptions.add(createRegular);
        createOptions.add(createSpecial);
        createOptions.add(back);

        createMenu.add(createHeader, BorderLayout.NORTH);
        createMenu.add(createOptions, BorderLayout.CENTER);

        createMenu.setVisible(true);
    }

    /**
     * Sets the action that is performed when the user clicks on the create regular button. This is useful for creating regular files that are not part of the file system.
     * 
     * @param e - the action that is performed when the user clicks
     */
    public void setCreateRegularAction(ActionListener e) {
        createRegular.addActionListener(e);
    }

    /**
     * Sets the action that is performed when a special item is created. This action is performed when the user clicks on the create special button
     * 
     * @param e - the action that is
     */
    public void setCreateSpecialAction(ActionListener e) {
        createSpecial.addActionListener(e);
    }

    /**
     * Sets the action that is triggered when the user presses the back button. Note that this action will be triggered on the UI thread
     * 
     * @param e - the action that is
     */
    public void setBackAction(ActionListener e) {
        back.addActionListener(e);
    }

    /**
     * Disposes the test menu. This is called when the user clicks the Cancel button in the menu or when the menu is re - created
     */
    public void disposeTestMenu() {
        testMenu.dispose();
    }

    /**
     * Displays the menu for testing vending features and a list of options to choose which vending features are
     */
    public void displayTestMenu() {
        testMenu = new JFrame("Test Vending Machine");
        testMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testMenu.setSize(650, 300);
        testMenu.setResizable(false);
        testMenu.setLayout(new BorderLayout());

        JPanel testHeader = new JPanel();
        JLabel testText = new JLabel("Test the Vending Machine");
        testText.setFont(new Font("Arial", Font.BOLD, 50));
        testHeader.add(testText);
        testHeader.setAlignmentX(CENTER_ALIGNMENT);

        JPanel testOptions = new JPanel();
        testOptions.setLayout(new FlowLayout(FlowLayout.CENTER));

        vendingFeatures = new JButton();
        vendingFeatures.setText("Vending Features");
        vendingFeatures.setBorder(null);
        vendingFeatures.setBackground(Color.gray);
        vendingFeatures.setFocusable(false);
        vendingFeatures.setPreferredSize(new Dimension(250, 50));

        maintenanceFeatures = new JButton();
        maintenanceFeatures.setText("Maintenance Features");
        maintenanceFeatures.setBorder(null);
        maintenanceFeatures.setBackground(Color.gray);
        maintenanceFeatures.setFocusable(false);
        maintenanceFeatures.setPreferredSize(new Dimension(250, 50));

        testBack = new JButton();
        testBack.setText("back");
        testBack.setBorder(null);
        testBack.setBackground(Color.gray);
        testBack.setFocusable(false);
        testBack.setPreferredSize(new Dimension(250, 50));

        testOptions.add(vendingFeatures);
        testOptions.add(maintenanceFeatures);
        testOptions.add(testBack);

        testMenu.add(testHeader, BorderLayout.NORTH);
        testMenu.add(testOptions, BorderLayout.CENTER);

        testMenu.setVisible(true);
    }

    /**
     * Sets the action that is notified when vending features are pressed. This is a no - op if there is no vending features
     * 
     * @param e - the action that is
     */
    public void setVendingFeaturesAction(ActionListener e) {
        vendingFeatures.addActionListener(e);
    }

    /**
     * Sets the action that is notified when maintenance features are enabled. This is a convenience method for adding an ActionListener to the MaintenanceFeatures list
     * 
     * @param e - the action to be
     */
    public void setMaintenanceFeaturesAction(ActionListener e) {
        maintenanceFeatures.addActionListener(e);
    }

    /**
     * Sets the action that is executed when the user presses the back button. This action will be executed in the test case and not in the test case
     * 
     * @param e - the action that is
     */
    public void setTestBackAction(ActionListener e) {
        testBack.addActionListener(e);
    }

    /**
     * Displays vending features for the user to choose which features to use when choosing a vending order
     */
    public void displayVendingFeatures() {
        System.out.println("==== Vending Features ====");
        System.out.println("[1] Display items");
        System.out.println("[2] Insert money");
        System.out.println("[3] Buy an item");
        System.out.println("[4] Customize a product");
        System.out.println("[5] Back");
        System.out.print("Enter choice: ");
    }

    /**
     * Disposes of the maintenance window. This is called when the user clicks the Cancel button in the maintenance
     */
    public void disposeMaintenance() {
        displayMaintenance.dispose();
    }

    /**
     * Creates and displays the maintenance features for the vending machine.
     */
    public void displayMaintenanceFeatures() {
        displayMaintenance = new JFrame("Test Vending Machine");
        displayMaintenance.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayMaintenance.setSize(650, 600);
        displayMaintenance.setResizable(false);
        displayMaintenance.setLayout(new BorderLayout());

        JPanel maintenanceHeader = new JPanel();
        JLabel maintenanceText = new JLabel("Testing Features");
        maintenanceText.setFont(new Font("Arial", Font.BOLD, 50));
        maintenanceHeader.add(maintenanceText);
        maintenanceHeader.setAlignmentX(CENTER_ALIGNMENT);

        JPanel maintenanceOptions = new JPanel();
        maintenanceOptions.setLayout(new GridLayout(2, 3, 5, 5));

        restock = new JButton();
        restock.setText("Restock an Item");
        restock.setBorder(null);
        restock.setBackground(Color.gray);
        restock.setFocusable(false);
        restock.setPreferredSize(new Dimension(250, 50));

        setPrice = new JButton();
        setPrice.setText("Set Item Price");
        setPrice.setBorder(null);
        setPrice.setBackground(Color.gray);
        setPrice.setFocusable(false);
        setPrice.setPreferredSize(new Dimension(250, 50));

        collect = new JButton();
        collect.setText("Collect Payment");
        collect.setBorder(null);
        collect.setBackground(Color.gray);
        collect.setFocusable(false);
        collect.setPreferredSize(new Dimension(250, 50));

        replenish = new JButton();
        replenish.setText("Replenenish Money");
        replenish.setBorder(null);
        replenish.setBackground(Color.gray);
        replenish.setFocusable(false);
        replenish.setPreferredSize(new Dimension(250, 50));

        printSummary = new JButton();
        printSummary.setText("Print Transaction Summary");
        printSummary.setBorder(null);
        printSummary.setBackground(Color.gray);
        printSummary.setFocusable(false);
        printSummary.setPreferredSize(new Dimension(250, 50));

        maintenanceBack = new JButton();
        maintenanceBack.setText("back");
        maintenanceBack.setBorder(null);
        maintenanceBack.setBackground(Color.gray);
        maintenanceBack.setFocusable(false);
        maintenanceBack.setPreferredSize(new Dimension(250, 50));

        maintenanceOptions.add(restock);
        maintenanceOptions.add(setPrice);
        maintenanceOptions.add(collect);
        maintenanceOptions.add(replenish);
        maintenanceOptions.add(printSummary);
        maintenanceOptions.add(maintenanceBack);

        displayMaintenance.add(maintenanceHeader, BorderLayout.NORTH);
        displayMaintenance.add(maintenanceOptions, BorderLayout.CENTER);

        displayMaintenance.setVisible(true);

    }
    public void setDisplayStatusAction(ActionListener e) {
        displayStatus.addActionListener(e);
    }
    /**
     * Adds an action listener for the restock button.
     *
     * @param e the action listener to be added
     */
    public void setRestockAction(ActionListener e) {
        restock.addActionListener(e);
    }

    /**
     * Adds an action listener for the replenish button.
     *
     * @param e the action listener to be added
     */
    public void setReplenishAction(ActionListener e) {
        replenish.addActionListener(e);
    }
    /**
     * Adds an action listener for the collect button.
     *
     * @param e the action listener to be added
     */
    public void setCollectAction(ActionListener e) {
        collect.addActionListener(e);
    }
    /**
     * Adds an action listener for the set price button.
     *
     * @param e the action listener to be added
     */
    public void setSetPriceAction(ActionListener e) {
        setPrice.addActionListener(e);
    }
    /**
     * Adds an action listener for the print transaction summary button.
     *
     * @param e the action listener to be added
     */
    public void setPrintSummaryAction(ActionListener e) {
        printSummary.addActionListener(e);
    }
    /**
     * Adds an action listener for the maintenance back button.
     *
     * @param e the action listener to be added
     */
    public void setMaintenanceBackAction(ActionListener e) {
        maintenanceBack.addActionListener(e);
    }
    /**
     * Displays a message indicating that a regular vending machine has been created.
     */
    public void displayRegularVendingMachineCreatedMessage() {
        JOptionPane.showMessageDialog(null, "Regular vending machine created.", "Vending Machine Factory", JOptionPane.INFORMATION_MESSAGE, null);
    }
    /**
     * Displays a message indicating that a special vending machine has been created.
     */
    public void displaySpecialVendingMachineCreatedMessage() {
        JOptionPane.showMessageDialog(null, "Special vending machine created.", "Vending Machine Factory", JOptionPane.INFORMATION_MESSAGE, null);
    }
    /**
     * Prompts the user to select an item to restock.
     */
    public void promptItemToRestock() {
        itemRestockPrompt = new JFrame("Testing Features");
        itemRestockPrompt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        itemRestockPrompt.setSize(600, 150);
        itemRestockPrompt.setResizable(false);
        itemRestockPrompt.setLayout(new BorderLayout());

        JPanel restockHeader = new JPanel();
        JLabel restockText = new JLabel("Enter the number of the item you want to restock:");
        restockText.setFont(new Font("Arial", Font.BOLD, 12));
        restockHeader.add(restockText);
        restockHeader.setAlignmentX(CENTER_ALIGNMENT);

        restockField = new JTextField();
        restockField.setPreferredSize(new Dimension(300, 50));

        JPanel restockPrompt = new JPanel();
        restockPrompt.setLayout(new FlowLayout(FlowLayout.CENTER));

        submitRestock = new JButton();
        submitRestock.setText("Submit");
        submitRestock.setBorder(null);
        submitRestock.setBackground(Color.gray);
        submitRestock.setFocusable(false);
        submitRestock.setPreferredSize(new Dimension(250, 50));

        itemRestockPrompt.add(restockHeader, BorderLayout.NORTH);
        itemRestockPrompt.add(restockField, BorderLayout.CENTER);
        itemRestockPrompt.add(submitRestock, BorderLayout.SOUTH);

        itemRestockPrompt.setVisible(true);
    }
    /**
     * Sets the action listener for the restock submission button.
     *
     * @param e the action listener to be added
     */
    public void setSubmitRestockAction(ActionListener e) {
        submitRestock.addActionListener(e);
    }
    /**
     * Retrieves the value from the restock field.
     *
     * @return the number of the item to be restocked
     */
    public int getRestockField() {
        return Integer.valueOf(restockField.getText());
    }
    /**
     * Disposes of the item restock prompt.
     */
    public void disposeItemPrompt() {
        itemRestockPrompt.dispose();
    }
    /**
     * Prompts the user to enter the quantity of an item to add.
     */
    public void promptQuantityToAdd() {
        quantityAddPrompt = new JFrame("Testing Features");
        quantityAddPrompt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quantityAddPrompt.setSize(600, 150);
        quantityAddPrompt.setResizable(false);
        quantityAddPrompt.setLayout(new BorderLayout());

        JPanel quantityHeader = new JPanel();
        JLabel quantityText = new JLabel("Enter the quantity you want to add:");
        quantityText.setFont(new Font("Arial", Font.BOLD, 12));
        quantityHeader.add(quantityText);
        quantityHeader.setAlignmentX(CENTER_ALIGNMENT);

        quantityField = new JTextField();
        quantityField.setPreferredSize(new Dimension(300, 50));

        JPanel quantityPrompt = new JPanel();
        quantityPrompt.setLayout(new FlowLayout(FlowLayout.CENTER));

        submitQuantity = new JButton();
        submitQuantity.setText("Submit");
        submitQuantity.setBorder(null);
        submitQuantity.setBackground(Color.gray);
        submitQuantity.setFocusable(false);
        submitQuantity.setPreferredSize(new Dimension(250, 50));

        quantityAddPrompt.add(quantityHeader, BorderLayout.NORTH);
        quantityAddPrompt.add(quantityField, BorderLayout.CENTER);
        quantityAddPrompt.add(submitQuantity, BorderLayout.SOUTH);

        quantityAddPrompt.setVisible(true);
    }
    /**
     * Sets the action listener for the quantity submission button.
     *
     * @param e the action listener to be added
     */
    public void setSubmitQuantityAction(ActionListener e) {
        submitQuantity.addActionListener(e);
    }
    /**
     * Retrieves the value from the quantity field.
     *
     * @return the quantity to be added
     */
    public int getQuantityField() {
        return Integer.valueOf(quantityField.getText());
    }
    /**
     * Disposes of the quantity prompt.
     */
    public void disposeQuantity() {
        quantityAddPrompt.dispose();
    }
    /**
     * Prompts the user to select an item for a price change.
     */
    public void promptItemForPriceChange() {
        slotPricePrompt = new JFrame("Testing Features");
        slotPricePrompt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        slotPricePrompt.setSize(600, 150);
        slotPricePrompt.setResizable(false);
        slotPricePrompt.setLayout(new BorderLayout());

        JPanel slotHeader = new JPanel();
        JLabel slotText = new JLabel("Enter the number of the item for which you want to set the price:");
        slotText.setFont(new Font("Arial", Font.BOLD, 12));
        slotHeader.add(slotText);
        slotHeader.setAlignmentX(CENTER_ALIGNMENT);

        slotField = new JTextField();
        slotField.setPreferredSize(new Dimension(300, 50));

        JPanel slotPrompt = new JPanel();
        slotPrompt.setLayout(new FlowLayout(FlowLayout.CENTER));

        submitSlot = new JButton();
        submitSlot.setText("Submit");
        submitSlot.setBorder(null);
        submitSlot.setBackground(Color.gray);
        submitSlot.setFocusable(false);
        submitSlot.setPreferredSize(new Dimension(250, 50));

        slotPricePrompt.add(slotHeader, BorderLayout.NORTH);
        slotPricePrompt.add(slotField, BorderLayout.CENTER);
        slotPricePrompt.add(submitSlot, BorderLayout.SOUTH);

        slotPricePrompt.setVisible(true);
    }
    /**
     * Sets the action listener for the slot submission button.
     *
     * @param e the action listener to be added
     */
    public void setSubmitSlotAction(ActionListener e) {
        submitSlot.addActionListener(e);
    }
    /**
     * Retrieves the value from the slot field.
     *
     * @return the number of the item for which the price is to be set
     */
    public int getSlotField() {
        return Integer.valueOf(slotField.getText());
    }
    /**
     * Disposes of the slot prompt.
     */
    public void disposeSlot() {
        slotPricePrompt.dispose();
    }
    /**
     * Prompts the user to enter the new price for an item.
     */
    public void promptNewPrice() {
        newPrice = new JFrame("Testing Features");
        newPrice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newPrice.setSize(600, 150);
        newPrice.setResizable(false);
        newPrice.setLayout(new BorderLayout());

        JPanel priceHeader = new JPanel();
        JLabel priceText = new JLabel("Enter the new price:");
        priceText.setFont(new Font("Arial", Font.BOLD, 12));
        priceHeader.add(priceText);
        priceHeader.setAlignmentX(CENTER_ALIGNMENT);

        priceField = new JTextField();
        priceField.setPreferredSize(new Dimension(300, 50));

        JPanel pricePrompt = new JPanel();
        pricePrompt.setLayout(new FlowLayout(FlowLayout.CENTER));

        submitPrice = new JButton();
        submitPrice.setText("Submit");
        submitPrice.setBorder(null);
        submitPrice.setBackground(Color.gray);
        submitPrice.setFocusable(false);
        submitPrice.setPreferredSize(new Dimension(250, 50));

        newPrice.add(priceHeader, BorderLayout.NORTH);
        newPrice.add(priceField, BorderLayout.CENTER);
        newPrice.add(submitPrice, BorderLayout.SOUTH);

        newPrice.setVisible(true);
    }
    /**
     * Sets the action listener for the price submission button.
     *
     * @param e the action listener to be added
     */
    public void setSubmitPriceAction(ActionListener e) {
        submitPrice.addActionListener(e);
    }
    /**
     * Retrieves the value from the price field.
     *
     * @return the new price to be set
     */
    public int getPriceField() {
        return Integer.valueOf(priceField.getText());
    }

    /**
     * Disposes of the price prompt.
     */
    public void disposePrice() {
        newPrice.dispose();
    }
    /**
     * Prompts the user to enter the denomination to replenish.
     */
    public void promptDenominationToReplenish() {
        denominationPrompt = new JFrame("Testing Features");
        denominationPrompt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        denominationPrompt.setSize(600, 150);
        denominationPrompt.setResizable(false);
        denominationPrompt.setLayout(new BorderLayout());

        JPanel denominationHeader = new JPanel();
        JLabel denominationText = new JLabel("Enter the denomination you want to replenish:");
        denominationText.setFont(new Font("Arial", Font.BOLD, 12));
        denominationHeader.add(denominationText);
        denominationHeader.setAlignmentX(CENTER_ALIGNMENT);

        denominationField = new JTextField();
        denominationField.setPreferredSize(new Dimension(300, 50));

        submitDenomination = new JButton();
        submitDenomination.setText("Submit");
        submitDenomination.setBorder(null);
        submitDenomination.setBackground(Color.gray);
        submitDenomination.setFocusable(false);
        submitDenomination.setPreferredSize(new Dimension(250, 50));

        denominationPrompt.add(denominationHeader, BorderLayout.NORTH);
        denominationPrompt.add(denominationField, BorderLayout.CENTER);
        denominationPrompt.add(submitDenomination, BorderLayout.SOUTH);

        denominationPrompt.setVisible(true);
    }
    /**
     * Sets the action listener for the denomination submission button.
     *
     * @param e the action listener to be added
     */
    public void setDenominationAction(ActionListener e) {
        submitDenomination.addActionListener(e);
    }
    /**
     * Retrieves the value from the denomination field.
     *
     * @return the denomination to be replenished
     */
    public int getDenominationField() {
        return Integer.valueOf(denominationField.getText());
    }
    /**
     * Disposes of the denomination prompt.
     */
    public void disposeDenomination() {
        denominationPrompt.dispose();
    }
    /**
     * Displays an error message if no vending machine has been created.
     */
    public void displayNoVendingMachineError() {
        JOptionPane.showMessageDialog(null, "No vending machine created. Please create a vending machine first.", "Vending Machine Factory", JOptionPane.ERROR_MESSAGE, null);
    }
}