//package com.bookstore.controllers;
//
//import com.bookstore.entity.Book;
//import com.bookstore.service.BookService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class BookController {
//
//    private final BookService bookService;
//
//    public BookController(BookService bookService){
//        this.bookService = bookService;
//    }
//
//    @GetMapping("/book_form")
//    public String bookRegister(){
//        return "book_form";
//    }
//
//    @GetMapping("/books")
//    public String getAllBook(Model model){
//        List<Book> list = bookService.getAllBooks();
//        model.addAttribute("books", list);
//        return "books";
//    }
//
//    @GetMapping("/books/{id}")
//    public String getBookDetails(@PathVariable Long id, Model model){
//        Book book = bookService.getBookById(id);
//        model.addAttribute("book", book);
//        return "book-details";
//    }
//
//    @PostMapping("/save")
//    public String addBook(@ModelAttribute Book book){
//        bookService.save(book);
//        return "redirect:/books";
//    }
//
////    @GetMapping("/my_books")
////    public ModelAndView getMyBooks(){
////        return "myBooks";
////    }
//}
