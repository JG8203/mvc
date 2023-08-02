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

    public void customizeProduct(int slotNumber) {
        Slot baseItemSlot = model.getSlots().get(slotNumber - 1);
        if (!model.checkIfCanCustomize(baseItemSlot)) {
            view.displayInsufficientBalance();
            return;
        }

        List<String> choices = new ArrayList<>();
        Item item = model.getSlots().get(slotNumber - 1).getItem();

        view.displayAddOns(item.getIngredients());
        view.displaySelectAddOnPrompt();

        view.setSubmitSelectAction(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String choice = view.getCustomizeField();
                if (choice.toLowerCase().equals("done")) {
                    view.disposeCustomizePrompt();
                    view.disposeAddOns();
                }
                choices.add(choice);
            }
        });
        int remainingBalance = model.customizeProduct(slotNumber, choices);

        Map<Integer, Integer> change = model.getCoinBox().returnChange(remainingBalance); // Get the change from the model
        if (change != null) {
            view.displayChange(change); // Display the change
        }
    }
}
