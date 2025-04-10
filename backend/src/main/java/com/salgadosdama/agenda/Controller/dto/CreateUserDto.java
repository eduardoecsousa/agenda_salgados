package com.salgadosdama.agenda.Controller.dto;

import com.salgadosdama.agenda.models.entity.User;
import com.salgadosdama.agenda.security.security.Role;

public record CreateUserDto(String name, Role role, String cpf, String username, String password) {
  public User toEntity(){
    return new User(name, role, cpf, username, password);
  }
}
