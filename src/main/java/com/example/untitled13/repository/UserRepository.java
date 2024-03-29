package com.example.untitled13.repository;

import com.example.untitled13.entity.Role;
import com.example.untitled13.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findAllByRoles(Set<Role> roleSet) ;
    Boolean existsByUsername(String username);
}
