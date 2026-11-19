package com.college.library.controller;

import com.college.library.model.Book;
import com.college.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Part (b): add a new book  ->  POST /api/books
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book saved = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved); // 201
    }

    // Part (c-i): get all books  ->  GET /api/books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks()); // 200
    }

    // Part (c-ii): get one book by id  ->  GET /api/books/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)                                   // 200
                .orElseGet(() -> ResponseEntity.notFound().build());       // 404
    }

    // Part (d): update a book  ->  PUT /api/books/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book book) {
        return bookService.updateBook(id, book)
                .map(ResponseEntity::ok)                                   // 200
                .orElseGet(() -> ResponseEntity.notFound().build());       // 404
    }

    // Part (e): delete a book  ->  DELETE /api/books/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        if (bookService.deleteBook(id)) {
            return ResponseEntity.ok("Book with id " + id + " deleted successfully"); // 200
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Book with id " + id + " not found");                           // 404
    }
}
