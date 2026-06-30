package org.example.AssociationMapping.controller;

import org.example.AssociationMapping.dto.*;
import org.example.AssociationMapping.entity.*;
import org.example.AssociationMapping.repository.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Employee API", description = "One-to-Many: Employee to Address")
public class EmployeeController {

    private final EmployeeRepository empRepo;
    private final AddressRepository addrRepo;

    @Operation(summary = "Create a new Employee")
    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeRequest req) {
        Employee e = new Employee();
        e.setName(req.getName());
        e.setGender(req.getGender());
        e.setSalary(req.getSalary());
        return empRepo.save(e);
    }

    @Operation(summary = "Get all Employees")
    @GetMapping
    public List<Employee> getAllEmployees() {
        return empRepo.findAll();
    }

    @Operation(summary = "Get Employee by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return empRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add Address to an Employee")
    @PostMapping("/{empId}/addresses")
    public ResponseEntity<Address> addAddress(
            @PathVariable Long empId,
            @RequestBody AddressRequest req) {

        return empRepo.findById(empId).map(emp -> {
            Address addr = new Address();
            addr.setCity(req.getCity());
            addr.setState(req.getState());
            addr.setType(req.getType());
            addr.setEmployee(emp);
            return ResponseEntity.ok(addrRepo.save(addr));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all Addresses of an Employee")
    @GetMapping("/{empId}/addresses")
    public ResponseEntity<List<Address>> getAddresses(@PathVariable Long empId) {
        return empRepo.findById(empId)
                .map(emp -> ResponseEntity.ok(emp.getAddresses()))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete Employee by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (!empRepo.existsById(id)) return ResponseEntity.notFound().build();
        empRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}