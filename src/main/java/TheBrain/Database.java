package TheBrain;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.util.List;

public class Database {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    private JdbcTemplate jdbcTemplate;

    public Database(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    public void add(Entry entry) {
//        jdbcTemplate.update("INSERT INTO ENTRIES VALUES (?, ?, ?, ?, ?)", entry.getUser(), entry.getId(), entry.getTitle(), entry.getText(), entry.getDate());
    }


    public void delete(String id) {
//        jdbcTemplate.update("DELETE FROM ENTRIES WHERE id=?", id);
    }


    public List<Entry> getEntries() {
        String query = "SELECT * FROM dailytasks";
        return jdbcTemplate.query(query,new MyRowMapper());
    }


}






