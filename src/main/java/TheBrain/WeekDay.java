package TheBrain;

import java.time.LocalDate;

public class WeekDay {

    public static String getWeekDay(LocalDate date) {
        String day = date.getDayOfWeek().toString();
        day = day.toLowerCase();
        String capital = day.substring(0,1).toUpperCase();
        return  day.replace(day.substring(0,1), capital);
    }

    public static String getWeekDay() {
        return getWeekDay(LocalDate.now());
    }
}
