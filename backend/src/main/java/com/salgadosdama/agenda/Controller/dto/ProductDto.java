package com.salgadosdama.agenda.Controller.dto;

import com.salgadosdama.agenda.models.entity.Order;
import com.salgadosdama.agenda.models.entity.Product;
import com.salgadosdama.agenda.models.entity.Savory;

public record ProductDto(Long id, Order idOrder, Savory idSavory, int quantity) {
  public static ProductDto fromEntity(Product product){
    return new ProductDto(
            product.getId(),
            product.getIdOrder(),
            product.getIdSavory(),
            product.getQuantity()
    );
  }
}
