import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class Dronedynamics{
    // Attributes extends
    private String timestamp;
    private String url;
    private Drone drone;
    private double speed;
    private String alignmentRoll;
    private String controlRange;
    private String alignmentYaw;
    private String longitude;
    private String latitude;
    private int batteryStatus;
    private String lastSeen;
    private String status;
    private static final String USER_AGENT = "MOzilla FIrefox Awesome version";
    private static final String TOKEN = "Token bdaf7b42fe5e499577325a6443b1e04f79461592";

    // Constructor
    public Dronedynamics(String timestamp, String url_, double speed, String alignmentRoll,
                         String controlRange, String alignmentYaw, String longitude, String latitude,
                         int batteryStatus, String lastSeen, String status) {
        this.timestamp = timestamp;
        this.url = url_;
        this.alignmentRoll = alignmentRoll;
        this.controlRange = controlRange;
        this.alignmentYaw = alignmentYaw;
        this.longitude = longitude;
        this.latitude = latitude;
        this.batteryStatus = batteryStatus;
        this.lastSeen = lastSeen;
        this.status = status;

        String drone_ = url_;
        
        try{
            String droneTypeData = datos(drone_);

            try{
                JSONObject o = new JSONObject(droneTypeData);
    
                if (o.has("carriage_type") && o.has("carriage_weight")) {
                    String carriageType = o.getString("carriage_type");
                    int carriageWeight = o.getInt("carriage_weight");
                    int id = o.getInt("id");
                    String dronetype = o.getString("dronetype");
                    String created = o.getString("created");
                    String serialnumber = o.getString("serialnumber");

                    this.drone = new Drone(id , dronetype, serialnumber, created, carriageWeight, carriageType);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException e){
            e.printStackTrace();
        }


    }


    private static String datos(String url_) throws IOException{
        HttpURLConnection connection = (HttpURLConnection) new URL(url_).openConnection();
        connection.setRequestProperty("Authorization", TOKEN);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
           

            while((line = reader.readLine()) != null){
                response.append(line);
            }

            reader.close();

            return response.toString();

        } else {

            throw new IOException("Error al obtener los datos. Código de estado: " + connection.getResponseCode());
        }
    }

    // Getters


    public String getTimestamp() {
        return timestamp;
    }


    public Drone getDrone() {
        return drone;
    }


    public double getSpeed() {
        return speed;
    }

    public String getUrl(){
        return url;
    }

    public String getAlignmentRoll() {
        return alignmentRoll;
    }


    public String getControlRange() {
        return controlRange;
    }


    public String getAlignmentYaw() {
        return alignmentYaw;
    }


    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }


    public int getBatteryStatus() {
        return batteryStatus;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public String getStatus() {
        return status;
    }

}
