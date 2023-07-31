import java.util.Scanner;

public class MaintenanceFeaturesController {
    private VendingMachineController machineController;
    private MaintenanceFeaturesView view;
    private Scanner scanner;
    
    public MaintenanceFeaturesController(VendingMachineController machineController, MaintenanceFeaturesView view, Scanner scanner) {
        this.machineController = machineController;
        this.view = view;
        this.scanner = scanner;
    }

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
