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
@DiscriminatorValue("CC")
@Data @AllArgsConstructor @NoArgsConstructor
public class CurrentAccount extends BankAccount {
	//cheque especial

	
	private double overDraft;
	//private double balance;
	@ManyToOne
	//private Costumer costumer;
	//private Date createdAt;
	@Enumerated(EnumType.STRING	)
	//private AccountStatus status;

public double getOverDraft() {
	return overDraft;
}

public void setOverDraft(double overDraft) {
	this.overDraft = overDraft;
}
/*
 * public double getBalance() { return balance; }
 * 
 * public void setBalance(double balance) { this.balance = balance; }
 * 
 * public Costumer getCostumer() { return costumer; }
 * 
 * public void setCostumer(Costumer costumer) { this.costumer = costumer; }
 * 
 * public Date getCreatedAt() { return createdAt; }
 * 
 * public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
 * 
 * public AccountStatus getStatus() { return status; }
 * 
 * public void setStatus(AccountStatus status) { this.status = status; }
 */

public void setCostumer(Optional<Costumer> costumer) {
	// TODO Auto-generated method stub
	
}


}
