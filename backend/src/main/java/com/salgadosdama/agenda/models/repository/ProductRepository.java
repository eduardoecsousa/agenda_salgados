package com.salgadosdama.agenda.models.repository;

import com.salgadosdama.agenda.models.entity.Order;
import com.salgadosdama.agenda.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findByIdOrder(Order order);
  List<Product> findByIdOrderAndActive(Order idOrder, boolean active);
}
