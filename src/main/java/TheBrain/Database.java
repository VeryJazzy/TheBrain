package TheBrain;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Database {

    private LocalDate savedDate;
    private Entry todaysQuoteEntry;
    private List<Entry> todaysDailys;

    private final JdbcTemplate jdbcTemplate;

    public Database(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
        savedDate = LocalDate.of(1995,5,28);
        todaysQuoteEntry = getRandomEntry("quotes");
        todaysDailys = getEntries("dailys");
    }



    public int add(Entry entry, String table) {
        int rowsAffected = jdbcTemplate.update("INSERT INTO " + table + " VALUES (?, ?, ?)", entry.getId(), entry.getDate(), entry.getText());
        if (table.equals("dailys")) {
            todaysDailys = getEntries("dailys");
        }
        return rowsAffected;
    }

    public void delete(String id, String table) {
        jdbcTemplate.update("DELETE FROM " + table + " WHERE id=?", id);
    }

    public List<Entry> getEntries(String table) {
        String query = "SELECT * FROM " + table;
        List<Entry> entryList = jdbcTemplate.query(query,new MyRowMapper());
        if (entryList.isEmpty()) {
            return List.of(emptyTableEntryMessage());
        }
        return entryList;
    }

    public List<Entry> getDailysEntries() {
        return getDailysEntries(LocalDate.now());
    }

    public List<Entry> getDailysEntries(LocalDate today) {
        if (todaysDailys.isEmpty()) {
            todaysDailys.add(emptyTableEntryMessage());
        }

       if (savedDate.isEqual(today)) {
           return todaysDailys;
       }

       todaysDailys = getEntries("dailys");
       savedDate = today;
       return todaysDailys;
    }

    public void clearDailyForTheDay(String id) {
        for (int i = 0; i < todaysDailys.size(); i++) {
            if (todaysDailys.get(i).getId().equals(id)) {
                todaysDailys.remove(i);
            }
        }
    }

    public List<Entry> getEntryWithID(String id, String table) {
        String query = "SELECT * FROM " + table + " WHERE ID = '" + id + "'";
        return jdbcTemplate.query(query,new MyRowMapper());
    }


    public Entry getRandomEntry(String table, LocalDate today) {
        //checks if its the same day
        if (savedDate.isEqual(today)) {
           return todaysQuoteEntry;
       }

        String query = "SELECT * FROM " + table;
        List<Entry> entries = jdbcTemplate.query(query,new MyRowMapper());
        if (entries.isEmpty()) {
           return emptyTableEntryMessage();
        }
        Random random = new Random();
        Entry newRandomEntry = entries.get(random.nextInt(entries.size()));

        //re-roll if needed
        while (newRandomEntry.equals(todaysQuoteEntry)) {
            newRandomEntry = entries.get(random.nextInt(entries.size()));
        }

        savedDate = today;
        todaysQuoteEntry = newRandomEntry;
        return todaysQuoteEntry;
    }

    public Entry getRandomEntry(String table) {
        return getRandomEntry(table,LocalDate.now());
    }

    private Entry emptyTableEntryMessage() {
        return new Entry.Builder(ID.createID()).withMessage("~").build();
    }
}






