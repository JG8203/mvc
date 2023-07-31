import java.util.List;

public class SpecialVendingMachineView extends VendingMachineView {
    public void displayAddOns(List<Item> addOns) {
        System.out.println("Available add-ons:");
        for (int i = 0; i < addOns.size(); i++) {
            Item addOn = addOns.get(i);
            System.out.printf("%d. %s - Price: PHP%d, Calories: %d%n",
                    i + 1, addOn.getName(), addOn.getPrice(), addOn.getCalories());
        }
    }
}
