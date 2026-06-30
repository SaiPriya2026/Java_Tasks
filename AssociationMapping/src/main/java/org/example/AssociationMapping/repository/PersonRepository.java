package org.example.AssociationMapping.repository;
import org.example.AssociationMapping.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PersonRepository extends JpaRepository<Person, Long> {}