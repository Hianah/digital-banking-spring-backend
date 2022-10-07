package org.sid.ebanckingbackend.service;

import java.util.List;

import org.sid.ebanckingbackend.dtos.BankAccountDTO;
import org.sid.ebanckingbackend.dtos.CurrentAccountDTO;
import org.sid.ebanckingbackend.dtos.CustomerDTO;
import org.sid.ebanckingbackend.dtos.SavingAccountDTO;
import org.sid.ebanckingbackend.entities.BankAccount;
import org.sid.ebanckingbackend.entities.Costumer;
import org.sid.ebanckingbackend.entities.CurrentAccount;
import org.sid.ebanckingbackend.entities.SavingAccount;
import org.sid.ebanckingbackend.exception.BalanceNotSufficent;
import org.sid.ebanckingbackend.exception.BankAccountNotFoundException;

public interface BanckAccountService {


	CurrentAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft,  long customerID);
	
	SavingAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, long customerID);
	CustomerDTO saveCostumer(CustomerDTO customerDto);
	//List<Costumer> listCostumer();
	List<CustomerDTO> listCostumer();
	//consulter une compte
    //BankAccount getBanckAccountDTO(String accountID) throws BankAccountNotFoundException;
    void debit(String accountId,double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficent;
    void credit(String accountId,double amount, String description) throws BankAccountNotFoundException;
  

	void transfer(String accountIdSouce, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficent;
    
	 List<BankAccountDTO> bancAccountList();
	 CustomerDTO getCustomer(Long idCust);

	CustomerDTO updateCostumer(CustomerDTO customerDTO);

	void deleteCustomer(Long customerID);

	BankAccountDTO getBanckAccount(String accountID) throws BankAccountNotFoundException;

	Costumer saveCostumer(Costumer costumer);
	
}
