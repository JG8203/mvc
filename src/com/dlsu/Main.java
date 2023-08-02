import java.util.Scanner;

public class Main {
    /**
    * Entry point for the VendingMachineFactoryController. Creates a Scanner to read user input creates instances of the factory model view controller and starts the application
    * 
    * @param args - Command line arguments ( ignored
    */
    public static void main(String[] args) {
        // Create a Scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Create instances of the factory model, view, and controller
        VendingMachineFactoryModel factoryModel = new VendingMachineFactoryModel();
        VendingMachineFactoryView factoryView = new VendingMachineFactoryView();
        VendingMachineFactoryController factoryController = new VendingMachineFactoryController(factoryModel, factoryView, scanner);

        // Start the application
        factoryController.start();

        // Close the Scanner
        scanner.close();
    }
}

