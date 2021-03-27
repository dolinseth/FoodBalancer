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

    public FoodBankList(JSONObject root){
        importFromJSON(root);
    }

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

    public void importFromJSON(JSONObject root){
        JSONArray banks = root.getJSONArray("banks");
        banks.forEach(obj -> {
            foodBanks.add(new FoodBank((JSONObject)obj));
        });
    }

    public void addFoodBank(FoodBank fb){
        foodBanks.add(fb);
    }

    public void getFoodBank(String name){
        foodBanks.get(foodBanks.indexOf(new FoodBank(name)));
    }

    /*
    GETTERS AND SETTERS
    */

    public ArrayList<FoodBank> getFoodBanks(){
        return foodBanks;
    }
}
