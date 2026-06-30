package org.example.AssociationMapping.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Passport_Tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passportId;

    private String passportNum;
    private String issueDt;
    private String expDt;

    @OneToOne
    @JoinColumn(name = "person_id")   // FK column
    @JsonIgnore
    private Person person;
}