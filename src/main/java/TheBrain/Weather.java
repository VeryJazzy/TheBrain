package TheBrain;

public class Weather {

    static String APIkey = "fea71035d90ab1370f7d96c136dbc3b6";

    public static String getTemp(String city) {
        String json = Client.ExecuteGetRequest("http://api.openweathermap.org/data/2.5/weather?q=London&appid=" + APIkey);
        String temp = JsonParser.parseWeather(json);
        return temp;
    }
}
