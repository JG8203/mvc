import java.util.ArrayList;
import java.util.List;

/**
 * The Item class represents an item available in a vending machine. It includes various properties such as
 * name, price, calories, type, ingredients, and action.
 */
public class Item {
    private String name;
    private int price;
    private int calories;
    private String type; // 'base', 'addon', or 'product'
    private List<Item> ingredients = new ArrayList<>();
    private String action;

    /**
     * Constructs an Item with the specified name, price, calories, type, and action.
     *
     * @param name     The name of the item.
     * @param price    The price of the item in the vending machine.
     * @param calories The calorie count of the item.
     * @param type     The type of the item, which can be 'base', 'addon', or 'product'.
     * @param action   The action associated with the item, if any (e.g., 'stir', 'shake', 'mix', etc.).
     */
    public Item(String name, int price, int calories, String type, String action) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.type = type;
        this.action = action;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item.
     *
     * @param name The new name of the item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the item in the vending machine.
     *
     * @return The price of the item.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the item in the vending machine.
     *
     * @param price The new price of the item.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets the calorie count of the item.
     *
     * @return The calorie count of the item.
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Sets the calorie count of the item.
     *
     * @param calories The new calorie count of the item.
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Gets the type of the item (e.g., 'base', 'addon', or 'product').
     *
     * @return The type of the item.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the item.
     *
     * @param type The new type of the item.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the list of ingredients for the item.
     *
     * @return A list containing the ingredients of the item.
     */
    public List<Item> getIngredients() {
        return ingredients;
    }

    /**
     * Sets the list of ingredients for the item.
     *
     * @param ingredients A list containing the ingredients of the item.
     */
    public void setIngredients(List<Item> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Gets the action associated with the item, if any (e.g., 'stir', 'shake', 'mix', etc.).
     *
     * @return The action associated with the item.
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the action associated with the item.
     *
     * @param action The new action associated with the item.
     */
    public void setAction(String action) {
        this.action = action;
    }
}