package com.salgadosdama.agenda.Controller.dto;

import com.salgadosdama.agenda.models.entity.Savory;
import com.salgadosdama.agenda.models.entity.Stock;

public record StockDto(Long id, Savory savory, int quantity){
  public static StockDto fromEntity(Stock stock){
    return new StockDto(
            stock.getId(),
            stock.getIdSavory(),
            stock.getQuantity()
    );
  }
}
