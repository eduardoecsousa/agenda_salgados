package com.salgadosdama.agenda.service;

import com.salgadosdama.agenda.models.entity.Order;
import com.salgadosdama.agenda.models.entity.Product;
import com.salgadosdama.agenda.models.repository.OrderRepository;
import com.salgadosdama.agenda.models.repository.ProductRepository;
import com.salgadosdama.agenda.service.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
  private final ProductRepository productRepository;
  private final OrderRepository orderRepository;

  @Autowired
  public ProductService(ProductRepository productRepository, OrderRepository orderRepository) {
    this.productRepository = productRepository;
    this.orderRepository = orderRepository;
  }

  public List<Product> findByIdOrder(Long id) throws OrderNotFoundException {
    Order order = orderRepository.findById(id)
            .orElseThrow(OrderNotFoundException::new);
    return productRepository.findByIdOrder(order);
  }
}
