package com.salgadosdama.agenda.service;

import com.salgadosdama.agenda.Controller.dto.CreateProductDto;
import com.salgadosdama.agenda.models.entity.Order;
import com.salgadosdama.agenda.models.entity.Product;
import com.salgadosdama.agenda.models.entity.Savory;
import com.salgadosdama.agenda.models.entity.Stock;
import com.salgadosdama.agenda.models.repository.OrderRepository;
import com.salgadosdama.agenda.models.repository.ProductRepository;
import com.salgadosdama.agenda.models.repository.SavoryRepository;
import com.salgadosdama.agenda.models.repository.StockRepository;
import com.salgadosdama.agenda.service.exception.OrderNotFoundException;
import com.salgadosdama.agenda.service.exception.ProductNotFoundException;
import com.salgadosdama.agenda.service.exception.SavoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
  private final ProductRepository productRepository;
  private final OrderRepository orderRepository;
  private final SavoryRepository savoryRepository;
  private final StockRepository stockRepository;

  @Autowired
  public ProductService(ProductRepository productRepository, OrderRepository orderRepository, SavoryRepository savoryRepository, StockRepository stockRepository) {
    this.productRepository = productRepository;
    this.orderRepository = orderRepository;
    this.savoryRepository = savoryRepository;
    this.stockRepository = stockRepository;
  }

  public List<Product> findByIdOrder(Long id) throws OrderNotFoundException {
    Order order = orderRepository.findById(id)
            .orElseThrow(OrderNotFoundException::new);
    return productRepository.findByIdOrder(order);
  }

  public List<Product> findAllProducts(){
    return productRepository.findAll();
  }

  private void updateProductsToOrder(CreateProductDto product) throws ProductNotFoundException, SavoryNotFoundException {
    Product oldProduct = productRepository.findById(product.id())
            .orElseThrow(ProductNotFoundException::new);
    Savory savory = savoryRepository.findById(product.idSavory())
            .orElseThrow(SavoryNotFoundException::new);


    oldProduct.setQuantity(product.quantity());
    oldProduct.setIdSavory(savory);

    productRepository.save(oldProduct);

  }

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

  public void completedProduct(Order order){
    List<Product> products = productRepository.findByIdOrderAndActive(order);

    for (Product product : products) {
      Stock stock = stockRepository.findByIdSavory(product.getIdSavory());
      stock.setQuantity(stock.getQuantity() - product.getQuantity());
      stockRepository.save(stock);
      product.setActive(false);
      productRepository.save(product);
    }
  }


}
