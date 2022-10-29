package books.web;


import java.util.*;
import java.util.stream.Collectors;

import books.domain.Book;
import books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class BookServiceController {

    @Autowired
    BookService bookService;

	@GetMapping("/books/{isbn}")
    public ResponseEntity<?> getBook(@PathVariable String isbn) {
        Book book = bookService.findByIsbn(isbn);
        if (book == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Book with isbn= " + isbn + " is not available"),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Book> (book, HttpStatus.OK);
    }

	@DeleteMapping("/books/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        Book book = bookService.findByIsbn(isbn);
        if (book == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Book with isbn = " + isbn + " is not available"),HttpStatus.NOT_FOUND);
        }
        bookService.delete(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	@PostMapping("/books")
    public ResponseEntity<?> addContact(@RequestBody Book book) {
        bookService.add(book);
        return new ResponseEntity<Book> (book, HttpStatus.OK);
    }

	@PutMapping("/books/{isbn}")
    public ResponseEntity<?> updateBook(@PathVariable String isbn, @RequestBody Book book) {
        bookService.update(book);
		return new ResponseEntity<Book> (book, HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<?> allBooks() {
        Books allbooks = new Books();
        allbooks.setBooks(bookService.findAll());
        return new ResponseEntity<Books> (allbooks, HttpStatus.OK);
    }
}


