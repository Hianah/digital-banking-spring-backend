package org.sid.ebanckingbackend;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.hibernate.internal.build.AllowSysOut;
import org.sid.ebanckingbackend.dtos.BankAccountDTO;
import org.sid.ebanckingbackend.dtos.CurrentAccountDTO;
import org.sid.ebanckingbackend.dtos.CustomerDTO;
import org.sid.ebanckingbackend.dtos.SavingAccountDTO;
import org.sid.ebanckingbackend.entities.AccountOperation;
import org.sid.ebanckingbackend.entities.Costumer;
import org.sid.ebanckingbackend.entities.CurrentAccount;
import org.sid.ebanckingbackend.entities.SavingAccount;
import org.sid.ebanckingbackend.enums.AccountStatus;
import org.sid.ebanckingbackend.enums.OPerationType;
import org.sid.ebanckingbackend.exception.BalanceNotSufficent;
import org.sid.ebanckingbackend.exception.BankAccountNotFoundException;
import org.sid.ebanckingbackend.exception.CustomerNotFoundException;
import org.sid.ebanckingbackend.repositoris.AccountOperationRepository;
import org.sid.ebanckingbackend.repositoris.BankAccountRepository;
import org.sid.ebanckingbackend.repositoris.CostumerRepository;
import org.sid.ebanckingbackend.service.BanckAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class EbankingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbankingBackendApplication.class, args);
	}
	
	/**
	 * Pour tester le Service
	 * 
	 * **/
	//@Bean
	CommandLineRunner commandLineRunner(BanckAccountService bankAccoutService) {
		
	return args->{
			Stream.of("Maria","José","Miguel").forEach (name-> {
				Costumer customer= new Costumer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				bankAccoutService.saveCostumer(customer);
		});
			//pour chaque client je vais créer une Compte courrent et une epargne
			bankAccoutService.listCostumer().forEach(customer->{
			try {
				bankAccoutService.saveCurrentBankAccount(Math.random()*9000,9000,customer.getId());
				bankAccoutService.saveSavingBankAccount(Math.random()*120000,5.5,customer.getId());
				List<BankAccountDTO> listbankAccount= bankAccoutService.bancAccountList();
				//bankAccoutService.bancAccountList().forEach(account->{
				for(BankAccountDTO bankAccount:listbankAccount) {
					for(int i=0; i < 10;i++) {
						String accountId;
						if(bankAccount instanceof SavingAccountDTO) {
							accountId=((SavingAccountDTO) bankAccount).getId();
						}else {
							accountId=((CurrentAccountDTO) bankAccount).getId();
						}
						bankAccoutService.credit(accountId ,10000+Math.random()*120000, "CREDIT");
						bankAccoutService.debit(accountId ,10000+Math.random()*120000, "DEBIT");
					}
					
				}
				
			}catch(CustomerNotFoundException ex) {
				ex.printStackTrace();	
			}catch (BankAccountNotFoundException|BalanceNotSufficent e) {
				e.printStackTrace();
			}
		});
			};
				
	}	
		
	
		@Bean
		CommandLineRunner commandLineRunner(CostumerRepository costumerRepository, 
		BankAccountRepository bankAccountRepository, AccountOperationRepository operationRepository) {
			
	  	
		  return args->{Stream.of("Ana","Pedro", "Miguel").forEach(name->{ Costumer
		  costumer= new Costumer(); 
		  costumer.setName(name);
		  costumer.setEmail(name+"@gmail.com");
		  costumerRepository.save(costumer); });
		 
				/*
				 * return args ->{ Stream.of("Luis", "Yamine", "Feiza").forEach(name->{ Costumer
				 * costumer = new Costumer(); costumer.setName(name);
				 * costumer.setEmail(name+"@gmail.com"); costumerRepository.save(costumer); });
				 */
	  
	  //
	  //
	  
	  //
	  }; }
	 
}
