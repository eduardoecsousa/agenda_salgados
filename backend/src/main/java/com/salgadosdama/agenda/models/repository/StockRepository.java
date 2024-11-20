package com.salgadosdama.agenda.models.repository;

import com.salgadosdama.agenda.models.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
