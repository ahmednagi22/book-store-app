package com.bookstore.controllers;

import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book createdBook = bookService.addBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book){
        book.setId(id);
       Book updatedBook = bookService.updateBook(book);
       return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
}
