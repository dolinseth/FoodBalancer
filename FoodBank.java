import org.json.JSONObject;

public class FoodBank implements Serializable, Comparable {
    protected String name;
    protected FoodList foodList = new FoodList();
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

    public void importFromJSON(JSONObject root){
        name = root.getString("Name");
        location = new Coordinate(root.getJSONObject("Location"));
        foodList = new FoodList(root.getJSONObject("FoodList"));
    }

    public JSONObject toJSON(){
        JSONObject root = new JSONObject();
        root.put("Name", name);
        root.put("Location", location.toJSON());
        root.put("FoodList", foodList.toJSON());

        return root;
    }

    @Override
    public int compareTo(Object obj){
        if(obj instanceof FoodBank){
            FoodBank fb = (FoodBank)obj;
            if(fb.getName().compareTo(name) == 0){
                return 0;
            }
            else{
                return 1;
            }
        }
        else{
            return -1;
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
}