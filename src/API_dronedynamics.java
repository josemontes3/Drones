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
    private static final String USER_AGENT = "Mozilla Firefox Awesome version";
    private static final String BASE_URL = "http://dronesim.facets-labs.com/api/%d/dynamics/?format=json&limit=100000";
    private static final String TOKEN = "Token bdaf7b42fe5e499577325a6443b1e04f79461592";

    public List<Dronedynamics> dronedyna(int droneId) {
        System.out.println("Test started... ");

        String urlString = String.format(BASE_URL, droneId);

        URL url;
        try {
            url = new URL(urlString);

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
                return parseResponse(response.toString());// Return the list of Dronedynamics

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

        return new ArrayList<>(); // Return an empty list in case of error
    }

    private List<Dronedynamics> parseResponse(String input) {
        System.out.println("Response from server: \n " + input);

        List<Dronedynamics> droneList = new ArrayList<>();// Dronedynamics object storage list

        try {
            JSONObject jsonResponse = new JSONObject(input);
            JSONArray jsonFile = jsonResponse.getJSONArray("results");

            for (int i = 0; i < jsonFile.length(); i++) {

                JSONObject o = jsonFile.getJSONObject(i);
                if (o.has("align_roll") && o.has("align_pitch") && o.has("align_yaw")) {
                    String timestamp = o.getString("timestamp");
                    String drone = o.getString("drone");
                    double speed = o.getInt("speed");
                    String alignment_roll = o.getString("align_roll");
                    String control_range = o.getString("align_pitch");
                    String alignment_yaw = o.getString("align_yaw");
                    String longitude = o.getString("longitude");
                    String latitude = o.getString("latitude");
                    int battery_status = o.getInt("battery_status");
                    String last_seen = o.getString("last_seen");
                    String status = o.getString("status");

                    Dronedynamics droneData = new Dronedynamics(timestamp, drone, speed, alignment_roll,
                            control_range, alignment_yaw, longitude, latitude, battery_status, last_seen, status);

                    // Add the object to the list
                    droneList.add(droneData);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return droneList;
    }

    public static String formatJson(String input) {
        final int indentSpaces = 4;
        Object json = new JSONTokener(input).nextValue();

        if (json instanceof JSONObject) {
            JSONObject o = (JSONObject) json;

            return o.toString(indentSpaces);
        } else if (json instanceof JSONArray) {
            return ((JSONArray) json).toString(indentSpaces);
        } else {
            throw new IllegalArgumentException("Input string is not a valid JSON");
        }
    }
}
