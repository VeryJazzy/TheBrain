package TheBrain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTest {

    @Test
    void testResponseIsReceived() {
        String temp = Weather.getTemp("Romford");
        assertNotEquals(temp, null);
    }

}