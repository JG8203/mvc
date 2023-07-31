import java.util.ArrayList;
import java.util.List;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Item> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Item> ingredients) {
        this.ingredients = ingredients;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}