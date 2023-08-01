import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The SpecialVendingMachineController class represents a controller for a special vending machine that extends the basic
 * VendingMachineController. It adds additional features for customizing products with add-ons and handling change returns.
 */
public class SpecialVendingMachineController extends VendingMachineController {
    private SpecialVendingMachineModel model;
    private SpecialVendingMachineView view;

    /**
     * Constructs a SpecialVendingMachineController with the given dependencies.
     *
     * @param model   The SpecialVendingMachineModel instance associated with the special vending machine.
     * @param view    The SpecialVendingMachineView instance responsible for displaying special vending machine operations.
     * @param scanner The Scanner instance used for user input.
     */
    public SpecialVendingMachineController(SpecialVendingMachineModel model, SpecialVendingMachineView view, Scanner scanner) {
        super(model, view, scanner);
        this.model = model;
        this.view = view;
    }

    /**
     * Allows customization of a product in the vending machine with add-ons.
     * The method checks if the product is customizable, displays the available add-ons, and prompts the user for choices.
     * It then calls the SpecialVendingMachineModel to handle the customization and returns any change if applicable.
     *
     * @param slotNumber The slot number of the product to be customized.
     * @param scanner    The Scanner instance used for user input during customization.
     */
    public void customizeProduct(int slotNumber, Scanner scanner) {
        Slot baseItemSlot = model.getSlots().get(slotNumber - 1);
        if (!model.checkIfCanCustomize(baseItemSlot)) {
            System.out.println("Insufficient balance or selected item is not a base item.");
            return;
        }

        List<String> choices = new ArrayList<>();
        while (true) {
            // Retrieve the item from the slot
            Item item = model.getSlots().get(slotNumber - 1).getItem();

            // Now display the add-ons for the item
            view.displayAddOns(item.getIngredients());

            System.out.println("Enter the number of the add-on you want to include, or 'done' to finish customization.");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();
            if (choice.toLowerCase().equals("done")) {
                break;
            }
            choices.add(choice);
        }
        int remainingBalance = model.customizeProduct(slotNumber, choices);

        Map<Integer, Integer> change = model.getCoinBox().returnChange(remainingBalance); // Get the change from the model
        if (change != null) {
            view.displayChange(change); // Display the change
        }
    }
}