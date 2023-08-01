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

    /**
    * Called when the application starts. This is where you can start the application's main menu ( s
    */
    public void start() {
        this.mainMenu();
    }

    /**
    * Displays the main menu and asks the user to create a machine or test it. If the user chooses to test it displays an error
    */
    public void mainMenu() {
        // This method is called by the scanner to read a choice from the scanner.
        while (true) {
            view.displayMainMenu();
            String choice = scanner.nextLine();

            // This method is called when the user clicks on a choice.
            if (choice.equals("1")) {
                this.createMenu();
            // This method is called when the user clicks on a choice.
            } else if (choice.equals("2")) {
                // This method is called when the user clicks on a machine.
                if (model.getMachine() != null) {
                    this.testMenu(scanner);
                } else {
                    view.displayNoVendingMachineError();
                }
            // if choice is 3 then display invalid choice error
            } else if (choice.equals("3")) {
                break;
            } else {
                view.displayInvalidChoiceError();
            }
        }
    }

    /**
    * Displays the create menu and asks the user what to create. If the user chose to create a vending machine they are added to the model
    */
    public void createMenu() {
        // This method is called by the user when the user selects a choice.
        while (true) {
            view.displayCreateMenu();
            String choice = scanner.nextLine();

            // This method is called when the user clicks on a choice.
            if (choice.equals("1")) {
                model.createRegularVendingMachine();
                view.displayRegularVendingMachineCreatedMessage();
            // This method is called when the user clicks on a choice.
            } else if (choice.equals("2")) {
                model.createSpecialVendingMachine();
                view.displaySpecialVendingMachineCreatedMessage();
            // if choice is 3 then display invalid choice error
            } else if (choice.equals("3")) {
                break;
            } else {
                view.displayInvalidChoiceError();
            }
        }
    }

    /**
    * Tests the Vehicle's features. This method is called by the GUI when the user presses the menu button.
    * 
    * @param scanner - The Scanner to read the user's input
    */
    public void testMenu(Scanner scanner) {
        // This method is called by the scanner to read a choice from the scanner.
        while (true) {
            view.displayTestMenu();
            String choice = scanner.nextLine();

            // This method is called when a choice is selected.
            if (choice.equals("1")) {
                this.testVendingFeatures();
            // This method is called when choice is 2 or 3.
            } else if (choice.equals("2")) {
                this.testMaintenanceFeatures();
            // if choice is 3 then display invalid choice error
            } else if (choice.equals("3")) {
                break;
            } else {
                view.displayInvalidChoiceError();
            }
        }
    }

    /**
    * Tests the vending features screen. This is a simple test to make sure we can interact with the model
    */
    public void testVendingFeatures() {
        // Get the current vending machine from the model
        VendingMachineController machineController = model.getMachine();

        // Create the VendingFeaturesView and VendingFeaturesController
        VendingFeaturesView vendingView = new VendingFeaturesView();
        VendingFeaturesController vendingController = new VendingFeaturesController(machineController, vendingView, scanner);

        // Start the vending features
        vendingController.start();
    }
    /**
    * Tests the Maintenance Features screen. This is a test for bug MONDRIAN - 456 " Maintained features cannot be removed after it has been started
    */
    public void testMaintenanceFeatures() {
        // Create the MaintenanceFeaturesView and MaintenanceFeaturesController
        MaintenanceFeaturesView maintenanceView = new MaintenanceFeaturesView();
        MaintenanceFeaturesController maintenanceController = new MaintenanceFeaturesController(model.getMachine(), maintenanceView, scanner);

        // Start the maintenance features
        maintenanceController.start();
    }
}
