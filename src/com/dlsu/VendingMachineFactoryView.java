public class VendingMachineFactoryView {
    public void displayMainMenu() {
        System.out.println("==== Vending Machine Factory ====");
        System.out.println("[1] Create a Vending Machine");
        System.out.println("[2] Test a Vending Machine");
        System.out.println("[3] Exit");
        System.out.print("Enter choice: ");
    }

    public void displayCreateMenu() {
        System.out.println("==== Create a Vending Machine ====");
        System.out.println("[1] Create a Regular Vending Machine");
        System.out.println("[2] Create a Special Vending Machine");
        System.out.println("[3] Back");
        System.out.print("Enter choice: ");
    }

    public void displayTestMenu() {
        System.out.println("==== Test a Vending Machine ====");
        System.out.println("[1] Vending Features");
        System.out.println("[2] Maintenance Features");
        System.out.println("[3] Back");
        System.out.print("Enter choice: ");
    }

    public void displayVendingFeatures() {
        System.out.println("==== Vending Features ====");
        System.out.println("[1] Display items");
        System.out.println("[2] Insert money");
        System.out.println("[3] Buy an item");
        System.out.println("[4] Customize a product");
        System.out.println("[5] Back");
        System.out.print("Enter choice: ");
    }

    public void displayMaintenanceFeatures() {
        System.out.println("==== Maintenance Features ====");
        System.out.println("[1] Restock an item");
        System.out.println("[2] Set item price");
        System.out.println("[3] Collect payment");
        System.out.println("[4] Replenish money");
        System.out.println("[5] Print transaction summary");
        System.out.println("[6] Back");
        System.out.print("Enter choice: ");
    }
    public void displayRegularVendingMachineCreatedMessage() {
        System.out.println("Regular vending machine created.");
    }

    public void displaySpecialVendingMachineCreatedMessage() {
        System.out.println("Special vending machine created.");
    }

    public void promptBillsToInsert() {
        System.out.println("Enter the bills you want to insert, separated by spaces (e.g., '100 100 20'):");
    }

    public void promptItemToBuy() {
        System.out.println("Enter the number of the item you want to buy:");
    }

    public void promptItemToRestock() {
        System.out.println("Enter the number of the item you want to restock:");
    }

    public void promptQuantityToAdd() {
        System.out.println("Enter the quantity you want to add:");
    }

    public void promptItemForPriceChange() {
        System.out.println("Enter the number of the item for which you want to set the price:");
    }

    public void promptNewPrice() {
        System.out.println("Enter the new price:");
    }

    public void promptDenominationToReplenish() {
        System.out.println("Enter the denomination you want to replenish:");
    }

    public void displayNoVendingMachineError() {
        System.out.println("No vending machine created. Please create a vending machine first.");
    }

    public void displayInvalidChoiceError() {
        System.out.println("Invalid choice. Please try again.");
    }
}
