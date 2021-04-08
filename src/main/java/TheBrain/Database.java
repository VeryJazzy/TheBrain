package TheBrain;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Database {

    private LocalDate currentDate;
    private Entry todaysQuote;
    private List<Entry> todaysDailys;

    private final JdbcTemplate jdbcTemplate;

    public Database(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
        currentDate = LocalDate.of(1, 1, 1);
        todaysQuote = getTodaysQuote("quotes");
        todaysDailys = getEntries("dailys");
    }

    public Entry getTodaysQuote() {
        return todaysQuote;
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
        List<Entry> entryList = jdbcTemplate.query(query, new MyRowMapper());
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
        if (currentDate.isEqual(today)) {
            return todaysDailys;
        }

        todaysDailys = getEntries("dailys");
        currentDate = today;
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
        return jdbcTemplate.query(query, new MyRowMapper());
    }

    private Entry emptyTableEntryMessage() {
        return new Entry.Builder(ID.createID()).withMessage("~").build();
    }


    public Entry getTodaysQuote(String table) {
        return getTodaysQuote(table, LocalDate.now());
    }

    public Entry getTodaysQuote(String table, LocalDate today) {
        if (currentDate.isEqual(today)) {
            return todaysQuote;
        }

        currentDate = today;
        todaysQuote = getRandomEntry(table);
        return todaysQuote;
    }

    public Entry getRandomEntry(String table) {
        List<Entry> entries = jdbcTemplate.query("SELECT * FROM " + table, new MyRowMapper());
        Random random = new Random();
        Entry randomEntry = entries.get(random.nextInt(entries.size()));

        while (randomEntry.equals(todaysQuote)) {
            randomEntry = entries.get(random.nextInt(entries.size()));
        }
        return randomEntry;
    }



}






