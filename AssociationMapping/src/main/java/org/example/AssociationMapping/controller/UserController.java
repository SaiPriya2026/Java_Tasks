package org.example.AssociationMapping.controller;

import org.example.AssociationMapping.dto.UserRequest;
import org.example.AssociationMapping.entity.Role;
import org.example.AssociationMapping.entity.User;
import org.example.AssociationMapping.repository.RoleRepository;
import org.example.AssociationMapping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setPhno(userRequest.getPhno());

        if (userRequest.getRoleIds() != null && !userRequest.getRoleIds().isEmpty()) {
            List<Role> roles = userRequest.getRoleIds()
                    .stream()
                    .map(roleRepository::findByRoleId)
                    .collect(Collectors.toList());
            user.setRoles(roles);
        }

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.setName(userRequest.getName());
        user.setPhno(userRequest.getPhno());

        if (userRequest.getRoleIds() != null && !userRequest.getRoleIds().isEmpty()) {
            List<Role> roles = userRequest.getRoleIds()
                    .stream()
                    .map(roleRepository::findByRoleId)
                    .collect(Collectors.toList());
            user.setRoles(roles);
        }

        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            userRepository.deleteById(userId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<User> addRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        User user = userRepository.findByUserId(userId);
        Role role = roleRepository.findByRoleId(roleId);

        if (user == null || role == null) {
            return ResponseEntity.notFound().build();
        }

        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
            userRepository.save(user);
        }

        return ResponseEntity.ok(user);
    }
}

