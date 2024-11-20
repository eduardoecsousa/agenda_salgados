package com.salgadosdama.agenda.models.repository;

import com.salgadosdama.agenda.models.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
