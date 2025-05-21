package com.bookstore.service;

import com.bookstore.dto.AddToCartRequest;
import com.bookstore.entity.Cart;
import com.bookstore.entity.CartItem;
import com.bookstore.exception.BookNotFoundException;
import com.bookstore.exception.UserNotFoundException;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.CartItemRepository;
import com.bookstore.repository.CartRepository;
import com.bookstore.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.bookstore.entity.User;
import com.bookstore.entity.Book;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {


    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final CartRepository cartRepository;

    public CartService(CartItemRepository cartItemRepository,
                       BookRepository bookRepository,
                       UserRepository userRepository,
                       CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }


    public void addToCart(AddToCartRequest request, String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User Not Found with this email"));

        Cart cart = cartRepository.findByUser(user).
                orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new BookNotFoundException("Book not found with this id try another"));


        // check if cartItem exist or not for specific cart
        CartItem existingItem = cartItemRepository.findByCartAndBook(cart, book);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + request.quantity());
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setBook(book);
            newItem.setQuantity(request.quantity());
            cartItemRepository.save(newItem);
        }

    }


//    public List<CartItem> getCartItems(User user) {
//        return new ArrayList<>();
//        //return cartItemRepository.findByUser(user);
//    }


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
