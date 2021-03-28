import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.JSONObject;

public class FoodItem implements Serializable, Comparable<FoodItem>{
    protected enum FoodType {FATS_OILS_SWEETS, DAIRY, MEAT, VEGETABLE, FRUIT, GRAIN, NONE};
    protected ArrayList<FoodType> foodTypes = new ArrayList<FoodType>();
    protected String name = "";
    protected int quantity = 0;
    protected int caloriesPerServing = 0;
    protected int fatPerServing = 0;
    protected int cholesterolPerServing = 0;
    protected int carbsPerServing = 0;
    protected Date expirationDate = new GregorianCalendar(121, 2, 27).getTime();
    protected int servingsPerContainer = 0;

    /**
     * default constructor
     */
    public FoodItem(String name){
        this.name = name;
    }

    /**
     * alternate constructor that takes in a JSONObject to import the FoodItem from a JSON file
     * @param root - the JSONObject representing this FoodItem
     */
    public FoodItem(JSONObject root){
        importFromJSON(root);
    }

    /**
     * imports the properties of this food item from a JSONObject
     * required by Serialization interface
     * @param root - the JSONObject to import the properties from
     */
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
        foodTypes = getFoodTypesFromString(root.getString("FoodTypes"));
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
        root.put("FoodTypes", getFoodTypesString());

        return root;
    }

    /**
     * helper function to convert the food types that apply to this FoodItem to a space separated string for JSON exporting
     * @return - the space separated string containing the food types
     */
    protected String getFoodTypesString(){
        String ret = "";
        for(int i = 0; i < foodTypes.size(); i++){
            ret += foodTypes.get(i).toString();
            if(i != foodTypes.size() - 1){
                ret += " ";
            }
        }

        return ret;
    }

    /**
     * helper function that converts a space separated string of food types (created by getFoodTypesString()) to an ArrayList of FoodTypes to be imported
     * @param ftString - the space separated string of food types to add to the list
     * @return - an ArrayList<FoodType> containing each of the FoodTypes specified in the input string
     */
    private ArrayList<FoodType> getFoodTypesFromString(String ftString){
        ArrayList<FoodType> fts = new ArrayList<FoodType>();
        String[] ftSplit = ftString.split(" ");
        for(int i = 0; i < ftSplit.length; i++){
            fts.add(foodTypeStrToEnum(ftSplit[i]));
        }

        return fts;
    }

    /**
     * helper function that converts a string containing a single food type into the FoodType enumerated type representing that food type
     * @param foodTypeString - the string containing a single food type
     * @return - the enumerated type representation of the input string
     */
    private FoodType foodTypeStrToEnum(String foodTypeString){
        FoodType result;
        switch(foodTypeString){
            case "FATS_OILS_SWEETS":
                result = FoodType.FATS_OILS_SWEETS; 
                break;
            case "DAIRY":
                result = FoodType.DAIRY;
                break;
            case "MEAT":
                result = FoodType.MEAT;
                break;
            case "VEGETABLE":
                result = FoodType.VEGETABLE;
                break;
            case "FRUIT":
                result = FoodType.FRUIT;
                break;
            case "GRAIN":
                result = FoodType.GRAIN;
                break;
            default:
                result = FoodType.NONE;
                break;
        }

        return result;
    }

    /**
     * compares this FoodItem to another, returns true if they are equivalent, false otherwise
     * @param fi - the FoodItem to compare this one to
     * @return - boolean representing whether the provided FoodItem is equivalent to this FoodItem
     */
    @Override
    public int compareTo(FoodItem fi){
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

    public ArrayList<FoodType> getFoodTypes(){
        return foodTypes;
    }

    public void addFoodType(FoodType ft){
        if(!foodTypes.contains(ft)){
            foodTypes.add(ft);
        }
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