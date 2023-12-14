package com.example.untitled13.controller;

import com.example.untitled13.entity.Cart;
import com.example.untitled13.entity.User;
import com.example.untitled13.service.CartService;
import com.example.untitled13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PostMapping("/add/{productId}/{quantity}")
    public ResponseEntity<String> addProductToCart(@PathVariable("productId") Long productId, @PathVariable("quantity") Long quantity) {
        try {
            cartService.addProductToCart(getCurrentUserId(), productId, quantity);
            return ResponseEntity.ok("Product added to cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public List<Cart> getCart(){
        return cartService.getCart(getCurrentUserId());
    }

    @DeleteMapping("/removeProduct/{productId}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable("productId") Long productId) {
        try {
            cartService.removeProductFromCart(getCurrentUserId(), productId);
            return ResponseEntity.ok("Product removed from cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeCart() {
        try {
            cartService.removeCart(getCurrentUserId());
            return ResponseEntity.ok("Cart removed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/buy")
    public ResponseEntity<?> buyCart() {
        try {
            cartService.buyCart(getCurrentUserId());
            return ResponseEntity.ok("Purchase successful.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.loadUserByUsername((String) authentication.getPrincipal());
        return user.getId();
    }
}
