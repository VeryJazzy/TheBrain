package TheBrain;

import java.util.UUID;

public class ID {

    public static String createID() {
        return UUID.randomUUID().toString();
    }
}
