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
class DatabaseTest {

    @Autowired
    private Database database;

    @Test
    void TestDataBaseIsAccessible() {
        List<Entry> entries = database.getEntries("testing");
        assertNotNull(entries);
    }

    @Test
    void TestgetEntriesReturnsAtLeastOneEntry() {
        List<Entry> entries = database.getEntries("testing");
        assertTrue(entries.size() > 0);
    }

    @Test
    void TestGetEntryWithID() {
        String id = ID.createID();
        List<Entry> list = database.getEntryWithID(id, "testing");

        database.add(new Entry.Builder(id)
                .withDate("2021-03-01")
                .withMessage("this message was added by a test, and was not cleaned up").build(), "testing");
        List<Entry> listWithEntry = database.getEntryWithID(id, "testing");

        assertEquals(list.size(), 0);
        assertEquals(listWithEntry.size(), 1);
    }

    @Test
    void TestAddFunctionality() {
        String id1 = ID.createID();
        String id2 = ID.createID();

        Entry e = new Entry.Builder(id1)
                .withDate("2021-03-01")
                .withMessage("this message was added by a test, and was not cleaned up")
                .build();
        Entry e2 = new Entry.Builder(id2)
                .withDate("2021-03-01")
                .withMessage("this message was added by a test, and was not cleaned up")
                .build();
        int numberOfRowsAffected = database.add(e, "testing");
        numberOfRowsAffected += database.add(e2, "testing");

        database.delete(id1, "testing");
        database.delete(id2, "testing");
        assertEquals(2, numberOfRowsAffected);
    }

    @Test
    void TestDeleteFunctionality() {
        String id = ID.createID();
        Entry e = new Entry.Builder(id)
                .withDate("2021-03-01")
                .withMessage("this message was added by a test, and was not cleaned up")
                .build();
        database.add(e, "testing");
        List<Entry> listWithID = database.getEntryWithID(id, "testing");
        database.delete(id, "testing");
        List<Entry> listWithoutID = database.getEntryWithID(id, "testing");

        assertEquals(1,listWithID.size());
        assertEquals(0,listWithoutID.size());
    }




}