package com.bookstore.controller;

import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/book_form")
    public String bookRegister(){
        return "book_form";
    }

    @GetMapping("/books")
    public String getAllBook(Model model){
        List<Book> list = bookService.getAllBooks();
        model.addAttribute("books", list);
        return "books";
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book book){
        bookService.save(book);
        return "redirect:/books";
    }

//    @GetMapping("/my_books")
//    public ModelAndView getMyBooks(){
//        return "myBooks";
//    }
}
