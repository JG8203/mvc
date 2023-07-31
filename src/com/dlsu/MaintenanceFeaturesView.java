public class MaintenanceFeaturesView {
    public void displayMenu() {
        System.out.println("==== Maintenance Features ====");
        System.out.println("[1] Restock an item");
        System.out.println("[2] Set item price");
        System.out.println("[3] Collect payment");
        System.out.println("[4] Replenish money");
        System.out.println("[5] Print transaction summary");
        System.out.println("[6] Back");
        System.out.print("Enter choice: ");
    }
    
    public void displayRestockPrompt() {
        System.out.println("Enter the number of the item you want to restock:");
        System.out.println("Enter the quantity you want to add:");
    }
    
    public void displaySetItemPricePrompt() {
        System.out.println("Enter the number of the item for which you want to set the price:");
        System.out.println("Enter the new price:");
    }
    
    public void displayReplenishMoneyPrompt() {
        System.out.println("Enter the denomination you want to replenish:");
        System.out.println("Enter the quantity you want to add:");
    }
}
