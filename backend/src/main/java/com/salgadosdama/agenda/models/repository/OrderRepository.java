package com.salgadosdama.agenda.models.repository;

import com.salgadosdama.agenda.models.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
