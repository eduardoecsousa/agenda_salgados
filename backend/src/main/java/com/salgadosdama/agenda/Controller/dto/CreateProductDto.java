package com.salgadosdama.agenda.Controller.dto;

import com.salgadosdama.agenda.models.entity.Product;

public record CreateProductDto(Long id, Long idSavory, int quantity) {
   public static CreateProductDto fromEntity(Product product){
     return new CreateProductDto(
             product.getId(),
             product.getIdSavory().getId(),
             product.getQuantity()
     );

   }
}
