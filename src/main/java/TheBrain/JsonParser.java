package TheBrain;

import org.json.JSONObject;

public class JsonParser {

    static String parseWeather(String json) {
        JSONObject jObj = new JSONObject(json);
        double kelvinTemp = jObj.getJSONObject("main").getDouble("temp");
        int celcius = (int) Math.round(kelvinTemp - 273.15);
        int fahrenheit = (int) Math.round(kelvinTemp * 1.8 - 459.67);
        return celcius + "℃" + " / " + fahrenheit + "℉";
    }


}
