import java.util.Scanner;

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
        while (true) {
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
        }
    }

    public void createMenu() {
        while (true) {
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
        }
    }

    public void testMenu(Scanner scanner) {
        while (true) {
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
        }
    }

    public void testVendingFeatures() {
        // Get the current vending machine from the model
        VendingMachineController machineController = model.getMachine();

        // Create the VendingFeaturesView and VendingFeaturesController
        VendingFeaturesView vendingView = new VendingFeaturesView();
        VendingFeaturesController vendingController = new VendingFeaturesController(machineController, vendingView, scanner);

        // Start the vending features
        vendingController.start();
    }

    public void testMaintenanceFeatures(Scanner scanner) {
        while (true) {
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
        }
    }
}
