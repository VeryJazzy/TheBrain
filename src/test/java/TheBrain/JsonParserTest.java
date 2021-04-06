package TheBrain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    @Test
    void ReturnsCorrectTemps() {
        String jsonExample = "{\"coord\":{\"lon\":0.1858,\"lat\":51.5752},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02d\"}],\"base\":\"stations\",\"main\":{\"temp\":281.35,\"feels_like\":277.73,\"temp_min\":280.37,\"temp_max\":282.59,\"pressure\":1033,\"humidity\":71},\"visibility\":10000,\"wind\":{\"speed\":3.09,\"deg\":300},\"clouds\":{\"all\":20},\"dt\":1615977889,\"sys\":{\"type\":1,\"id\":1414,\"country\":\"GB\",\"sunrise\":1615961319,\"sunset\":1616004394},\"timezone\":0,\"id\":2639192,\"name\":\"Romford\",\"cod\":200}";
        String parsed = JsonParser.parseWeather(jsonExample);
        assertEquals("8\u2103/47\u2109", parsed);
    }

    @Test
    void ReturnsCorrectTemps2() {
        String jsonExample = "{\"coord\":{\"lon\":0.1858,\"lat\":51.5752},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02d\"}],\"base\":\"stations\",\"main\":{\"temp\":150.35,\"feels_like\":277.73,\"temp_min\":280.37,\"temp_max\":282.59,\"pressure\":1033,\"humidity\":71},\"visibility\":10000,\"wind\":{\"speed\":3.09,\"deg\":300},\"clouds\":{\"all\":20},\"dt\":1615977889,\"sys\":{\"type\":1,\"id\":1414,\"country\":\"GB\",\"sunrise\":1615961319,\"sunset\":1616004394},\"timezone\":0,\"id\":2639192,\"name\":\"Romford\",\"cod\":200}";
        String parsed = JsonParser.parseWeather(jsonExample);
        assertEquals("-123\u2103/-189\u2109", parsed);
    }
}