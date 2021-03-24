package TheBrain;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

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
        return jdbcTemplate.query(query,new MyRowMapper());
    }

    public List<Entry> getEntryWithID(String id, String table) {
        String query = "SELECT * FROM " + table + " WHERE ID = '" + id + "'";
        return jdbcTemplate.query(query,new MyRowMapper());
    }


}






