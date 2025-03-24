package com.salgadosdama.agenda.models.repository;

import com.salgadosdama.agenda.models.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByActive(boolean active);
}
