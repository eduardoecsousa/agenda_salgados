package com.salgadosdama.agenda.models.repository;

import com.salgadosdama.agenda.models.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
}
