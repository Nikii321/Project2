package com.example.untitled13.controller;

import com.example.untitled13.entity.User;
import com.example.untitled13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser() {
        User currentUser = userService.findUserById(getCurrentUserId());
        return ResponseEntity.ok(currentUser);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeUser() {
        boolean isDeleted = userService.deleteUser(getCurrentUserId());
        if (isDeleted) {
            return ResponseEntity.ok("User deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Error deleting user.");
        }
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.loadUserByUsername((String) authentication.getPrincipal());
        return user.getId();
    }
}