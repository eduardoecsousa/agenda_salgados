package com.salgadosdama.agenda.models.repository;

import com.salgadosdama.agenda.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
