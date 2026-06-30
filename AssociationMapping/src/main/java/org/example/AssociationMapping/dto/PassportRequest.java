package org.example.AssociationMapping.dto;
import lombok.Data;

@Data
public class PassportRequest {
    private String passportNum;
    private String issueDt;
    private String expDt;
}