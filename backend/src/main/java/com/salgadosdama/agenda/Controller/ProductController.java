package com.salgadosdama.agenda.Controller;

import com.salgadosdama.agenda.Controller.dto.ProductDto;
import com.salgadosdama.agenda.models.entity.Product;
import com.salgadosdama.agenda.service.ProductService;
import com.salgadosdama.agenda.service.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/{idOrder}")
  public List<ProductDto> findByIdOrder(@PathVariable Long idOrder) throws OrderNotFoundException {
    return productService.findByIdOrder(idOrder)
            .stream()
            .map(ProductDto::fromEntity)
            .toList();
  }
}

