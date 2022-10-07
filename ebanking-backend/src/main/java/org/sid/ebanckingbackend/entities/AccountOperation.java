package org.sid.ebanckingbackend.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.sid.ebanckingbackend.enums.OPerationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AccountOperation {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Date dateOperation;
	private double amount;
	@Enumerated(EnumType.STRING)
	private OPerationType operationType;
	@ManyToOne
	private BankAccount bankAccount;
	 private String description;
	 
	public Date getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public OPerationType getOperationType() {
		return operationType;
	}
	public void setOperationType(OPerationType operationType) {
		this.operationType = operationType;
	}
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
