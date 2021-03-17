package TheBrain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class MyRowMapper implements RowMapper<Entry> {

    @Override
    public Entry mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Entry.Builder(rs.getString("id"))
                .withDate(rs.getString("date"))
                .withMessage(rs.getString("message"))
                .build();
    }


}
