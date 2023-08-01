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

    public void start() {
        this.mainMenu();
    }

    public void mainMenu() {
        view.displayMainMenu();

        view.setCreateMachineAction(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                createMenu();
            }
        });

        view.setTestMachineAction(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getMachine() != null) {
                    testMenu();
                    //this.testMenu(scanner);
                } else {
                    view.displayNoVendingMachineError();
                }
            }
        });

        view.setExitAction(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                view.disposeMainMenu();
            }
        });



        /*while (true) {
            
            
            view.displayMainMenu();
            String choice = scanner.nextLine();
            
            if (choice.equals("1")) {
                this.createMenu();
            } else if (choice.equals("2")) {
                if (model.getMachine() != null) {
                    this.testMenu(scanner);
                } else {
                    view.displayNoVendingMachineError();
                }
            } else if (choice.equals("3")) {
                break;
            } else {
                view.displayInvalidChoiceError();
            }
        }*/
    }

    public void createMenu() {
        view.displayCreateMenu();

        view.setCreateRegularAction(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                model.createRegularVendingMachine();
                view.displayRegularVendingMachineCreatedMessage();
            }
        });

        view.setCreateSpecialAction(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                model.createSpecialVendingMachine();
                view.displaySpecialVendingMachineCreatedMessage();
            }
        });

        view.setBackAction(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                view.disposeCreateMenu();
            }
        });
        /*while (true) {
            view.displayCreateMenu();
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                model.createRegularVendingMachine();
                view.displayRegularVendingMachineCreatedMessage();
            } else if (choice.equals("2")) {
                model.createSpecialVendingMachine();
                view.displaySpecialVendingMachineCreatedMessage();
            } else if (choice.equals("3")) {
                break;
            } else {
                view.displayInvalidChoiceError();
            }
        }*/
    }

    public void testMenu() {
        view.displayTestMenu();

        view.setVendingFeaturesAction(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                testVendingFeatures();
            }
        });

        view.setMaintenanceFeaturesAction(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                testMaintenanceFeatures();
            }
        });

        view.setTestBackAction(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                view.disposeTestMenu();
            }
        });

        /*while (true) {
            view.displayTestMenu();
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                this.testVendingFeatures();
            } else if (choice.equals("2")) {
                this.testMaintenanceFeatures(scanner);
            } else if (choice.equals("3")) {
                break;
            } else {
                view.displayInvalidChoiceError();
            }
        }*/
    }

    public void testVendingFeatures() {
        // Get the current vending machine from the model
        VendingMachineController machineController = model.getMachine();

        // Create the VendingFeaturesView and VendingFeaturesController
        VendingFeaturesView vendingView = new VendingFeaturesView();
        VendingFeaturesController vendingController = new VendingFeaturesController(machineController, vendingView);

        // Start the vending features
        vendingController.start();
    }

    public void testMaintenanceFeatures() {
        view.displayMaintenanceFeatures();

        view.setRestockAction(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                view.promptItemToRestock();
                view.setSubmitRestockAction(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        view.promptQuantityToAdd();
                        int slotNumber = view.getRestockField();
                        view.disposeItemPrompt();
                        view.setSubmitQuantityAction(new ActionListener(){
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
            @Override
            public void actionPerformed(ActionEvent e){
                view.promptItemForPriceChange();
                view.setSubmitSlotAction(new ActionListener(){
                    @Override public void actionPerformed(ActionEvent e){
                        view.promptNewPrice();
                        int slotNumber = view.getSlotField();
                        view.disposeSlot();
                        view.setSubmitPriceAction(new ActionListener(){
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
            @Override
            public void actionPerformed(ActionEvent e){
                model.getMachine().collectPayment();
            }
        });

        view.setReplenishAction(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                view.promptDenominationToReplenish();
                view.setDenominationAction(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        view.promptQuantityToAdd();
                        int denomination = view.getDenominationField();
                        view.disposeDenomination();
                        view.setSubmitQuantityAction(new ActionListener(){
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
            @Override
            public void actionPerformed(ActionEvent e){
                model.getMachine().printTransactionSummary();
            }
        });

        view.setMaintenanceBackAction(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                view.disposeMaintenance();
            }
        });

        /*while (true) {
            view.displayMaintenanceFeatures();
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                view.promptItemToRestock();
                int slotNumber = Integer.parseInt(scanner.nextLine());
                view.promptQuantityToAdd();
                int quantity = Integer.parseInt(scanner.nextLine());
                model.getMachine().restock(slotNumber, quantity);
            } else if (choice.equals("2")) {
                view.promptItemForPriceChange();
                int slotNumber = Integer.parseInt(scanner.nextLine());
                view.promptNewPrice();
                int price = Integer.parseInt(scanner.nextLine());
                model.getMachine().setItemPrice(slotNumber, price);
            } else if (choice.equals("3")) {
                model.getMachine().collectPayment();
            } else if (choice.equals("4")) {
                view.promptDenominationToReplenish();
                int denomination = Integer.parseInt(scanner.nextLine());
                view.promptQuantityToAdd();
                int quantity = Integer.parseInt(scanner.nextLine());
                model.getMachine().replenishMoney(denomination, quantity);
            } else if (choice.equals("5")) {
                model.getMachine().printTransactionSummary();
            } else if (choice.equals("6")) {
                break;
            } else {
                view.displayInvalidChoiceError();
            }
        }*/
    }
}
