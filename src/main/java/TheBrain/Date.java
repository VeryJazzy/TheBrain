package TheBrain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {

    public static String today() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        return dtf.format(today);
    }
}
