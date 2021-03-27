package TheBrain;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Random;

public class Database {

    private final JdbcTemplate jdbcTemplate;

    public Database(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    public int add(Entry entry, String table) {
        return jdbcTemplate.update("INSERT INTO " + table + " VALUES (?, ?, ?)", entry.getId(), entry.getDate(), entry.getText());
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

    public List<Entry> getEntryWithID(String id, String table) {
        String query = "SELECT * FROM " + table + " WHERE ID = '" + id + "'";
        return jdbcTemplate.query(query,new MyRowMapper());
    }

    public Entry getRandomEntry(String table) {


        String query = "SELECT * FROM " + table;
        List<Entry> entries = jdbcTemplate.query(query,new MyRowMapper());

        if (entries.isEmpty()) {
           return emptyTableEntryMessage();
        }

        Random random = new Random();
        return entries.get(random.nextInt(entries.size()));
    }

    private Entry emptyTableEntryMessage() {
        return new Entry.Builder(ID.createID()).withMessage("table empty").build();
    }


}






