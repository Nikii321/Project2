package com.example.untitled13.repository;

import com.example.untitled13.entity.Cart;
import com.example.untitled13.entity.Product;
import com.example.untitled13.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserAndAndProduct(User user, Product product);
    void deleteByUserAndProduct(User user, Product product);
    void deleteByUser(User user);
    List<Cart> getByUser(User user);
}
