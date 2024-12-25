package com.salgadosdama.agenda.service;

import com.salgadosdama.agenda.Controller.dto.CreatedOrderDto;
import com.salgadosdama.agenda.models.entity.Customer;
import com.salgadosdama.agenda.models.entity.Order;
import com.salgadosdama.agenda.models.repository.*;
import com.salgadosdama.agenda.service.exception.CustomerNotFoundException;
import com.salgadosdama.agenda.service.exception.OrderNotFoundException;
import com.salgadosdama.agenda.service.exception.ProductNotFoundException;
import com.salgadosdama.agenda.service.exception.SavoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
  private final OrderRepository orderRepository;
  private final SavoryRepository savoryRepository;
  private final CustomerRepository customerRepository;
  private final StockRepository stockRepository;
  private final ProductService productService;

  @Autowired
  public OrderService(OrderRepository orderRepository,
                      SavoryRepository savoryRepository, CustomerRepository customerRepository, StockRepository stockRepository, ProductService productService) {
    this.orderRepository = orderRepository;
    this.savoryRepository = savoryRepository;
    this.customerRepository = customerRepository;
    this.stockRepository = stockRepository;
    this.productService = productService;
  }

  public Order createNewOrder(CreatedOrderDto createdOrderDto) throws SavoryNotFoundException, CustomerNotFoundException, OrderNotFoundException, ProductNotFoundException {
    Order order = createAndSaveOrder(createdOrderDto);

    productService.updateOrAddProductsForOrder(order.getId(), createdOrderDto.products());

    return order;
  }

  private Customer verifyCustomer(Long id) throws CustomerNotFoundException {
    Customer customer = customerRepository.findById(id)
            .orElseThrow(CustomerNotFoundException::new);

    return customer;
  }

  private Order createAndSaveOrder(CreatedOrderDto createdOrderDto) throws CustomerNotFoundException {
    Customer customer = verifyCustomer(createdOrderDto.idCustomer());

    Order order = new Order();
    order.setCompleted(createdOrderDto.completed());
    order.setDate(createdOrderDto.date());
    order.setIdCustomer(customer);

    return orderRepository.save(order);
  }


  public Order updateOrder(long id, CreatedOrderDto order) throws OrderNotFoundException, ProductNotFoundException, CustomerNotFoundException, SavoryNotFoundException {
    Order oldOrder = orderRepository.findById(id)
            .orElseThrow(OrderNotFoundException::new);

    Customer customer = verifyCustomer(order.idCustomer());
    oldOrder.setIdCustomer(customer);
    oldOrder.setDate(order.date());


    orderRepository.save(oldOrder);

    return oldOrder;

  }

  public List<Order> findAllOrder(){
    return orderRepository.findAll();
  }
}
