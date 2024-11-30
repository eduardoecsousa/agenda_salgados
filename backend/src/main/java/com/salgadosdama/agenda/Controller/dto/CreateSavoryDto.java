package com.salgadosdama.agenda.Controller.dto;

import com.salgadosdama.agenda.models.entity.Savory;

public record CreateSavoryDto(String nameSavory, int quantity) {
  public Savory toEntity(){ return new Savory(nameSavory); }
}
