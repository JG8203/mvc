public class VendingFeaturesView {
    public void displayMenu() {
        System.out.println("==== Vending Features ====");
        System.out.println("[1] Display items");
        System.out.println("[2] Insert money");
        System.out.println("[3] Buy an item");
        System.out.println("[4] Customize a product (Special Vending Machines only)");
        System.out.println("[5] Back");
        System.out.print("Enter choice: ");
    }
    
    public void displayInsertMoneyPrompt() {
        System.out.println("Enter the bills you want to insert, separated by spaces (e.g., '100 100 20'):");
    }
    
    public void displayBuyItemPrompt() {
        System.out.println("Enter the number of the item you want to buy:");
    }
    
    public void displayCustomizeProductPrompt() {
        System.out.println("Enter the number of the base item you want to customize:");
    }
}
