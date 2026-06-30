package org.example.AssociationMapping.repository;
import org.example.AssociationMapping.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PassportRepository extends JpaRepository<Passport, Long> {}