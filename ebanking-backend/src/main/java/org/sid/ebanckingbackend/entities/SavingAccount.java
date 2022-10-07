package org.sid.ebanckingbackend.entities;

import java.util.Date;
import java.util.Optional;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.sid.ebanckingbackend.enums.AccountStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("SA")
@Data @NoArgsConstructor @AllArgsConstructor

public class SavingAccount extends BankAccount{


	private double interestRate;
	//private double balance;
	@ManyToOne
	private Costumer costumer;
	//private Date createdAt;
	@Enumerated(EnumType.STRING)
	//private AccountStatus status;
	
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public void setCostumer(Optional<Costumer> costumer) {
		// TODO Auto-generated method stub
		
	}
	public Costumer getCostumer() {
		return costumer;
	}
	public void setCostumer(Costumer costumer) {
		this.costumer = costumer;
	}
	
	/*
	 * public double getBalance() { return balance; } public void setBalance(double
	 * balance) { this.balance = balance; } public Costumer getCostumer() { return
	 * costumer; } public void setCostumer(Costumer costumer) { this.costumer =
	 * costumer; } public Date getCreatedAt() { return createdAt; } public void
	 * setCreatedAt(Date createdAt) { this.createdAt = createdAt; } public
	 * AccountStatus getStatus() { return status; } public void
	 * setStatus(AccountStatus status) { this.status = status; }
	 */
	
}
