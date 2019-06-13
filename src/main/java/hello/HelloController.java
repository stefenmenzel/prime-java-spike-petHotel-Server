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
    public String addPet(@RequestBody Pet pet) throws IOException{
        // final JsonNode jsonNode = mapper.readTree(newPet);
        // Pet pet = new Pet();

        // String name = jsonNode.findValue("name").toString();
        // String type = jsonNode.findValue("type").toString();
        // String color = jsonNode.findValue("color").toString();
        // String date = jsonNode.findValue("date").toString();
        // int owner_id = Integer.parseInt(jsonNode.findValue("owner_id").toString());

        // pet.setName(name);
        // pet.setType(type);
        // pet.setColor(color);
        // pet.setDate(date);
        // pet.setOwnerId(owner_id);
        
        // String name = pet.getName();
        // String type = pet.getType();
        // String color = pet.getColor();
        // String date = pet.getDate();
        // int owner_id = pet.getOwnerId();
        // System.out.println("req.body: " + test);

        // String query = "INSERT INTO pets (name, type, color, date, owner_id) VALUES (?, ?, ?, ?, ?);";
        // jdbcTemplate.update(query, name, type, color, date, owner_id);
        return "back from the post: " + pet.getName() + " " + pet.getType() + " " + pet.getColor()+ " " + pet.getDate() + " " + pet.getChecked_in() + " " + pet.getOwner_id();        
    }
    
    @RequestMapping("/owners")
    public List<Owner> getAllOwners(){
        String query = "SELECT * FROM owners";
        List<Owner> owners = jdbcTemplate.query(
            query, new OwnerRowMapper());
            return owners;
    }
}
