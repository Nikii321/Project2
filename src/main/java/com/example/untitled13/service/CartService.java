package com.example.untitled13.service;

import com.example.untitled13.entity.Cart;
import com.example.untitled13.entity.Product;
import com.example.untitled13.entity.User;
import com.example.untitled13.repository.CartRepository;
import com.example.untitled13.repository.ProductRepository;
import com.example.untitled13.repository.UserRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Cart> getCart(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with ID: " + userId));
        return cartRepository.getByUser(user);
    }

    @Transactional
    public void addProductToCart(Long userId, Long productId, long quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with ID: " + userId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
        Cart cart = cartRepository.findByUserAndAndProduct(user, product).orElse(
                new Cart(null, null, user, product, 0L)
        );
        long amount = cart.getQuality() + quantity;
        if (product.getAmount() < amount) {
            throw new RuntimeException("Not enough stock for product: " + product);
        }
        cart.setQuality(amount);
        cartRepository.save(cart);
    }

    @Transactional
    public void removeProductFromCart(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with ID: " + userId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
        cartRepository.deleteByUserAndProduct(user, product);
    }

    @Transactional
    public void removeCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with ID: " + userId));
        cartRepository.deleteByUser(user);
    }

    @Transactional
    public void buyCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with ID: " + userId));
        List<Cart> cart = cartRepository.getByUser(user);
        cart.forEach(carts -> {
            if (carts.getProduct().getAmount() < carts.getQuality()) {
                throw new RuntimeException("Not enough stock for product: " + carts.getProduct().getName());
            }
            carts.getProduct().setAmount(carts.getProduct().getAmount() - carts.getQuality());
            productRepository.save(carts.getProduct());
        });
        removeCart(userId);
    }
}
