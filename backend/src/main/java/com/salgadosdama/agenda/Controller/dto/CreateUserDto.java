package com.salgadosdama.agenda.Controller.dto;

import com.salgadosdama.agenda.models.entity.Type;
import com.salgadosdama.agenda.models.entity.User;

public record CreateUserDto(String name, Type type, String cpf) {
  public User toEntity(){
    return new User(name, type, cpf);
  }
}