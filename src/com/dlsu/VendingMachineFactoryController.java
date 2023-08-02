import java.util.Scanner;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VendingMachineFactoryController {
    private VendingMachineFactoryModel model;
    private VendingMachineFactoryView view;
    private Scanner scanner;

    public VendingMachineFactoryController(VendingMachineFactoryModel model, VendingMachineFactoryView view, Scanner scanner) {
        this.model = model;
        this.view = view;
        this.scanner = scanner;
    }

    /**
    * Called when the application starts. This is where you can start the application's main menu ( s
    */
    public void start() {
        this.mainMenu();
    }

    /**
    * Displays the main menu and allows the user to create a machine and test it. This is called when the user clicks on the menu
    */
    public void mainMenu() {
        view.displayMainMenu();

        view.setCreateMachineAction(new ActionListener(){
            /**
            * Creates the menu and calls createMenu (). This method is called when the user clicks on the button
            * 
            * @param e - the ActionEvent that notified
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                createMenu();
            }
        });

        view.setTestMachineAction(new ActionListener(){
            /**
            * Called when the user presses the button. Checks if there is a machine to vend or if it is not a vending machine.
            * 
            * @param e - The ActionEvent that caused this
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                // testMenu method for testing the machine.
                if (model.getMachine() != null) {
                    testMenu();
                    //this.testMenu(scanner);
                } else {
                    view.displayNoVendingMachineError();
                }
            }
        });

        view.setExitAction(new ActionListener(){
            /**
            * Disposes the main menu. This is called when the user clicks on the button. It will be removed from the JInternalFrame and any resources associated with it will be reclaimed as part of the event handling.
            * 
            * @param e - The ActionEvent that caused this method to be called
            */
            @Override
            public void actionPerformed(ActionEvent e){
                view.disposeMainMenu();
            }
        });
    }

    /**
    * Displays the create menu and allows the vending machine to be created. This menu is displayed when the user clicks on the create
    */
    public void createMenu() {
        view.displayCreateMenu();

        view.setCreateRegularAction(new ActionListener(){
            /**
            * Creates and displays a regular vending machine. It is possible to create a machine without having to enter the machine's name in the model.
            * 
            * @param e - the ActionEvent that notified this method of the action
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                model.createRegularVendingMachine();
                view.displayRegularVendingMachineCreatedMessage();
            }
        });

        view.setCreateSpecialAction(new ActionListener(){
            /**
            * Creates and displays a vending machine. It is called when the user clicks on the button. This will create a new vending machine and display a message to the user about the creation
            * 
            * @param e - The ActionEvent that caused this
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                model.createSpecialVendingMachine();
                view.displaySpecialVendingMachineCreatedMessage();
            }
        });

        view.setBackAction(new ActionListener(){
            /**
            * Disposes the create menu. This is called when the user clicks on the button to create a new file
            * 
            * @param e - The ActionEvent that caused the
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                view.disposeCreateMenu();
            }
        });
    }

    /**
    * Tests the vending and maintenance features menu. This is a menu that allows the user to select a feature
    */
    public void testMenu() {
        view.displayTestMenu();

        view.setVendingFeaturesAction(new ActionListener(){
            /**
            * Tests vending features and displays results. This is called when the user clicks on the button. It calls #testVendingFeatures () to do the actual testing
            * 
            * @param e - the ActionEvent that caused
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                testVendingFeatures();
            }
        });

        view.setMaintenanceFeaturesAction(new ActionListener(){
            /**
            * Tests the maintenance features. This is called when the user clicks on the button. It calls #testMaintenanceFeatures () to make sure we are in a state where it can be tested.
            * 
            * @param e - The ActionEvent that caused this method to be called
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                testMaintenanceFeatures();
            }
        });

        view.setTestBackAction(new ActionListener(){
            /**
            * Disposes the test menu. This is called when the user clicks on the button. It will be removed from the view and re - created on the next run.
            * 
            * @param e - The ActionEvent that caused the event ( not used
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                view.disposeTestMenu();
            }
        });
    }

    /**
    * Tests the vending features screen. This is a simple test to make sure we can interact with the model
    */
    public void testVendingFeatures() {
        // Get the current vending machine from the model
        VendingMachineController machineController = model.getMachine();

        // Create the VendingFeaturesView and VendingFeaturesController
        VendingFeaturesView vendingView = new VendingFeaturesView();
        VendingFeaturesController vendingController = new VendingFeaturesController(machineController, vendingView);

        // Start the vending features
        vendingController.start();
    }

    /**
    * Tests maintenance features. Restock items are promoted to slots and quantities are redeemed. Set price changes are promoted to slots
    */
    public void testMaintenanceFeatures() {
        view.displayMaintenanceFeatures();

        view.setRestockAction(new ActionListener(){
            /**
            * Restock the quantity and prompt. This is called when the user clicks on the restock button.
            * 
            * @param e - The ActionEvent that caused this method to be called
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                view.promptItemToRestock();
                view.setSubmitRestockAction(new ActionListener(){
                    /**
                    * Called when the user presses the restock button. This is the place where the quantity will be promoted to the slot
                    * 
                    * @param e - The ActionEvent that caused this
                    */
                    @Override
                    public void actionPerformed(ActionEvent e){
                        view.promptQuantityToAdd();
                        int slotNumber = view.getRestockField();
                        view.disposeItemPrompt();
                        view.setSubmitQuantityAction(new ActionListener(){
                            /**
                            * Restock the quantity of the slot. Disposes the quantity field when done. This is called from the JRadioButton
                            * 
                            * @param e - the ActionEvent that notified
                            */
                            @Override
                            public void actionPerformed(ActionEvent e){
                                int quantity = view.getQuantityField();
                                System.out.println(""+slotNumber+" "+quantity);
                                model.getMachine().restock(slotNumber, quantity);
                                view.disposeQuantity();
                            }
                        });  
                    }
                });
            }
        });

        view.setSetPriceAction(new ActionListener(){
            /**
            * Called when the user clicks on an item. Prompts the user to change the price and submits the change
            * 
            * @param e - The ActionEvent that caused this
            */
            @Override
            public void actionPerformed(ActionEvent e){
                view.promptItemForPriceChange();
                view.setSubmitSlotAction(new ActionListener(){
                    /**
                    * Called when the user presses the button. This is the place to do the action that is needed to set the price of the item
                    * 
                    * @param e - The ActionEvent that caused this
                    */
                    @Override public void actionPerformed(ActionEvent e){
                        view.promptNewPrice();
                        int slotNumber = view.getSlotField();
                        view.disposeSlot();
                        view.setSubmitPriceAction(new ActionListener(){
                            /**
                            * Updates the price of the item. This is called when the user clicks on the price button. It will save the price in the model and disposes the price field
                            * 
                            * @param e - The ActionEvent that caused this
                            */
                            @Override
                            public void actionPerformed(ActionEvent e){
                                int price = view.getPriceField();
                                model.getMachine().setItemPrice(slotNumber, price);
                                view.disposePrice();
                            }
                        });
                    }
                });
            }
        });

        view.setCollectAction(new ActionListener(){
            /**
            * Collects payment from the machine. This is called when the user presses the button. It will collect the payment and send it to MobilityController#collectPayment ()
            * 
            * @param e - the ActionEvent that caused this
            */
            @Override
            public void actionPerformed(ActionEvent e){
                model.getMachine().collectPayment();
            }
        });

        view.setReplenishAction(new ActionListener(){
            /**
            * Called when the user presses the button. Replaces the denomination and quantity with a replenish action
            * 
            * @param e - The ActionEvent that caused this
            */
            @Override
            public void actionPerformed(ActionEvent e){
                view.promptDenominationToReplenish();
                view.setDenominationAction(new ActionListener() {
                    /**
                    * Called when the user presses the button. Replaces the denomination and quantity entered in the quantity field by one.
                    * 
                    * @param e - The ActionEvent that caused this method to be called
                    */
                    @Override
                    public void actionPerformed(ActionEvent e){
                        view.promptQuantityToAdd();
                        int denomination = view.getDenominationField();
                        view.disposeDenomination();
                        view.setSubmitQuantityAction(new ActionListener(){
                            /**
                            * Invoked when the user presses the button. Replaces the money in the model with the quantity entered in the quantity field
                            * 
                            * @param e - The ActionEvent that caused this
                            */
                            @Override 
                            public void actionPerformed(ActionEvent e){
                                int quantity = view.getQuantityField();
                                model.getMachine().replenishMoney(denomination, quantity);
                                view.disposeQuantity();
                            }
                        });
                    }
                });
            }
        });

        view.setPrintSummaryAction(new ActionListener(){
            /**
            * Prints the transaction summary to the output. This is called when the user clicks on the button. It will print the transaction summary to the machine's file system
            * 
            * @param e - The ActionEvent that caused this
            */
            @Override
            public void actionPerformed(ActionEvent e){
                model.getMachine().printTransactionSummary();
            }
        });

        view.setMaintenanceBackAction(new ActionListener(){
            /**
            * Disposes the maintenance window. This is called when the user clicks on the Cancel button. It will be called by the system in response to a request to close the maintenance window
            * 
            * @param e - The ActionEvent that caused this
            */
            @Override
            public void actionPerformed(ActionEvent e){
                view.disposeMaintenance();
            }
        });
    }
}
