package com.salgadosdama.agenda.service;

import com.salgadosdama.agenda.Controller.dto.CreateProductDto;
import com.salgadosdama.agenda.models.entity.Order;
import com.salgadosdama.agenda.models.entity.Product;
import com.salgadosdama.agenda.models.entity.Savory;
import com.salgadosdama.agenda.models.repository.OrderRepository;
import com.salgadosdama.agenda.models.repository.ProductRepository;
import com.salgadosdama.agenda.models.repository.SavoryRepository;
import com.salgadosdama.agenda.service.exception.OrderNotFoundException;
import com.salgadosdama.agenda.service.exception.ProductNotFoundException;
import com.salgadosdama.agenda.service.exception.SavoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {
  private final ProductRepository productRepository;
  private final OrderRepository orderRepository;
  private final SavoryRepository savoryRepository;

  //constructor
  @Autowired
  public ProductService(ProductRepository productRepository, OrderRepository orderRepository, SavoryRepository savoryRepository) {
    this.productRepository = productRepository;
    this.orderRepository = orderRepository;
    this.savoryRepository = savoryRepository;
  }

  //busco produto pelo id
  public List<Product> findByIdOrder(Long id) throws OrderNotFoundException {
    Order order = orderRepository.findById(id)
            .orElseThrow(OrderNotFoundException::new);
    return productRepository.findByIdOrder(order);
  }

  //busca todos os products;
  public List<Product> findAllProducts(){
    return productRepository.findAll();
  }

  //Atualiza os produtos
  private void updateProductsToOrder(CreateProductDto product) throws ProductNotFoundException, SavoryNotFoundException {
    Product oldProduct = productRepository.findById(product.id())
            .orElseThrow(ProductNotFoundException::new);
    Savory savory = savoryRepository.findById(product.idSavory())
            .orElseThrow(SavoryNotFoundException::new);


    oldProduct.setQuantity(product.quantity());
    oldProduct.setIdSavory(savory);

    productRepository.save(oldProduct);

  }

  //Cria novo produto de acordo com o pedido
  public void addProductsToOrder(Order order,CreateProductDto productDto) throws SavoryNotFoundException, ProductNotFoundException, OrderNotFoundException {

    Savory savory = savoryRepository.findById(productDto.idSavory())
            .orElseThrow(SavoryNotFoundException::new);

    Product product = new Product();
    product.setIdOrder(order);
    product.setIdSavory(savory);
    product.setQuantity(productDto.quantity());
    product.setActive(true);
    productRepository.save(product);

  }

  //Delega se ele atualiza ou cria um novo
  public List<Product> updateOrAddProductsForOrder(long idOrder, List<CreateProductDto> createProductDtos) throws OrderNotFoundException, SavoryNotFoundException, ProductNotFoundException {
    Order order = orderRepository.findById(idOrder)
            .orElseThrow(OrderNotFoundException::new);
    for (CreateProductDto productDto : createProductDtos){
      if (productDto.id() == null){
        addProductsToOrder(order, productDto);
      } else {
        updateProductsToOrder(productDto);
      }
    }
    return productRepository.findByIdOrder(order);
  }

  //Finaliza o produto
  public void completedProduct(Order order) {
    List<Product> products = productRepository.findByIdOrderAndActive(order, true);

    for (Product product : products) {
      Savory savory = savoryRepository.findById(product.getIdSavory().getId())
              .orElseThrow();
      savory.setQuantity(savory.getQuantity() - product.getQuantity());
      if (savory.getQuantity() < 0) savory.setQuantity(0);
      savoryRepository.save(savory);
      product.setActive(false);
      productRepository.save(product);
    }
  }

  //Busco o products ativos
  public List<Product> getProductsActive(){
    List<Product> products = productRepository.findByActive(true);

    return products;
  }

  //Retorna a quatidade total de savory de Order ativas
  public List<Map<String, Object>> getQuantityForSavory(){
    List<Object[]> products = productRepository.addQuantityBySavory();

    return products.stream().map(product -> {
      Map<String, Object> map = new HashMap<>();
      map.put("idSavory", product[0]);
      map.put("nameSavory", product[1]);
      map.put("totalQuantity", product[2]);
      return map;
    }).collect(Collectors.toList());
  }
}
