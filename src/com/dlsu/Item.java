import java.util.ArrayList;
import java.util.List;

/**
 * The Item class represents an item in a menu or inventory with properties like name, price, calories, type, ingredients, and action.
 *
 * Each item can be of three types:
 * - 'base': Base item used as the main component of a menu item.
 * - 'addon': Add-on item that can be added to a base item to customize it.
 * - 'product': Fully assembled product available for purchase.
 *
 * Items can have a name, price, calories, and a user-defined action. They can also have a list of ingredients, which are other items associated with them, in case the item is a 'base' or 'addon'.
 */

 public class Item {
    private String name;
    private int price;
    private int calories;
    private String type;  // 'base', 'addon', or 'product'
    private List<Item> ingredients = new ArrayList<>();
    private String action;

    public Item(String name, int price, int calories, String type, String action) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.type = type;
        this.action = action;
    }

    /**
    * Returns the name of this entity. This is the entity's name in the form of a java. lang. String object.
    * 
    * 
    * @return the entity's name in the form of a java. lang. String object or null if there is no
    */
    public String getName() {
        return name;
    }

    /**
    * Sets the name of the resource. This is used to distinguish resources from resource types that are in a resource group.
    * 
    * @param name - the name of the resource to be set as
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
    * Returns the price of the item. This is used to determine how much the item has been sold to the customer in order to make it available for a purchase.
    * 
    * 
    * @return the price of the item or - 1 if there is no price associated with the item ( for example if it has been purchased
    */
    public int getPrice() {
        return price;
    }

    /**
    * Sets the price of the order. Note that this is a property and should not be changed in future releases
    * 
    * @param price - The price of the
    */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
    * Returns the number of calories. This is used to determine how many items are in the cart.
    * 
    * 
    * @return the number of calories in the cart ( 1 - 4 ) or - 1 if there is no
    */
    public int getCalories() {
        return calories;
    }

    /**
    * Sets the number of calories to be generated. This is used when creating a project or updating an existing project
    * 
    * @param calories - the number of calories to
    */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
    * Returns the type of the message. This is the message type that will be used to create the JabRef message.
    * 
    * 
    * @return the type of the message or null if there is no type associated with the message ( for example if the message is a JSON message
    */
    public String getType() {
        return type;
    }

    /**
    * Sets the type of the event. This is used to determine whether or not an event is a user event or a web event
    * 
    * @param type - the type of the
    */
    public void setType(String type) {
        this.type = type;
    }

    /**
    * Returns a list of ingredients. This is the same as #getIngredients () but returns a List instead of a List.
    * 
    * 
    * @return a non - null List of ingredients in this item's inventory or null if there are
    */
    public List<Item> getIngredients() {
        return ingredients;
    }

    /**
    * Sets the ingredients to be displayed. This is a List of items that are the same type as the ingredients in the School
    * 
    * @param ingredients - a List of items
    */
    public void setIngredients(List<Item> ingredients) {
        this.ingredients = ingredients;
    }

    /**
    * Returns the action to be performed. This is a string that can be used to perform a query on the data source.
    * 
    * 
    * @return the action to be performed on the data source or null if there is no action to be performed on
    */
    public String getAction() {
        return action;
    }

    /**
    * Sets the action to be performed when the button is pressed. Default is " save ". Note that this is a string that will be passed to the button's onAction method as the value of the button
    * 
    * @param action - the action to be
    */
    public void setAction(String action) {
        this.action = action;
    }
}