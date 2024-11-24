package com.salgadosdama.agenda.Controller.dto;

import com.salgadosdama.agenda.models.entity.Type;

public record TypeDto(Long id, String typeName) {

  public static TypeDto fromEntity(Type type){
    return new TypeDto(
            type.getId(),
            type.getNameType()
    );
  }
}
