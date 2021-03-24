package TheBrain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Autowired // this doesn't need to be set up because spring checks the app.properties for the datasource
    private DataSource dataSource;

    @Bean
    public Database getDatabase() {
        return new Database(dataSource);
    }


}