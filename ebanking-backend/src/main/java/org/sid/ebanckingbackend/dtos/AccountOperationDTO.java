package org.sid.ebanckingbackend.dtos;

import java.util.Date;

import org.sid.ebanckingbackend.enums.OPerationType;

import lombok.Data;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OPerationType type;
    private String description;
	
}

