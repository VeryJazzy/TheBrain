package TheBrain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void ClientReceivesResponse(){
        String APIkey = "fea71035d90ab1370f7d96c136dbc3b6";
        String response = Client.ExecuteGetRequest("http://api.openweathermap.org/data/2.5/weather?q=London&appid=" + APIkey);
        assertNotEquals(response, null);
    }

    @Test
    void ClientReceivesNullIfInvalid(){
        String APIkey = "fea71035d90ab1370f7d96c136dbc3b6";
        String response = Client.ExecuteGetRequest("sdfgzdfh");
        assertNull(response);
    }

}