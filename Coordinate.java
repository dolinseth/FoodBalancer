import org.json.JSONObject;

public class Coordinate implements Serializable{
    protected double lat, lon;

    public Coordinate(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }

    public Coordinate(JSONObject root){
        importFromJSON(root);
    }

    public JSONObject toJSON(){
        JSONObject root = new JSONObject();
        root.put("Lat", lat);
        root.put("Lon", lon);

        return root;
    }

    public void importFromJSON(JSONObject root){
        this.lat = root.getDouble("Lat");
        this.lon = root.getDouble("Lon");
    }

    public double getDistanceTo(Coordinate c){
        //we will treat phi_1 and lambda_1 as the lattitude and longitude of this point, and phi_2/lambda_2 as the lat+lon of c
        double earthRadius = 6.371 * Math.pow(10, 6);
        double phi_1 = degreesToRadians(lat);
        double lambda_1 = degreesToRadians(lon);
        double phi_2 = degreesToRadians(c.getLat());
        double lambda_2 = degreesToRadians(c.getLon());

        double dPhi = Math.abs(phi_1 - phi_2); //absolute difference in lattitude
        double dLambda = Math.abs(lambda_1 - lambda_2); //absolute difference in longitude
        double centralAngle = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(dPhi / 2.0), 2) + Math.cos(phi_1) * Math.cos(phi_2) * Math.pow(Math.sin(dLambda / 2.0), 2)));
        double gcDistance = centralAngle * earthRadius;

        return gcDistance;
    }

    private double radiansToDegrees(double radians){
        return radians * (180.0 / Math.PI);
    }

    private double degreesToRadians(double degrees){
        return degrees * (Math.PI / 180.0);
    }

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
