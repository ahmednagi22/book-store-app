package com.bookstore.service;

import com.bookstore.entity.CartItem;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.CartItemRepository;
import org.springframework.stereotype.Service;
import com.bookstore.entity.User;
import com.bookstore.entity.Book;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService{


    private final CartItemRepository cartItemRepository;

    private final BookRepository bookRepository;

    public CartService(CartItemRepository cartItemRepository, BookRepository bookRepository) {
        this.cartItemRepository = cartItemRepository;
        this.bookRepository = bookRepository;
    }


//    public void addItemToCart(Long bookId, int quantity, User user) {
//        Book book = bookRepository.findById(bookId)
//                .orElseThrow(() -> new RuntimeException("Book not found"));
//
//        CartItem existingItem = cartItemRepository.findByUserAndBook(user, book);
//        if (existingItem != null) {
//            existingItem.setQuantity(existingItem.getQuantity() + quantity);
//            cartItemRepository.save(existingItem);
//        } else {
//            CartItem newItem = new CartItem();
//            newItem.setBook(book);
//            newItem.setQuantity(quantity);
//            cartItemRepository.save(newItem);
//        }
//    }


    public List<CartItem> getCartItems(User user) {
        return new ArrayList<>();
        //return cartItemRepository.findByUser(user);
    }


//    public void updateQuantity(Long cartItemId, int quantity, User user) {
//        CartItem item = cartItemRepository.findByIdAndUser(cartItemId, user);
//        item.setQuantity(quantity);
//        cartItemRepository.save(item);
//    }
//
//
//    public void removeItemFromCart(Long cartItemId, User user) {
//        CartItem item = cartItemRepository.findByIdAndUser(cartItemId, user);
//        cartItemRepository.delete(item);
//    }


//    public double getTotalPrice(User user) {
//        return cartItemRepository.findByUser(user)
//                .stream()
//                .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
//                .sum();
//    }

//    public int getTotalItems(User user) {
//        return cartItemRepository.findByUser(user)
//                .stream()
//                .mapToInt(CartItem::getQuantity)
//                .sum();
//    }
}
