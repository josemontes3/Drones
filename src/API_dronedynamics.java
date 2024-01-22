import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class API_dronedynamics {
    private static final String USER_AGENT = "MOzilla FIrefox Awesome version";
    private static final String ENDPOINT_URL = "http://dronesim.facets-labs.com/api/dronedynamics/?format=json";
    private static final String TOKEN = "Token bdaf7b42fe5e499577325a6443b1e04f79461592";

    public List<Dronedynamics> dronedyna() {
        System.out.println("Test started... ");

        URL url;
        try {
            url = new URL(ENDPOINT_URL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine);
                }

                bufferedReader.close();
                return test(response.toString()); // Devolver la lista de Dronedynamics

            } else {
                System.out.println("Server response error, check connection");
            }

        } catch (MalformedURLException e) {
            System.out.println("The URL provided is not in valid format" + e.getLocalizedMessage());
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("General IO Exception" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        return new ArrayList<>(); // Devolver una lista vacía en caso de error
    }

    public List<Dronedynamics> test(String input) {
        System.out.println("Response from server: \n " + input);

        List<Dronedynamics> droneList = new ArrayList<>(); // Lista para almacenar objetos Dronedynamics

        try{
            JSONObject jsonResponse = new JSONObject(input);
            JSONArray jsonFile = jsonResponse.getJSONArray("results");

            for (int i = 0; i < jsonFile.length(); i++){

            JSONObject o = jsonFile.getJSONObject(i);
                if (o.has("align_roll") && o.has("align_pitch") && o.has("align_yaw")){
                    String timestamp = o.getString("timestamp");
                    String drone = o.getString("drone");
                    double speed = o.getInt("speed");
                    String alignment_roll = o.getString("align_roll");
                    String control_range = o.getString("align_pitch");
                    String   alignment_yaw = o.getString("align_yaw");
                    String longitude = o.getString("longitude");
                    String latitude = o.getString("latitude");
                    int battery_status = o.getInt("battery_status");
                    String last_seen = o.getString("last_seen");
                    String status = o.getString("status");

                    Dronedynamics droneData = new Dronedynamics(i, timestamp, drone, speed, alignment_roll,
                         control_range, alignment_yaw, longitude, latitude,
                         battery_status, last_seen, status);

                    // Agregar el objeto a la lista
                    droneList.add(droneData);

                    // Mostrar los datos por pantalla usando el método toString()
                    //System.out.println(droneData.toString());
                
                   /*System.out.println("Timestamp:  " + timestamp + "  \n\t Drone:  " + drone + " \n\t Speed:  " + speed + ": \n\t Alignment_roll :  " 
                    +  alignment_roll +  " \n\t Control_range: " + control_range + " \n\t Alignment Yaw: " + alignment_yaw + "\n\t Longitude: " + longitude + "\n\t Latitude: " + latitude +
                    "\n\t Battery Status: " + battery_status + "\n\t Last Seen: " + last_seen + "\n\t Status: " + status + "\n");
                    */
                }
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return droneList;
    }

    public static String formatJson(String input){
        final int indentSpaces = 4;
        Object json = new JSONTokener(input).nextValue();

        if(json instanceof JSONObject){
            JSONObject o = (JSONObject) json;

            return o.toString(indentSpaces);
        } else if (json instanceof JSONArray){
            return ((JSONArray) json).toString(indentSpaces);
        } else {
            throw new IllegalArgumentException("Input string is not a valid JSON");
        }
    }
}
