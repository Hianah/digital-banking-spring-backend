package org.sid.ebanckingbackend.mappers;

import org.sid.ebanckingbackend.dtos.CurrentAccountDTO;
import org.sid.ebanckingbackend.dtos.CustomerDTO;
import org.sid.ebanckingbackend.dtos.SavingAccountDTO;
import org.sid.ebanckingbackend.entities.Costumer;
import org.sid.ebanckingbackend.entities.CurrentAccount;
import org.sid.ebanckingbackend.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

//MapStruc - genere toutes les interfaces, l'assinature des toutes les metodos, si on veux.
@Service
public class BankAccountMapperImpl {
	
	/**je lui donne un object customer comme input et il me retourn un customerDTO
	 * */
	public CustomerDTO fromCustomer(Costumer customer) {
		
		CustomerDTO customerDto = new CustomerDTO();
		//Ici Il copie des properties ver une outre - d'un object ver une outre
		//BeanUtils.copyProperties(customer, customerDto);
		//customerDto.setId(customer.getId());
		customerDto.setName(customer.getName());	
		customerDto.setEmail(customer.getEmail());
		return customerDto;
	}
	/**je lui donne un object customerDTO comme input et il me retourn un customer - L'inverse
	 * */
	
public Costumer fromCustomerDTO(CustomerDTO customerDto) {
		
	Costumer customer = new Costumer();
	BeanUtils.copyProperties(customerDto, customer);

	return customer;
	}

public SavingAccountDTO fromSavingAccount(SavingAccount savingAccount) {
	
	SavingAccountDTO savAccountDTO = new SavingAccountDTO();
	BeanUtils.copyProperties(savAccountDTO, savingAccount);
	savAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCostumer()));

	return savAccountDTO;
}

public SavingAccount fromSavingAccountDTO(SavingAccountDTO savingAccountDTO) {
	
	SavingAccount savAccount = new SavingAccount();
	BeanUtils.copyProperties(savAccount, savingAccountDTO);
	savAccount.setCostumer(fromCustomerDTO(savingAccountDTO.getCustomerDTO()));
	return savAccount;
}

public CurrentAccountDTO fromCurrentAccount(CurrentAccount currentAccount) {
	
	CurrentAccountDTO currAccountDTO = new CurrentAccountDTO();
	BeanUtils.copyProperties(currAccountDTO, currentAccount);
	
	currAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCostumer()));

	return currAccountDTO;
}

public CurrentAccount fromCurrentAccountDTO(CurrentAccountDTO currentAccountDTO) {
	
	CurrentAccount currAccount = new CurrentAccount();
	BeanUtils.copyProperties(currAccount, currentAccountDTO);
	currAccount.setCostumer(fromCustomerDTO(currentAccountDTO.getCustomerDTO()));

	return currAccount;
}



}
