import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class FoodBankList implements Serializable{
    protected ArrayList<FoodBank> foodBanks = new ArrayList<FoodBank>();

    /**
     * default constructor
     */
    public FoodBankList(){

    }

    /**
     * alternate constructor, imports the properties of this object from the provided JSONObject
     * @param root - the JSONObject containing the properties of this FoodBankList
     */
    public FoodBankList(JSONObject root){
        importFromJSON(root);
    }

    /**
     * converts this object to a JSONObject containing all its properties
     * required by Serialization interface
     */
    public JSONObject toJSON(){
        JSONObject root = new JSONObject();
        JSONArray banks = new JSONArray();
        foodBanks.forEach(obj -> {
            FoodBank fb = (FoodBank)obj;
            banks.put(fb.toJSON());
        });

        root.put("FoodBanks", banks);

        return root;
    }

    /**
     * imports the properties of this FoodBankList from the provided JSONObject
     * required by Serialization interface
     * @param root - the JSONObject to import the properties from
     */
    public void importFromJSON(JSONObject root){
        JSONArray banks = root.getJSONArray("FoodBanks");
        banks.forEach(obj -> {
            foodBanks.add(new FoodBank((JSONObject)obj));
        });
    }

    /**
     * add a food bank to this FoodBankList
     * @param fb - the FoodBank to add to the list
     */
    public void addFoodBank(FoodBank fb){
        foodBanks.add(fb);
    }

    /**
     * gets a reference to the FoodBank object with the specified name
     * @param name - the name of the food bank to retrieve the reference to
     * @return - the retrieved reference to the specified FoodBank object
     */
    public FoodBank getFoodBank(String name){
        int ind = foodBanks.indexOf(new FoodBank(name));
        if(ind != -1){
            return foodBanks.get(ind);
        }
        else{
            return null;
        }
    }

    /**
     * applies a food transport suggestion by removing the specified items from the source and adding them to the destination
     * @param fts - the FoodTransportSuggestion to apply
     */
    public void applyFoodTransportSuggestion(FoodTransportSuggestion fts){
        fts.getFoods().getFoodItems().forEach(fi -> {
            fts.destination.addFoodItem(fi);
            fi.setQuantity(fi.getQuantity() * -1);
            fts.source.addFoodItem(fi);
        });
    }

    /*
    GETTERS AND SETTERS
    */

    public ArrayList<FoodBank> getFoodBanks(){
        return foodBanks;
    }
}
