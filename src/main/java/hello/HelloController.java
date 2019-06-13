package hello;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindingResult;

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
    // String query = "SELECT * FROM books";
    // List<Book> books = jdbcTemplate.query(
    // query, new BookRowMapper());
    // return books;
    // }

    @RequestMapping(value = "/pets", method = RequestMethod.GET)
    public List<Pet> getAllPets() {
        String query = "SELECT * FROM pets";
        List<Pet> pets = jdbcTemplate.query(query, new PetRowMapper());
        return pets;
    }

    @RequestMapping(value = "/addPet", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addPet(@RequestBody Pet pet, BindingResult bindingResult) {
        String query = "INSERT INTO pets (name, type, color, date, owner_id) VALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, pet.getName(), pet.getType(), pet.getColor(), pet.getDate(), pet.getOwner_id());

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("error in add pet POST route: 500", HttpStatus.BAD_REQUEST);
        } else {
            addPet(pet);
            return new ResponseEntity<>("response from add pet POST route: 201", HttpStatus.CREATED);
        }
    }

    public void addPet(Pet pet) {
        String query = "INSERT INTO pets (name, type, color, date, owner_id) VALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, pet.getName(), pet.getType(), pet.getColor(), pet.getDate(), pet.getOwner_id());
    }

    @RequestMapping(value = "/owners", method = RequestMethod.GET)
    public List<Owner> getAllOwners() {
        String query = "SELECT * FROM owners";
        List<Owner> owners = jdbcTemplate.query(query, new OwnerRowMapper());
        return owners;
    }

    @RequestMapping(value = "/addOwner", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addOwner(@RequestBody Owner owner, BindingResult bindingResult) {
        String query = "INSERT INTO owners (name) VALUES (?);";
        jdbcTemplate.update(query, owner.getName());

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("error in add owner POST route: 500", HttpStatus.BAD_REQUEST);
        } else {
            addOwner(owner);
            return new ResponseEntity<>("response from owner POST route: 201", HttpStatus.CREATED);
        }
    }

    public void addOwner(Owner owner) {
        String query = "INSERT INTO owners (name) VALUES (?);";
        jdbcTemplate.update(query, owner.getName());
    }

    @RequestMapping(value = "/owners/delete/{id}", method = RequestMethod.DELETE)
    public String deleteOwner(@PathVariable("id") String ownerId) throws IOException {
        String query = "DELETE FROM owners WHERE id = ?;";
        jdbcTemplate.update(query, Integer.parseInt(ownerId));
        return "Delete owner " + ownerId;
    }

    @RequestMapping(value = "/checkPet/{id}", method = RequestMethod.PUT)
    // @ResponseBody
    public String checkPet(@PathVariable("id") String petId) throws IOException {
        String query = "UPDATE pets SET checked_in = NOT checked_in WHERE id=?;";
        jdbcTemplate.update(query, Integer.parseInt(petId));
        return "Checked pet" + petId;
        // if (bindingResult.hasErrors())

        // {
        //     return new ResponseEntity<>("error in check pet PUT route: 500", HttpStatus.BAD_REQUEST);
        // } else {
        //     addPet(pet);
        //     return new ResponseEntity<>("response from check pet PUT route: 202", HttpStatus.ACCEPTED);
        // }
    }

    @RequestMapping(value = "/pets/delete/{id}", method = RequestMethod.DELETE)
    public String deletePet(@PathVariable("id") String petId) throws IOException {
        String query = "DELETE FROM pets WHERE id=?;";
        jdbcTemplate.update(query, Integer.parseInt(petId));
        return "Delete pet" + petId;
    }

}
