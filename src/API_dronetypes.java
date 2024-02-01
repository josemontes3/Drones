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

public class API_dronetypes {

    private static final String USER_AGENT = "MOzilla FIrefox Awesome version";
    private static final String ENDPOINT_URL = "http://dronesim.facets-labs.com/api/dronetypes/?format=json&limit=10000";
    private static final String TOKEN = "Token bdaf7b42fe5e499577325a6443b1e04f79461592";

    public List<Dronetype> dronetype() {
        System.out.println("Test started... ");

        try {
            URL url = new URL(ENDPOINT_URL);
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
                return parseResponse(response.toString());

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

    private List<Dronetype> parseResponse(String input) {
        System.out.println("Response from server: \n " + input);

        List<Dronetype> droneList = new ArrayList<>();

        try {
            JSONObject jsonResponse = new JSONObject(input);
            JSONArray jsonFile = jsonResponse.getJSONArray("results");

            for (int i = 0; i < jsonFile.length(); i++) {
                JSONObject o = jsonFile.getJSONObject(i);

                if (o.has("max_carriage")) {
                    int id = o.getInt("id");
                    String manufacturer = o.getString("manufacturer");
                    String typename = o.getString("typename");
                    int weight = o.getInt("weight");
                    int maximum_speed = o.getInt("max_speed");
                    int battery_capacity = o.getInt("battery_capacity");
                    int control_range = o.getInt("control_range");
                    int maximum_carriage = o.getInt("max_carriage");

                    Dronetype droneData = new Dronetype(id, manufacturer, typename, weight, maximum_speed,
                            battery_capacity, control_range, maximum_carriage);

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
