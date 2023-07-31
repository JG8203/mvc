import java.util.*;

public class SpecialVendingMachineController extends VendingMachineController {
    private SpecialVendingMachineModel model;
    private SpecialVendingMachineView view;

    public SpecialVendingMachineController(SpecialVendingMachineModel model, SpecialVendingMachineView view, Scanner scanner) {
        super(model, view, scanner);
        this.model = model;
        this.view = view;
    }

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
