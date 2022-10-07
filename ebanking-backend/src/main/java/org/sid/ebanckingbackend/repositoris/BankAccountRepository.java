package org.sid.ebanckingbackend.repositoris;

import org.sid.ebanckingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}
