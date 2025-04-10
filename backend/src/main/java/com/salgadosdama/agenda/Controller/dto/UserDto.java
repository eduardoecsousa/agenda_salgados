package com.salgadosdama.agenda.Controller.dto;

import com.salgadosdama.agenda.models.entity.User;
import com.salgadosdama.agenda.security.security.Role;

public record UserDto(Long id, String name, Role role, String cpf) {
  public static UserDto fromEntity(User user){
    return new UserDto(
            user.getId(),
            user.getName(),
            user.getRole(),
            user.getCpf()
    );
  }
}
