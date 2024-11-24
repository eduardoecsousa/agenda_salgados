package com.salgadosdama.agenda.Controller.dto;

import com.salgadosdama.agenda.models.entity.Type;

public record CreatedTypeDto(String typeName) {
  public Type toEntity(){
    return new Type(typeName);
  }
}
