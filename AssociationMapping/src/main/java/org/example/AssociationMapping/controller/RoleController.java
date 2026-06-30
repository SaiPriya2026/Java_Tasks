package org.example.AssociationMapping.controller;

import org.example.AssociationMapping.dto.RoleRequest;
import org.example.AssociationMapping.entity.Role;
import org.example.AssociationMapping.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleRequest roleRequest) {
        Role role = new Role();
        role.setName(roleRequest.getName());
        role.setStatus(roleRequest.getStatus());

        Role savedRole = roleRepository.save(role);
        return ResponseEntity.ok(savedRole);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long roleId) {
        Role role = roleRepository.findByRoleId(roleId);
        if (role != null) {
            return ResponseEntity.ok(role);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable Long roleId, @RequestBody RoleRequest roleRequest) {
        Role role = roleRepository.findByRoleId(roleId);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }

        role.setName(roleRequest.getName());
        role.setStatus(roleRequest.getStatus());

        Role updatedRole = roleRepository.save(role);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long roleId) {
        Role role = roleRepository.findByRoleId(roleId);
        if (role != null) {
            roleRepository.deleteById(roleId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

