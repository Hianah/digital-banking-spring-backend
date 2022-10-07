package org.sid.ebanckingbackend.web;

import java.util.List;

import org.sid.ebanckingbackend.dtos.BankAccountDTO;
import org.sid.ebanckingbackend.exception.BankAccountNotFoundException;
import org.sid.ebanckingbackend.service.BanckAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankAccountRestAPI {

	private BanckAccountService bankAccountService;
	
	public BankAccountRestAPI(BanckAccountService bankAccountService) {
		
		this.bankAccountService= bankAccountService;
	}
	
	@GetMapping("/accounts/{accountId}")
	public BankAccountDTO getBanckAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
		
		return bankAccountService.getBanckAccount(accountId);
	}
	
	@GetMapping("/accounts")
	public List<BankAccountDTO> getListBankAccounts(){
		
		return bankAccountService.bancAccountList();
	}
}
