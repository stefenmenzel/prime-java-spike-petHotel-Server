package hello;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.jdbc.core.JdbcTemplate;
import hello.Book;

@RestController
public class HelloController {

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
        
    @RequestMapping("/pets")
    public List<Pet> getAllPets(){
        String query = "SELECT * FROM pets";
        List<Pet> pets = jdbcTemplate.query(
            query, new PetRowMapper());
            return pets; 
    }
    
    @RequestMapping("/owners")
    public List<Owner> getAllOwners(){
        String query = "SELECT * FROM owners";
        List<Owner> owners = jdbcTemplate.query(
            query, new OwnerRowMapper());
            return owners;
    }
}
