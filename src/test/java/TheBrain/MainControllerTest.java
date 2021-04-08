package TheBrain;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MainControllerTest {

    @Autowired
    private Database database;

    @Autowired
    MainController mainController;


    @Test
    void contextLoads() {
        assertNotNull(mainController);
        assertNotNull(database);
    }

    @Test
    void testAddToDb() {
        int before = database.getEntries("testing").size();
        mainController.handleForm("testing text", "testing");
        int after = database.getEntries("testing").size();
        assertEquals(after, (before + 1));
    }

    @Test
    void testDeletingAnEntry() {
        List<Entry> entryList = database.getEntries("testing");
        int before = database.getEntries("testing").size();
        mainController.deleteEntry(entryList.get(0).getId(), "testing");
        int after = database.getEntries("testing").size();

        assertEquals(after, (before - 1));
    }



}