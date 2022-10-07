package org.sid.ebanckingbackend.dtos;

import java.util.Date;
import java.util.Optional;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import org.sid.ebanckingbackend.entities.BankAccount;
import org.sid.ebanckingbackend.entities.Costumer;
import org.sid.ebanckingbackend.enums.AccountStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
public  class SavingAccountDTO extends BankAccountDTO {

	private String id;
	private double interestRate;
	private double balance;
	private CustomerDTO customerDTO;
	private Date createdAt;
	private AccountStatus status;
	
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	
	  public double getBalance() { return balance; } public void setBalance(double
	  balance) { this.balance = balance; } 
	  
	  public Date getCreatedAt() { return createdAt; }
	  public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
	  public AccountStatus getStatus() { return status; } 
	  public void setStatus(AccountStatus status) { this.status = status; }
	  
	  public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}
	  public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}
	 
	
	
	
		

	}
