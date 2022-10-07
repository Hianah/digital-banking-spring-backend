package org.sid.ebanckingbackend.dtos;

import java.util.Date;
import java.util.Optional;

import org.sid.ebanckingbackend.entities.Costumer;
import org.sid.ebanckingbackend.enums.AccountStatus;

import lombok.Data;

@Data
public class CurrentAccountDTO extends BankAccountDTO{
	

		private String id;
		private double overDraft;
		private double balance;
		private CustomerDTO customerDTO;
		private Date createdAt;
		private AccountStatus status;

	public double getOverDraft() {
		return overDraft;
	}

	public void setOverDraft(double overDraft) {
		this.overDraft = overDraft;
	}
	
	  public double getBalance() { return balance; }
	  
	  public void setBalance(double balance) { this.balance = balance; }
	  
	  
	  public Date getCreatedAt() { return createdAt; }
	  
	  public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
	  
	  public AccountStatus getStatus() { return status; }
	  
	  public void setStatus(AccountStatus status) { this.status = status; }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}

	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}
	 

	

}
