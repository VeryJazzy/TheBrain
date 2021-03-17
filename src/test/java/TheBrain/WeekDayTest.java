package TheBrain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WeekDayTest {

    @Test
    void testCorrectDayReturned() {
        LocalDate aMonday = LocalDate.of(2021, 3,15);
        String day = WeekDay.getWeekDay(aMonday);
        assertEquals("Monday", day);
    }
    @Test
    void testCorrectDayReturned2() {
        LocalDate aMonday = LocalDate.of(2022, 1,1);
        String day = WeekDay.getWeekDay(aMonday);
        assertEquals("Saturday", day);
    }

}