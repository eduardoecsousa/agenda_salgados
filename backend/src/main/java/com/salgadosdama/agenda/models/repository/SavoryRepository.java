package com.salgadosdama.agenda.models.repository;

import com.salgadosdama.agenda.models.entity.Savory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavoryRepository extends JpaRepository<Savory, Long> {
}
