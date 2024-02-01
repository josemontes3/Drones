import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Drone{
    // Drone class specific attributes
    private int id;
    private String url;
    private Dronetype dronetype;
    private String serialnumber;
    private int carriageweight;
    private String carriagetype;
    private String created;
    private static final String USER_AGENT = "MOzilla FIrefox Awesome version";
    private static final String TOKEN = "Token bdaf7b42fe5e499577325a6443b1e04f79461592";

    // Constructor
    public Drone(int id_, String url_, String serialnumber, String created, int carriageweight, String carriagetype) {
        // Initialises the attributes specific to the Drone class
        this.id = id_;
        this.created = created;
        this.serialnumber = serialnumber;
        this.carriageweight = carriageweight;
        this.carriagetype = carriagetype;
        this.url = url_;

        String droneType = url_;
       

        try{
            String droneTypeData = datos(droneType);

            

            try{
                JSONObject o = new JSONObject(droneTypeData);
    
                if (o.has("max_carriage")) {
                    int typeId = o.getInt("id");
                    String manufacturer = o.getString("manufacturer");
                    String typename = o.getString("typename");
                    int weight = o.getInt("weight");
                    int maximum_speed = o.getInt("max_speed");
                    int battery_capacity = o.getInt("battery_capacity");
                    int control_range = o.getInt("control_range");
                    int maximum_carriage = o.getInt("max_carriage");
    
                    this.dronetype = new Dronetype(typeId, manufacturer, typename, weight, maximum_speed,
                            battery_capacity, control_range, maximum_carriage);
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
    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Dronetype getDronetype() {
        return dronetype;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public int getCarriageweight() {
        return carriageweight;
    }

    public String getCarriagetype() {
        return carriagetype;
    }

    public String getCreated() {
        return created;
    }


    @Override
    public String toString() {
        return  "Drone ID: " + getId() + 
                "\n DroneType: " + getUrl() +   
                "\n Created: " + getCreated() +
                "\n SerialNumber: " + getSerialnumber() + 
                "\n CarriageWeight: " + getCarriageweight() +
                "\n CarriageType:" + getCarriagetype();
    }
}

