package com.salgadosdama.agenda.service;

import com.salgadosdama.agenda.models.entity.Customer;
import com.salgadosdama.agenda.models.entity.Order;
import com.salgadosdama.agenda.models.entity.Product;
import com.salgadosdama.agenda.models.entity.Savory;
import com.salgadosdama.agenda.models.repository.CustomerRepository;
import com.salgadosdama.agenda.models.repository.OrderRepository;
import com.salgadosdama.agenda.models.repository.ProductRepository;
import com.salgadosdama.agenda.models.repository.SavoryRepository;
import com.salgadosdama.agenda.service.exception.CustomerNotFoundException;
import com.salgadosdama.agenda.service.exception.SavoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final SavoryRepository savoryRepository;

  private final CustomerRepository customerRepository;

  @Autowired
  public OrderService(OrderRepository orderRepository,
                      ProductRepository productRepository, SavoryRepository savoryRepository, CustomerRepository customerRepository) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.savoryRepository = savoryRepository;
    this.customerRepository = customerRepository;
  }

  public Order createNewOrder(Order order, List<Product> products) throws SavoryNotFoundException, CustomerNotFoundException {
    Customer customer = customerRepository.findById(order.getIdCustomer().getId())
            .orElseThrow(CustomerNotFoundException::new);
    order.setIdCustomer(customer);
    Order newOrder = orderRepository.save(order);

    for(Product product : products){
      Savory savory = savoryRepository.findById(product.getId())
              .orElseThrow(SavoryNotFoundException::new);
      product.setIdSavory(savory);
      product.setIdOrder(newOrder);
      productRepository.save(product);
    }
    return newOrder;
  }

  public List<Order> findAllOrder(){
    return orderRepository.findAll();
  }
}
