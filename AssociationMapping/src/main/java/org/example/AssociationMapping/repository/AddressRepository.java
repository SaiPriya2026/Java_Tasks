package org.example.AssociationMapping.repository;
import org.example.AssociationMapping.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AddressRepository extends JpaRepository<Address, Long> {}