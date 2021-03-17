package TheBrain;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Client {

    private static final HttpClient client = HttpClients.createDefault();

    static String ExecuteGetRequest(String uri) {
        try {
            HttpResponse response = client.execute(new HttpGet(uri));
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            System.out.println("Bad request : " + e.getMessage());
            return null;
        }
    }
}

