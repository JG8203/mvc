import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class SpecialVendingMachineController extends VendingMachineController {
    private SpecialVendingMachineModel model;
    private SpecialVendingMachineView view;

    public SpecialVendingMachineController(SpecialVendingMachineModel model, SpecialVendingMachineView view, Scanner scanner) {
        super(model, view, scanner);
        this.model = model;
        this.view = view;
    }

    /**
    * Displays the add - ons to customize a product. This method is called when the user chooses to customize an item.
    *
    * @param slotNumber - The slot number of the product to customize
    */
    public void customizeProduct(int slotNumber) {
        Slot baseItemSlot = model.getSlots().get(slotNumber - 1);
        if (!model.checkIfCanCustomize(baseItemSlot)) {
            view.displayInsufficientBalance();
            return;
        }

        List<String> choices = new ArrayList<>();
        Item baseItem = model.getSlots().get(slotNumber - 1).getItem();

        List<Item> addOns = baseItem.getIngredients(); // Get add-on items from the base item
        view.displayAddOns(addOns);
        view.displaySelectAddOnPrompt();

        List<Item> selectedAddOns = new ArrayList<>();

        view.setSubmitSelectAction(new ActionListener() {
            /**
            * Handles the user clicking on the add - on button. This method is called when the user presses the Done button.
            *
            * @param e - The ActionEvent that caused the method to be called
            */
            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = view.getCustomizeField();

                if (choice.toLowerCase().equals("done")) {
                    view.disposeCustomizePrompt();
                    view.disposeAddOns();

                    // Process the customization and calculate change after "done" is entered
                    int remainingBalance = model.customizeProduct(slotNumber, choices);

                    view.displayAdded(selectedAddOns); // Display the added items

                    Map<Integer, Integer> change = model.getCoinBox().returnChange(remainingBalance); // Get the change from the model
                    if (change != null) {
                        view.displayChange(change); // Display the change
                    }
                } else {
                    int addOnIndex = Integer.valueOf(choice) - 1;
                    if (addOnIndex >= 0 && addOnIndex < addOns.size()) {
                        selectedAddOns.add(addOns.get(addOnIndex));
                        choices.add(choice);
                    }
                }
            }
        });
    }
}
