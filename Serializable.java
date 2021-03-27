import org.json.JSONObject;

public interface Serializable{
    public JSONObject toJSON();
    public void importFromJSON(JSONObject root);
}