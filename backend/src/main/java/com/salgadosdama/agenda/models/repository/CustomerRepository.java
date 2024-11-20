package com.salgadosdama.agenda.models.repository;

import com.salgadosdama.agenda.models.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
