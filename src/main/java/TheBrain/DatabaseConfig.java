package TheBrain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public Database getDatabase() {
        return new Database(dataSource);
    }


}