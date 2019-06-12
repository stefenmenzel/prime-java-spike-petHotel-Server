package hello;

import hello.Owner;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class OwnerRowMapper implements RowMapper<Owner> {
    @Override
    public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
        Owner owner = new Owner();

        owner.setId(rs.getInt("id"));
        owner.setName(rs.getString("name"));
        
        return owner;
    }
}