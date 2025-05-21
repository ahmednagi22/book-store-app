package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.exception.BookNotFoundException;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public Book updateBook(Book book){
        if(!bookRepository.existsById(book.getId())){
            throw new BookNotFoundException("Cannot update. Book not found.");
        }
        return bookRepository.save(book);
    }

    public void deleteBook(Long id){
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException("cannot delete. Book not found");
        }
        bookRepository.deleteById(id);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException("Book not found"));
    }


}
