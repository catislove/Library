package mipt;

import mipt.model.Book;
import mipt.model.BookDTO;
import mipt.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.*;

@RestController
public class BookController {

    @Autowired
    private BookRepository repository;

//    @PostMapping("/book/")
//    public ResponseEntity<Book> createBook(@RequestBody Book book) {
//        repository.save(book);
//        return new ResponseEntity<>(book, HttpStatus.OK);
//   }

    @PostMapping("/book/")
    public ResponseEntity<HashMap<String, Integer>> createBook(@RequestBody Book book) {
        repository.save(book);
        HashMap<String, Integer> result = new HashMap<>();
        result.put("id", book.getId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping("/book/{id}")
//    public ResponseEntity<HashMap<String, Book>> getBook(@PathVariable Integer id) {
//        HashMap<String, Book> result = new HashMap<>();
//        Optional<Book> bookOptional = repository.findById(id);//
//        result.put("book", bookOptional.get());
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id) {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            BookDTO result = new BookDTO(book.getName(), book.getYear());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/book/")
    public ResponseEntity<HashMap<String, List<Book>>> getList() {
        HashMap<String, List<Book>> result = new HashMap<>();
        result.put("books", repository.findAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @DeleteMapping("/book/{id}")
    public ResponseEntity<Void> removeBook(@PathVariable Integer id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable Integer id, @RequestBody Book book) {
        Optional<Book> bookOptional = repository.findById(id);
        if (bookOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        book.setId(id);
        repository.save(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
