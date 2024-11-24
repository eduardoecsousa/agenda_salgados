package com.salgadosdama.agenda.Controller.dto;

import com.salgadosdama.agenda.models.entity.Type;
import com.salgadosdama.agenda.models.entity.User;

public record UserDto(Long id, String name, Type type, String cpf) {
  public static UserDto fromEntity(User user){
    return new UserDto(
            user.getId(),
            user.getName(),
            user.getUserType(),
            user.getCpf()
    );
  }
}
