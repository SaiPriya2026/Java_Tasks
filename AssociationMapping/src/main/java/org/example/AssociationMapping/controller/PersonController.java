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
@RequestMapping("/api/persons")
@RequiredArgsConstructor
@Tag(name = "Person API", description = "One-to-One: Person to Passport")
public class PersonController {

    private final PersonRepository personRepo;
    private final PassportRepository passportRepo;

    @Operation(summary = "Create a new Person")
    @PostMapping
    public Person createPerson(@RequestBody PersonRequest req) {
        Person p = new Person();
        p.setName(req.getName());
        p.setGender(req.getGender());
        p.setPhno(req.getPhno());
        return personRepo.save(p);
    }

    @Operation(summary = "Get all Persons")
    @GetMapping
    public List<Person> getAllPersons() {
        return personRepo.findAll();
    }

    @Operation(summary = "Get Person by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable Long id) {
        return personRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Assign Passport to a Person")
    @PostMapping("/{personId}/passport")
    public ResponseEntity<Passport> addPassport(
            @PathVariable Long personId,
            @RequestBody PassportRequest req) {

        return personRepo.findById(personId).map(person -> {
            Passport pass = new Passport();
            pass.setPassportNum(req.getPassportNum());
            pass.setIssueDt(req.getIssueDt());
            pass.setExpDt(req.getExpDt());
            pass.setPerson(person);
            return ResponseEntity.ok(passportRepo.save(pass));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get Passport of a Person")
    @GetMapping("/{personId}/passport")
    public ResponseEntity<?> getPassport(@PathVariable Long personId) {
        return personRepo.findById(personId).map(person -> {
            Passport p = person.getPassport();
            if (p == null) return ResponseEntity.ok("No passport assigned");
            return ResponseEntity.ok(p);
        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete Person by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        if (!personRepo.existsById(id)) return ResponseEntity.notFound().build();
        personRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}