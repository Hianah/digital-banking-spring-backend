package org.sid.ebanckingbackend.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data 
@NoArgsConstructor @AllArgsConstructor
public class Costumer {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	@OneToMany(mappedBy = "costumer")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private List<BankAccount> bankAccounts;
	
	
	
	public Costumer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Costumer(Long id, String name, String email, List<BankAccount> bankAccounts) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.bankAccounts = bankAccounts;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<BankAccount> getBankAccounts() {
		return bankAccounts;
	}
	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
	
	
	
}
