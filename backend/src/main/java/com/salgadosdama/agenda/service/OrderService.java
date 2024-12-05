package com.salgadosdama.agenda.service;

import com.salgadosdama.agenda.Controller.dto.CreateProductDto;
import com.salgadosdama.agenda.Controller.dto.CreatedOrderDto;
import com.salgadosdama.agenda.models.entity.Customer;
import com.salgadosdama.agenda.models.entity.Order;
import com.salgadosdama.agenda.models.entity.Product;
import com.salgadosdama.agenda.models.entity.Savory;
import com.salgadosdama.agenda.models.repository.CustomerRepository;
import com.salgadosdama.agenda.models.repository.OrderRepository;
import com.salgadosdama.agenda.models.repository.ProductRepository;
import com.salgadosdama.agenda.models.repository.SavoryRepository;
import com.salgadosdama.agenda.service.exception.CustomerNotFoundException;
import com.salgadosdama.agenda.service.exception.OrderNotFoundException;
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

  public Order createNewOrder(CreatedOrderDto createdOrderDto) throws SavoryNotFoundException, CustomerNotFoundException, OrderNotFoundException {
    Order order = new Order();
    order.setCompleted(createdOrderDto.completed());
    order.setDate(createdOrderDto.date());
    Customer customer = customerRepository.findById(createdOrderDto.idCustomer())
            .orElseThrow(CustomerNotFoundException::new);
    order.setIdCustomer(customer);
    Order newOrder = orderRepository.save(order);
    for(CreateProductDto createProductDto : createdOrderDto.products()){
      Savory savory = savoryRepository.findById(createProductDto.idSavory())
              .orElseThrow(SavoryNotFoundException::new);
      Product product = new Product();
      product.setIdOrder(newOrder);
      product.setIdSavory(savory);
      product.setQuantity(createProductDto.quantity());
      productRepository.save(product);
    }
    return newOrder;
  }

  public List<Order> findAllOrder(){
    return orderRepository.findAll();
  }
}
