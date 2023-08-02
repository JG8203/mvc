import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class VendingMachineFactoryModel {
    private VendingMachineController machine;

    public VendingMachineFactoryModel() {
        this.machine = null;
    }

    /**
    * Returns the vending machine. Note that this can be null in which case a machine is returned without any action.
    * 
    * 
    * @return the vending machine or null if none is available for the current action ( such as an empty action
    */
    public VendingMachineController getMachine() {
        return machine;
    }

    /**
    * Sets the vending machine. Used to communicate with the virtual machine when it is in the middle of a game
    * 
    * @param machine - the vending machine to
    */
    public void setMachine(VendingMachineController machine) {
        this.machine = machine;
    }
    /**
    * Creates a vending machine that does not have any items. Note that this is different from the one used in createMachine ()
    */
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

    /**
    * Creates a vending machine with special items and addons. Note that you cannot add items to the machine
    */
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
