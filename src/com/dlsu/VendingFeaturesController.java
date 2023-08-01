import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The VendingFeaturesController class represents a controller for handling various vending machine features
 * like displaying items, inserting money, buying items, and customizing products (for special vending machines).
 */
public class VendingFeaturesController {
    private VendingMachineController machineController;
    private VendingFeaturesView view;
    private Scanner scanner;

    /**
     * Constructs a VendingFeaturesController with the given dependencies.
     *
     * @param machineController The VendingMachineController instance associated with the vending machine.
     * @param view              The VendingFeaturesView instance responsible for displaying vending machine feature options.
     * @param scanner           The Scanner instance used for user input.
     */
    public VendingFeaturesController(VendingMachineController machineController, VendingFeaturesView view, Scanner scanner) {
        this.machineController = machineController;
        this.view = view;
        this.scanner = scanner;
    }

    /**
     * Starts the vending features controller, displaying the main menu and handling user input until the user chooses to exit.
     * The method processes user choices and delegates actions to the VendingMachineController accordingly.
     */
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
