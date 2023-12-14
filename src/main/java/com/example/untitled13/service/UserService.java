package com.example.untitled13.service;

import com.example.untitled13.entity.Role;
import com.example.untitled13.entity.User;
import com.example.untitled13.repository.RoleRepository;
import com.example.untitled13.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ProductService productService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElseThrow(() -> new RuntimeException("not found user with ID: " + userId));
    }

    public User findByUsernameAndPassword(String username, String password) {
        User user = loadUserByUsername(username);
        if(bCryptPasswordEncoder.matches(
                password, user.getPassword()
        )) throw new UsernameNotFoundException("incorrect password");
        return user;
    }

    public List<User> allUsersForAdmin() {
        Role role = new Role(1L, "ROLE_USER");
        Set<Role> set = new HashSet<>();
        set.add(role);
        return userRepository.findAllByRoles(set);
    }

    @Transactional
    public User saveUser(User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new RuntimeException("user with username "+ user.getUsername()+" exist");
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

}
