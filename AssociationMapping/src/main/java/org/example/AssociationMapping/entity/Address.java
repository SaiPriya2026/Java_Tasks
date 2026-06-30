package org.example.AssociationMapping.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ADDR_TBL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addrId;

    private String city;
    private String state;
    private String type;

    @ManyToOne
    @JoinColumn(name = "emp_id")   // FK column
    @JsonIgnore
    private Employee employee;
}