package com.salgadosdama.agenda.Controller;

import com.salgadosdama.agenda.Controller.dto.CreateProductDto;
import com.salgadosdama.agenda.Controller.dto.ProductDto;
import com.salgadosdama.agenda.models.entity.Product;
import com.salgadosdama.agenda.service.ProductService;
import com.salgadosdama.agenda.service.exception.OrderNotFoundException;
import com.salgadosdama.agenda.service.exception.ProductNotFoundException;
import com.salgadosdama.agenda.service.exception.SavoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

  @GetMapping()
  public List<ProductDto> findAllProducts(){
    return productService.findAllProducts()
            .stream()
            .map(ProductDto::fromEntity)
            .toList();
  }

  @PutMapping("/order/{idOrder}")
  public List<ProductDto> updateProducts(@PathVariable long idOrder,@RequestBody List<CreateProductDto> productDtos) throws OrderNotFoundException, SavoryNotFoundException, ProductNotFoundException {
    return productService.updateOrAddProductsForOrder(idOrder, productDtos)
            .stream()
            .map(ProductDto::fromEntity)
            .toList();
  }

  @GetMapping("/active")
  public List<Map<String, Object>> findByAllActive(){
    return productService.getQuantityForSavory();
  }


}

