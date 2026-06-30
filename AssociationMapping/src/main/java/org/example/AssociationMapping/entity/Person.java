package org.example.AssociationMapping.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Person_Tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    private String name;
    private String gender;
    private String phno;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Passport passport;
}