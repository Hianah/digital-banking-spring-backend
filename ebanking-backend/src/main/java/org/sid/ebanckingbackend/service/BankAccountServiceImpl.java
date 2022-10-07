package org.sid.ebanckingbackend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.sid.ebanckingbackend.dtos.BankAccountDTO;
import org.sid.ebanckingbackend.dtos.CurrentAccountDTO;
import org.sid.ebanckingbackend.dtos.CustomerDTO;
import org.sid.ebanckingbackend.dtos.SavingAccountDTO;
import org.sid.ebanckingbackend.entities.AccountOperation;
import org.sid.ebanckingbackend.entities.BankAccount;
import org.sid.ebanckingbackend.entities.Costumer;
import org.sid.ebanckingbackend.entities.CurrentAccount;
import org.sid.ebanckingbackend.entities.SavingAccount;
import org.sid.ebanckingbackend.enums.OPerationType;
import org.sid.ebanckingbackend.exception.BalanceNotSufficent;
import org.sid.ebanckingbackend.exception.BankAccountNotFoundException;
import org.sid.ebanckingbackend.exception.CustomerNotFoundException;
import org.sid.ebanckingbackend.mappers.BankAccountMapperImpl;
import org.sid.ebanckingbackend.repositoris.AccountOperationRepository;
import org.sid.ebanckingbackend.repositoris.BankAccountRepository;
import org.sid.ebanckingbackend.repositoris.CostumerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BanckAccountService {
	
	/**@Autowired est depracated pour faire l'injection d'independence**/
	@Autowired
	private AccountOperationRepository accountOperationRepository;
	@Autowired
	private CostumerRepository costumerRepository;
	@Autowired
	private BankAccountRepository bankAccountRepository;
	@Autowired
	private BankAccountMapperImpl dtoMapper;
	
	/**on peut aussi utilsier lombok a la place d'ici**/
	Logger log=LoggerFactory.getLogger(this.getClass().getName());

	
	/**donc on va utiliser un constructeur avec les trois parâmetres or utilise Lombok @AllArgsConstructor**/
	
	
	
	  public BankAccountServiceImpl(AccountOperationRepository
	  accountOperationRepository, CostumerRepository costumerRepository,
	  BankAccountRepository bankAccountRepository) { super();
	  this.accountOperationRepository = accountOperationRepository;
	  this.costumerRepository = costumerRepository; this.bankAccountRepository =
	  bankAccountRepository; }
	 
	 
	 
	
	
	@Override
	public CurrentAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft,  long customerID) throws CustomerNotFoundException{
		Optional<Costumer> costumer = costumerRepository.findById(customerID);
		if(costumer==null){
			throw new CustomerNotFoundException("Customer not found");
}
		
		CurrentAccount currentAccount= new CurrentAccount();
		currentAccount.setId(UUID.randomUUID().toString());
		currentAccount.setCreatedAt(new Date());
		currentAccount.setBalance(initialBalance);
		currentAccount.setOverDraft(overDraft);
		currentAccount.setCostumer(costumer);
		CurrentAccount savedBankAccount= bankAccountRepository.save(currentAccount);
		
		return dtoMapper.fromCurrentAccount(savedBankAccount);
	}
	@Override
	public SavingAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, long customerID) throws CustomerNotFoundException{
		Optional<Costumer> costumer = costumerRepository.findById(customerID);
		if(costumer==null){
			throw new CustomerNotFoundException("Customer not found");
		}
		
		SavingAccount savingAccount= new SavingAccount();
		savingAccount.setId(UUID.randomUUID().toString());
		savingAccount.setCreatedAt(new Date());
		savingAccount.setBalance(initialBalance);
		savingAccount.setInterestRate(interestRate);
		savingAccount.setCostumer(costumer);
		SavingAccount savedBankAccount= bankAccountRepository.save(savingAccount);
		
		return dtoMapper.fromSavingAccount(savedBankAccount);
		
		
	}

	@Override
	public Costumer saveCostumer(Costumer costumer) {
	
		log.info("Saving Customer");
	
	Costumer savedCustomer = costumerRepository.save(costumer);
			return savedCustomer;
	}
	
	@Override
	public CustomerDTO saveCostumer(CustomerDTO customerDTO) {
	
		log.info("Saving Customer");
	Costumer customer = dtoMapper.fromCustomerDTO(customerDTO);
	Costumer savedCustomer = costumerRepository.save(customer);
			return dtoMapper.fromCustomer(savedCustomer);
	}
	
	@Override
	public CustomerDTO updateCostumer(CustomerDTO customerDTO) {
	
		log.info("Saving Customer");
	Costumer customer = dtoMapper.fromCustomerDTO(customerDTO);
	Costumer savedCustomer = costumerRepository.save(customer);
			return dtoMapper.fromCustomer(savedCustomer);
	}
	
	@Override
	public void deleteCustomer(Long customerID) {
		costumerRepository.deleteById(customerID);
		
		
	}

	@Override
	public List<CustomerDTO> listCostumer() {
		List<Costumer> customers= costumerRepository.findAll();
		
		/**Programation functionnel utilisand le stream**/
		//pour chaque customer dans la list(customers.stream().map), je vais mapper customerDTO - 
		/*
		 * List<CustomerDTO> customerDTO= customers.stream()
		 * .map(customer->dtoMapper.fromCustomer(customer))
		 * .collect(Collectors.toList()); //Lambda
		 */
		
		/**Programation Interatiive**/
		List<CustomerDTO>customerDTO= new ArrayList<>();
		for (Costumer customer:customers) {
			CustomerDTO custDTO= dtoMapper.fromCustomer(customer);
			customerDTO.add(custDTO);
	
				}
		return customerDTO;
	}

	@Override
	public BankAccountDTO getBanckAccount(String accountID) throws BankAccountNotFoundException {
		BankAccount bankAccount= bankAccountRepository.findById(accountID).orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));
		
		if(bankAccount instanceof SavingAccount) {
			SavingAccount savAccount=(SavingAccount)bankAccount;
			return dtoMapper.fromSavingAccount(savAccount);
			
		}else {
			CurrentAccount curAccount=(CurrentAccount) bankAccount;
			return dtoMapper.fromCurrentAccount(curAccount);
			
		}
		
	}

	@Override
	//public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficent{
	public void debit(String accountId,double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficent{
		BankAccount bankAccount= bankAccountRepository.findById(accountId).orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));
		
	/**Si le sold est mineur que le montant qui va être retiré, donc erreur **/
	/*
	 * if(bankAccount.getBalance()<amount) { throw new
	 * BalanceNotSufficent("Balance not Sufficent"); }
	 */
		/**maintenaint il faut voir quelle operation**/
		AccountOperation accountOperation= new AccountOperation();
		accountOperation.setAmount(amount);
		accountOperation.setOperationType(OPerationType.DEBIT);
		accountOperation.setDateOperation(new Date());
		//je doit créer une operation qui concern a c'ette compte 
		accountOperation.setDescription(description); 
		accountOperation.setBankAccount(bankAccount);
		accountOperationRepository.save(accountOperation);
		//metre a jour le sold
		bankAccount.setBalance(bankAccount.getBalance()-amount);
		bankAccountRepository.save(bankAccount);
}

	@Override
	public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
		
		BankAccount bankAccount= bankAccountRepository.findById(accountId).orElseThrow(()->new BankAccountNotFoundException("BankAccount not found"));
		
		
			/**maintenaint il faut voir quelle operation**/
			AccountOperation accountOperation= new AccountOperation();
			accountOperation.setAmount(amount);
			accountOperation.setOperationType(OPerationType.CREDIT);
			accountOperation.setDateOperation(new Date());
			//je doit créer une operation qui concern a c'ette compte 
			accountOperation.setDescription(description); 
			accountOperation.setBankAccount(bankAccount);
			accountOperationRepository.save(accountOperation);
			//metre a jour le sold
			bankAccount.setBalance(bankAccount.getBalance()+amount);
			bankAccountRepository.save(bankAccount);
}

	@Override
	public void transfer(String accountIdSouce, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficent {
		debit(accountIdSouce, amount, accountIdDestination);
		credit(accountIdDestination,amount,accountIdSouce);
			
		
	}
	@Override
	 public List<BankAccountDTO> bancAccountList(){
		List<BankAccount> bankAccounts = bankAccountRepository.findAll();
		List<BankAccountDTO> listBankAccountDTO= bankAccounts.stream().map(bankAccount->{
			if(bankAccount instanceof SavingAccount) {
				SavingAccount saveAccount= (SavingAccount)bankAccount;
				return dtoMapper.fromSavingAccount(saveAccount);
			}else {
				CurrentAccount currentAccount= (CurrentAccount)bankAccount;
				return dtoMapper.fromCurrentAccount(currentAccount);
				
			}
		}).collect(Collectors.toList());
		return listBankAccountDTO;
		
	}
	

	public CustomerDTO getCustomer(Long id) throws CustomerNotFoundException{
		Costumer customer= costumerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException("Customer not found"));
		return dtoMapper.fromCustomer(customer);
	}



	
}
