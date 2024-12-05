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

  public Order createNewOrder(CreatedOrderDto createdOrderDto) throws SavoryNotFoundException, CustomerNotFoundException {
    Order order = createAndSaveOrder(createdOrderDto);

    addProductsToOrder(order, createdOrderDto.products());

    return order;
  }

  private Order createAndSaveOrder(CreatedOrderDto createdOrderDto) throws CustomerNotFoundException {
    Customer customer = customerRepository.findById(createdOrderDto.idCustomer())
            .orElseThrow(CustomerNotFoundException::new);

    Order order = new Order();
    order.setCompleted(createdOrderDto.completed());
    order.setDate(createdOrderDto.date());
    order.setIdCustomer(customer);

    return orderRepository.save(order);
  }

  private void addProductsToOrder(Order order, List<CreateProductDto> products) throws SavoryNotFoundException {
    for (CreateProductDto productDto : products) {
      Savory savory = savoryRepository.findById(productDto.idSavory())
              .orElseThrow(SavoryNotFoundException::new);

      Product product = new Product();
      product.setIdOrder(order);
      product.setIdSavory(savory);
      product.setQuantity(productDto.quantity());

      productRepository.save(product);
    }
  }

  public List<Order> findAllOrder(){
    return orderRepository.findAll();
  }
}
