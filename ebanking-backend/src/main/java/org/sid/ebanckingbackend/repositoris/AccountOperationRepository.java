package org.sid.ebanckingbackend.repositoris;

import org.sid.ebanckingbackend.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long>{

}
