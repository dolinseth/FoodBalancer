import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONArray;
import org.json.JSONObject;

public class FoodList implements Serializable {
    protected ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();

    /**
     * default constructor
     */
    public FoodList(){
        //default constructor, nothing here
    }

    /**
     * alternate constructor that imports properties of the object from a JSONObject 
     * @param root - the JSONObject that contains the properties of this FoodList
     */
    public FoodList(JSONObject root){
        importFromJSON(root);
    }


    /**
     * add a FoodItem to this FoodList
     * @param fi - the FoodItem to be added to the list
     */
    public void addFoodItem(FoodItem fi){
        if(foodItems.contains(fi)){
            System.out.println("Error: Tried to add a food item to this list that already exists. To update the quantity of the food item please use updateQuantity()");
        }
        else{
            foodItems.add(fi);
        }
    }

    /**
     * update the quantity of the specified food item in the FoodList
     * @param fi - the FoodItem to search the list for and update the quantity of
     * @param quantityChange - the change in quantity of this item (positive for increase, negative for decrease)
     */
    public void updateQuantity(FoodItem fi, int quantityChange){
        if(foodItems.contains(fi)){
            foodItems.get(foodItems.indexOf(fi)).updateQuantity(quantityChange);
        }
        else{
            System.out.println("Error: Tried to update quantity of a food item that does not exist in this list");
        }
    }

    /**
     * converts the FoodList to a JSONObject
     * required by serialization interface
     */
    public JSONObject toJSON(){
        JSONObject root = new JSONObject();
        JSONArray items = new JSONArray();
        foodItems.forEach(fi -> {
            items.put(fi.toJSON());
        });

        root.put("Items", items);

        return root;
    }

    /**
     * imports the properties of this FoodList from the JSON object
     * required by serialization interface
     * @param root - the JSONObject containing the properties to be imported
     */
    public void importFromJSON(JSONObject root){
        JSONArray items = root.getJSONArray("Items");
        items.forEach(obj -> {
            foodItems.add(new FoodItem((JSONObject)obj));
        });
    }


    /*
    GETTERS AND SETTERS
    */

    public ArrayList<FoodItem> getFoodItems(){
        return foodItems;
    }

    /**
     * calculates the total calories in the entire food list
     * @return - the total number of calories
     */
    public int getTotalCalories(){
        AtomicInteger calories = new AtomicInteger(0);
        foodItems.stream().forEach(fi -> {
            calories.addAndGet(fi.getTotalCalories() * fi.getQuantity());
        });

        return calories.get();
    }
    
    /**
     * calculates the total quantity of fat in the entire food list
     * @return - the total quantity of fat, in grams
     */
    public int getTotalFat(){
        AtomicInteger fat = new AtomicInteger(0);
        foodItems.forEach(fi -> {
            fat.addAndGet(fi.getTotalFat());
        });

        return fat.get();
    }

    /**
     * calculates the total quantity of cholesterol in the entire food list
     * @return - the total quantity of cholesterol, in milligrams
     */
    public int getTotalCholesterol(){
        AtomicInteger cholesterol = new AtomicInteger(0);
        foodItems.forEach(fi -> {
            cholesterol.addAndGet(fi.getTotalCholesterol());
        });

        return cholesterol.get();
    }

    /**
     * calculates the total quantity of carbs in the entire food list
     * @return - the total quantity of carbs, in grams
     */
    public int getTotalCarbs(){
        AtomicInteger carbs = new AtomicInteger(0);
        foodItems.forEach(fi -> {
            carbs.addAndGet(fi.getTotalCarbs());
        });

        return carbs.get();
    }
}
