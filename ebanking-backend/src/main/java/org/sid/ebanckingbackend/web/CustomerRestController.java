package org.sid.ebanckingbackend.web;

import java.util.List;

import org.sid.ebanckingbackend.dtos.CustomerDTO;
import org.sid.ebanckingbackend.exception.CustomerNotFoundException;
import org.sid.ebanckingbackend.service.BanckAccountService;
import org.sid.ebanckingbackend.service.BankAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CustomerRestController {

	@Autowired
	BanckAccountService banckAccountService ;
	
	@GetMapping("/customer")
	public List<CustomerDTO> getListCustomer(){
		
		return banckAccountService.listCostumer();
	}
	
	@GetMapping("/customer/{id}")
	public CustomerDTO getCustomer(@PathVariable(name="id") Long id) throws CustomerNotFoundException{
	
		return banckAccountService.getCustomer(id);
	}
	
	@PostMapping("/customers")
	public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
		return banckAccountService.saveCostumer(customerDTO);
		
		
	}
	
	@PutMapping("/customers/{customerID}")
	public CustomerDTO updateCustomerDTO(@PathVariable Long customerID, @RequestBody CustomerDTO customerDTO) {
		customerDTO.setId(customerID);
		return banckAccountService.updateCostumer(customerDTO);
		
	}
	
	@DeleteMapping("/customer/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		banckAccountService.deleteCustomer(id);
		
		
	}
}
