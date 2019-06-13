package hello;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.jdbc.core.JdbcTemplate;
import hello.Book;

@RestController
public class HelloController {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    // @RequestMapping("/book")
    // public List<Book> getAllBooks() {
    //     String query = "SELECT * FROM books";
    //     List<Book> books = jdbcTemplate.query(
    //         query, new BookRowMapper());
    //       return books;
    // }
        
    @RequestMapping(value = "/pets", method = RequestMethod.GET)
    public List<Pet> getAllPets(){
        String query = "SELECT * FROM pets";
        List<Pet> pets = jdbcTemplate.query(
            query, new PetRowMapper());
            return pets; 
    }

    @RequestMapping(value = "/addPet", method = RequestMethod.POST)
    @ResponseBody
    public String addPet(@RequestBody Pet pet){
        String query = "INSERT INTO pets (name, type, color, date, owner_id) VALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, pet.getName(), pet.getType(), pet.getColor(), pet.getDate(), pet.getOwner_id());
        return "back from the post: " + pet.getName() + " " + pet.getType() + " " + pet.getColor()+ " " + pet.getDate() + " " + pet.getChecked_in() + " " + pet.getOwner_id();        
    }
    
    @RequestMapping(value = "/owners", method = RequestMethod.GET)
    public List<Owner> getAllOwners(){
        String query = "SELECT * FROM owners";
        List<Owner> owners = jdbcTemplate.query(
            query, new OwnerRowMapper());
            return owners;
    }

    @RequestMapping(value = "/addOwner", method = RequestMethod.POST)
    @ResponseBody
    public String addOwner(@RequestBody Owner newOwner) throws IOException{
        String query = "INSERT INTO owners (name) VALUES (?);";
        jdbcTemplate.update(query, newOwner.getName());
        return "New owner added " + newOwner.getName();
    }

    @RequestMapping(value = "/deleteOwner", method = RequestMethod.DELETE)
    @ResponseBody 
    public String deleteOwner(@RequestBody Owner deleteOwner) throws IOException{
        String query = "DELETE FROM owners WHERE id = ?;";
        jdbcTemplate.update(query, deleteOwner.getId());
        return "Delete owner " + deleteOwner.getId();
    }
}
