import org.json.JSONObject;

/**
 * Coordinate class can contain latitude and longitude and calculate distance between two points for coordinating shipments between different food banks
 */
public class Coordinate implements Serializable{
    //latitude and longitude fields
    protected double lat, lon;

    /**
     * default constructor, accepts latitude and longitude
     * @param lat - the latitude, expressed as a double
     * @param lon - the longitude, expressed as a double
     */
    public Coordinate(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * alternate constructor, builds the Coordinate object from a JSON object for data persistence
     * @param root - the JSONObject containing the properties of the Coordinate
     */
    public Coordinate(JSONObject root){
        importFromJSON(root);
    }

    /**
     * returns a JSONObject describing the Coordinate object which can be used with importFromJSON()
     * required by Serializable interface
     */
    public JSONObject toJSON(){
        JSONObject root = new JSONObject();
        root.put("Lat", lat);
        root.put("Lon", lon);

        return root;
    }

    /**
     * imports properties of the object from a JSONObject
     * required by Serializable interface
     * @param root - the JSONObject containing the properties of the Coordinate
     */
    public void importFromJSON(JSONObject root){
        this.lat = root.getDouble("Lat");
        this.lon = root.getDouble("Lon");
    }

    /**
     * gets the Great Circle distance (https://en.wikipedia.org/wiki/Great-circle_distance) between this Coordinate and another one
     * @param c - the other Coordinate to calculate the distance to from this one
     * @return - a double representing the distance in meters from this Coordinate to c
     */
    public double getDistanceTo(Coordinate c){
        //we will treat phi_1 and lambda_1 as the latitude and longitude of this point, and phi_2/lambda_2 as the lat+lon of c
        double earthRadius = 6.371 * Math.pow(10, 6);
        double phi_1 = degreesToRadians(lat);
        double lambda_1 = degreesToRadians(lon);
        double phi_2 = degreesToRadians(c.getLat());
        double lambda_2 = degreesToRadians(c.getLon());

        double dPhi = Math.abs(phi_1 - phi_2); //absolute difference in latitude
        double dLambda = Math.abs(lambda_1 - lambda_2); //absolute difference in longitude
        double centralAngle = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(dPhi / 2.0), 2) + Math.cos(phi_1) * Math.cos(phi_2) * Math.pow(Math.sin(dLambda / 2.0), 2)));
        double gcDistance = centralAngle * earthRadius;

        return gcDistance;
    }

    /**
     * helper function to convert degrees to radians
     * @param degrees - the quantity of degrees to convert to radians
     * @return - the input value converted to radians, expressed as a double
     */
    private double degreesToRadians(double degrees){
        return degrees * (Math.PI / 180.0);
    }

    /*
    GETTERS AND SETTERS
    */

    public double getLat(){
        return lat;
    }

    public void setLat(double lat){
        this.lat = lat;
    }

    public double getLon(){
        return lon;
    }

    public void setLon(double lon){
        this.lon = lon;
    }
}
