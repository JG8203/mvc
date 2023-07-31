import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class VendingFeaturesController {
    private VendingMachineController machineController;
    private VendingFeaturesView view;
    private Scanner scanner;
    
    public VendingFeaturesController(VendingMachineController machineController, VendingFeaturesView view, Scanner scanner) {
        this.machineController = machineController;
        this.view = view;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            view.displayMenu();
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                machineController.displayItems();
            } else if (choice.equals("2")) {
                view.displayInsertMoneyPrompt();
                List<Integer> bills = Arrays.stream(scanner.nextLine().split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                machineController.insertMoney(bills);
            } else if (choice.equals("3")) {
                view.displayBuyItemPrompt();
                int slotNumber = Integer.parseInt(scanner.nextLine());
                machineController.buyItem(slotNumber);
            } else if (choice.equals("4")) {
                if (machineController instanceof SpecialVendingMachineController) {
                    view.displayCustomizeProductPrompt();
                    int slotNumber = Integer.parseInt(scanner.nextLine());
                    ((SpecialVendingMachineController) machineController).customizeProduct(slotNumber, scanner);
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else if (choice.equals("5")) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
