import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class VendingMachineFactoryModel {
    private VendingMachineController machine;

    public VendingMachineFactoryModel() {
        this.machine = null;
    }

    public VendingMachineController getMachine() {
        return machine;
    }

    public void setMachine(VendingMachineController machine) {
        this.machine = machine;
    }
    public void createRegularVendingMachine() {
        List<Item> items = new ArrayList<>(Arrays.asList(
                new Item("Bread", 20, 200, "base", "Placing"),
                new Item("Meat", 50, 300, "base", "Adding"),
                new Item("Cheese", 30, 150, "addon", "Slicing"),
                new Item("Lettuce", 10, 50, "addon", "Adding"),
                new Item("Tomato", 15, 50, "addon", "Slicing"),
                new Item("Mayo", 5, 100, "addon", "Squeezing"),
                new Item("Mustard", 5, 100, "addon", "Squeezing"),
                new Item("Pre-Made Sandwich", 120, 800, "product", "Forming")
        ));
        List<Slot> slots = new ArrayList<>();
        for (Item item : items) {
            slots.add(new Slot(item, 10));  // Assuming Slot has a constructor Slot(Item, int)
        }
        VendingMachineModel machineModel = new VendingMachineModel(slots);
        VendingMachineView machineView = new VendingMachineView();
        this.machine = new VendingMachineController(machineModel, machineView, new Scanner(System.in));
    }

    public void createSpecialVendingMachine() {
        // Create base items and addons
        Item bread = new Item("Bread", 20, 200, "base", "Placing");
        Item meat = new Item("Meat", 50, 300, "base", "Adding");
        Item cheese = new Item("Cheese", 30, 150, "addon", "Slicing");
        Item lettuce = new Item("Lettuce", 10, 50, "addon", "Adding");
        Item tomato = new Item("Tomato", 15, 50, "addon", "Slicing");
        Item mayo = new Item("Mayo", 5, 100, "addon", "Squeezing");
        Item mustard = new Item("Mustard", 5, 100, "addon", "Squeezing");

        // Create product and add addons
        Item sandwich = new Item("Pre-Made Sandwich", 120, 800, "base", "Forming");
        sandwich.getIngredients().add(cheese);
        sandwich.getIngredients().add(lettuce);
        sandwich.getIngredients().add(tomato);
        sandwich.getIngredients().add(mayo);
        sandwich.getIngredients().add(mustard);

        // Add all items to the items list
        List<Item> items = new ArrayList<>(Arrays.asList(bread, meat, cheese, lettuce, tomato, mayo, mustard, sandwich));

        List<Slot> slots = new ArrayList<>();
        for (Item item : items) {
            slots.add(new Slot(item, 10));  // Assuming Slot has a constructor Slot(Item, int)
        }
        SpecialVendingMachineModel machineModel = new SpecialVendingMachineModel(slots);
        SpecialVendingMachineView machineView = new SpecialVendingMachineView();
        this.machine = new SpecialVendingMachineController(machineModel, machineView, new Scanner(System.in));
    }
}
