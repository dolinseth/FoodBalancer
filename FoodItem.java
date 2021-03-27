import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class FoodItem implements Serializable, Comparable{
    protected String name;
    protected int quantity;
    protected int caloriesPerServing;
    protected int fatPerServing;
    protected int cholesterolPerServing;
    protected int carbsPerServing;
    protected Date expirationDate;
    protected int servingsPerContainer;

    /**
     * default constructor
     */
    public FoodItem(String name){
        this.name = name;
    }

    /**
     * empty constructor for use with importFromJSON();
     */
    public FoodItem(){

    }

    /**
     * alternate constructor that takes in a JSONObject to import the FoodItem from a JSON file
     * @param root - the JSONObject representing this FoodItem
     */
    public FoodItem(JSONObject root){
        importFromJSON(root);
    }

    public void importFromJSON(JSONObject root){
        name = root.getString("Name");
        quantity = root.getInt("Quantity");
        caloriesPerServing = root.getInt("CaloriesPerServing");
        fatPerServing = root.getInt("FatPerServing");
        cholesterolPerServing = root.getInt("CholesterolPerServing");
        carbsPerServing = root.getInt("CarbsPerServing");
        try {
            expirationDate = DateFormat.getInstance().parse(root.getString("ExpirationDate"));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error parsing date for FoodItem with name " + name + ". Date string provided was \"" + root.getString("ExpirationDate") + "\"");
        }
        servingsPerContainer = root.getInt("ServingsPerContainer");

    }

    /**
     * converts this FoodItem to a JSONObject for storing and retrieving data
     * @return - a JSONObject representing this FoodItem
     */
    public JSONObject toJSON(){
        JSONObject root = new JSONObject();
        root.put("Name", name);
        root.put("Quantity", quantity);
        root.put("CaloriesPerServing", caloriesPerServing);
        root.put("FatPerServing", fatPerServing);
        root.put("CholesterolPerServing", cholesterolPerServing);
        root.put("CarbsPerServing", carbsPerServing);
        root.put("ExpirationDate", expirationDate.toString());
        root.put("ServingsPerContainer", servingsPerContainer);

        return root;
    }

    /**
     * compares this FoodItem to another, returns true if they are equivalent, false otherwise
     * @param fi - the FoodItem to compare this one to
     * @return - boolean representing whether the provided FoodItem is equivalent to this FoodItem
     */
    @Override
    public int compareTo(Object obj){
        if(obj instanceof FoodItem){
            FoodItem fi = (FoodItem)obj;
            boolean equivalent = true;
            if(fi.name.compareTo(name) != 0){
                equivalent = false;
            }
            if(fi.caloriesPerServing != caloriesPerServing){
                equivalent = false;
            }
            if(fi.servingsPerContainer != servingsPerContainer){
                equivalent = false;
            }

            return (equivalent) ? (0) : (name.compareTo(fi.getName()));
        }
        else{
            return -1;
        }
    }

    /**
     * updates the quantity for this FoodItem (positive for adding more, negative for removing some)
     * @param quantityChange - the change in quantity of this FoodItem (negative for removing items)
     */
    public void updateQuantity(int quantityChange){
        quantity += quantityChange;
    }

    /*
    GETTERS AND SETTERS
    */

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getCaloriesPerServing(){
        return caloriesPerServing;
    }

    public void setCaloriesPerServing(int calories){
        caloriesPerServing = calories;
    }
    
    public int getTotalCalories(){
        return caloriesPerServing * servingsPerContainer;
    }

    public int getFatPerServing(){
        return fatPerServing;
    }

    public void setFatPerServing(int fat){
        fatPerServing = fat;
    }

    public int getTotalFat(){
        return fatPerServing * servingsPerContainer;
    }

    public int getCholesterolPerServing(){
        return cholesterolPerServing;
    }

    public void setCholesterolPerServing(int cholesterol){
        cholesterolPerServing = cholesterol;
    }

    public int getTotalCholesterol(){
        return cholesterolPerServing * servingsPerContainer;
    }

    public int getCarbsPerServing(){
        return carbsPerServing;
    }

    public void setCarbsPerServing(int carbs){
        carbsPerServing = carbs;
    }

    public int getTotalCarbs(){
        return carbsPerServing * servingsPerContainer;
    }

    public Date getExpirationDate(){
        return expirationDate;
    }

    public void setExpirationDate(Date date){
        expirationDate = date;
    }

    public int getServingsPerContainer(){
        return servingsPerContainer;
    }

    public void setServingsPerContainer(int servingsPerContainer){
        this.servingsPerContainer = servingsPerContainer;
    }
}