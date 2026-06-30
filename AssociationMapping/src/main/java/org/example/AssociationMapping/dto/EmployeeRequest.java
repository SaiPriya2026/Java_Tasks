package org.example.AssociationMapping.dto;
import lombok.Data;

@Data
public class EmployeeRequest {
    private String name;
    private String gender;
    private Double salary;
}