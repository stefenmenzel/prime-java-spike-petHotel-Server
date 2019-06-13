package hello;

import hello.Pet;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

//I'm a java newb but it seems like to me, this class is
//mapping through the database query, and for each row
//it's making a new instance of the Pet class and setting
//all the values accordingly.
public class PetRowMapper implements RowMapper<Pet>{
    @Override
    public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
        //this declares a new instance of the class Pet and gives it the name pet
        Pet pet = new Pet();

        //this is going through and setting all the initial values of our new pet.
        pet.setId(rs.getInt("id"));
        pet.setName(rs.getString("name"));
        pet.setType(rs.getString("type"));
        pet.setColor(rs.getString("color"));
        pet.setDate(rs.getString("date"));
        pet.setChecked_in(rs.getBoolean("checked_in"));
        pet.setOwner_id(rs.getInt("owner_id"));

        //send out a newly created pet instance...object?
        return pet;
    }
}