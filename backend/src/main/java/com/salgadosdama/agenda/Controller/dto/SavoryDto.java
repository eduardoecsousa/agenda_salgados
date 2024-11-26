package com.salgadosdama.agenda.Controller.dto;

import com.salgadosdama.agenda.models.entity.Savory;

public record SavoryDto(Long id, String nameSavory) {
  public static SavoryDto fromEntity(Savory savory){
    return new SavoryDto(
            savory.getId(),
            savory.getNameSavory()
    );
  }
}
