package com.salgadosdama.agenda.models.repository;

import com.salgadosdama.agenda.models.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
