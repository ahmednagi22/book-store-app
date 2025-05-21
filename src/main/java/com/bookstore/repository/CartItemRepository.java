package com.bookstore.repository;

import com.bookstore.entity.Book;
import com.bookstore.entity.Cart;
import com.bookstore.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartAndBook(Cart cart, Book book);
    //List<CartItem> findByUser(User user);
//    CartItem findByUserAndBook(User user, Book book);
//    CartItem findByIdAndUser(Long id, User user);
}
