package com.salgadosdama.agenda.Controller.dto;

import com.salgadosdama.agenda.models.entity.Savory;
import com.salgadosdama.agenda.models.entity.Stock;

public record CreateStockDto(Savory savory, int quantity) {
  public Stock toEntity(){
    return new Stock(savory, quantity);
  }
}
