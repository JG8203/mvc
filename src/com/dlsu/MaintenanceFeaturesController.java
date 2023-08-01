import java.util.Scanner;

/**
 * The MaintenanceFeaturesController class represents a controller for managing maintenance features of a vending machine.
 * It allows restocking items, setting item prices, collecting payments, replenishing money, printing transaction summaries,
 * and breaking out of the maintenance menu.
 */
public class MaintenanceFeaturesController {
    private VendingMachineController machineController;
    private MaintenanceFeaturesView view;
    private Scanner scanner;

    /**
     * Constructs a MaintenanceFeaturesController with the given dependencies.
     *
     * @param machineController The VendingMachineController instance associated with the vending machine.
     * @param view              The MaintenanceFeaturesView instance responsible for displaying maintenance menu options.
     * @param scanner           The Scanner instance used for user input.
     */
    public MaintenanceFeaturesController(VendingMachineController machineController, MaintenanceFeaturesView view, Scanner scanner) {
        this.machineController = machineController;
        this.view = view;
        this.scanner = scanner;
    }

    /**
     * Starts the maintenance features controller, displaying the maintenance menu and handling user input until the user chooses to exit.
     * The method processes user choices and delegates actions to the VendingMachineController accordingly.
     */
    public void start() {
        while (true) {
            view.displayMenu();
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                view.displayRestockPrompt();
                int slotNumber = Integer.parseInt(scanner.nextLine());
                int quantity = Integer.parseInt(scanner.nextLine());
                machineController.restock(slotNumber, quantity);
            } else if (choice.equals("2")) {
                view.displaySetItemPricePrompt();
                int slotNumber = Integer.parseInt(scanner.nextLine());
                int price = Integer.parseInt(scanner.nextLine());
                machineController.setItemPrice(slotNumber, price);
            } else if (choice.equals("3")) {
                machineController.collectPayment();
            } else if (choice.equals("4")) {
                view.displayReplenishMoneyPrompt();
                int denomination = Integer.parseInt(scanner.nextLine());
                int quantity = Integer.parseInt(scanner.nextLine());
                machineController.replenishMoney(denomination, quantity);
            } else if (choice.equals("5")) {
                machineController.printTransactionSummary();
            } else if (choice.equals("6")) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}