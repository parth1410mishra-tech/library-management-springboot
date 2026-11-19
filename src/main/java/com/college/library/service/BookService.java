package com.college.library.service;

import com.college.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * In-memory storage (no database needed). Data resets when the app restarts.
 */
@Service
public class BookService {

    private final Map<Integer, Book> books = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(0);

    public BookService() {
        // Sample data so GET endpoints show something immediately
        addBook(new Book(null, "Clean Code", "Robert C. Martin", "Programming", 5));
        addBook(new Book(null, "Effective Java", "Joshua Bloch", "Programming", 3));
    }

    public Book addBook(Book book) {
        int newId = idGenerator.incrementAndGet();
        book.setId(newId);
        books.put(newId, book);
        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>(books.values());
        list.sort((a, b) -> a.getId().compareTo(b.getId()));
        return list;
    }

    public Optional<Book> getBookById(Integer id) {
        return Optional.ofNullable(books.get(id));
    }

    public Optional<Book> updateBook(Integer id, Book updated) {
        Book existing = books.get(id);
        if (existing == null) {
            return Optional.empty();
        }
        existing.setTitle(updated.getTitle());
        existing.setAuthor(updated.getAuthor());
        existing.setCategory(updated.getCategory());
        existing.setAvailableCopies(updated.getAvailableCopies());
        return Optional.of(existing);
    }

    public boolean deleteBook(Integer id) {
        return books.remove(id) != null;
    }
}
