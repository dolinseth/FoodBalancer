import java.util.ArrayList;

import org.json.JSONObject;

public class FoodBank implements Serializable, Comparable<FoodBank> {
    protected String name;
    protected FoodList foodList = new FoodList();
    protected FoodRequirements foodRequirements;
    protected Coordinate location;

    /**
     * default constructor
     * @param name - the name of the FoodBank (should be unique to each FoodBank)
     */
    public FoodBank(String name){
        this.name = name;
    }

    /**
     * alternate constructor, imports properties from JSONObject
     * @param root - the JSONObject containing the properties to import
     */
    public FoodBank(JSONObject root){
        importFromJSON(root);
    }

    /**
     * imports the properties of the object from JSON
     * required by Serialization interface
     * @param root - the JSONObject to import the properties from
     */
    public void importFromJSON(JSONObject root){
        name = root.getString("Name");
        location = new Coordinate(root.getJSONObject("Location"));
        foodList = new FoodList(root.getJSONObject("FoodList"));
        foodRequirements = new FoodRequirements(root.getInt("TotalPeopleInArea"));
    }

    /**
     * converts the object to a JSONObject for data persistence
     * required by Serialization interface
     */
    public JSONObject toJSON(){
        JSONObject root = new JSONObject();
        root.put("Name", name);
        root.put("Location", location.toJSON());
        root.put("FoodList", foodList.toJSON());
        root.put("TotalPeopleInArea", foodRequirements.getTotalPeopleInArea());

        return root;
    }

    /**
     * calculates the surplus of calories at the given food bank (negative if there is a deficit)
     * assumes that the food bank needs to support all people in the area living below the poverty line for one week
     * @return - the calorie surplus (negative if it's a deficit)
     */
    public int calculateSurplusCalories(){
        if(foodRequirements != null){
            int surplusCalories = foodList.getTotalCalories() - foodRequirements.getTotalPeopleInArea()*2000*7;
            return surplusCalories;
        }
        else{
            return 0;
        }
    }

    public FoodBank getNearestFoodBank(FoodBankList fbl){
        double minDist = Double.MAX_VALUE;
        FoodBank nearest = null;
        for(FoodBank fb : fbl.getFoodBanks()){
            double distance = fb.getLocation().getDistanceTo(this.getLocation());
            if(distance < minDist){
                minDist = distance;
                nearest = fb;
            }
        }

        return nearest;
    }

    public FoodItem getMostAbundantItem(){
        ArrayList<FoodItem> foods = foodList.getFoodItems();
        int maxQuantity = -1;
        FoodItem mostAbundant = null;
        for(FoodItem fi : foods){
            if(fi.getQuantity() > maxQuantity){
                maxQuantity = fi.getQuantity();
                mostAbundant = fi;
            }
        }

        return mostAbundant;
    }

    /**
     * overridden compareTo method, to allow List.contains() to work properly
     */
    @Override
    public int compareTo(FoodBank fb){
        if(fb.getName().compareTo(name) == 0){
            return 0;
        }
        else{
            return 1;
        }
    }

    /*
    GETTERS AND SETTERS
    */

    public FoodList getFoodList(){
        return foodList;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Coordinate getLocation(){
        return location;
    }

    public void setLocation(Coordinate coordinate){
        location = coordinate;
    }

    public void addFoodItem(FoodItem fi){
        foodList.addFoodItem(fi);
    }

    public void setFoodRequirements(int totalPeopleInArea){
        foodRequirements = new FoodRequirements(totalPeopleInArea);
    }
}