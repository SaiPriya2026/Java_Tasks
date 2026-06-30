package org.example.AssociationMapping.dto;
import lombok.Data;

@Data
public class AddressRequest {
    private String city;
    private String state;
    private String type;
}