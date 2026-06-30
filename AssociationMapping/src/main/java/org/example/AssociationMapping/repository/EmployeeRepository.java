package org.example.AssociationMapping.repository;
import org.example.AssociationMapping.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {}