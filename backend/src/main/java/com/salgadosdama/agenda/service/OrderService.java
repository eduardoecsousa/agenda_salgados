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

  //CONSTRUCTOR
  @Autowired
  public OrderService(OrderRepository orderRepository,
                      SavoryRepository savoryRepository, CustomerRepository customerRepository, StockRepository stockRepository, ProductService productService) {
    this.orderRepository = orderRepository;
    this.savoryRepository = savoryRepository;
    this.customerRepository = customerRepository;
    this.stockRepository = stockRepository;
    this.productService = productService;
  }

  //CRIA NOVA ORDER
  public Order createNewOrder(CreatedOrderDto createdOrderDto) throws SavoryNotFoundException, CustomerNotFoundException, OrderNotFoundException, ProductNotFoundException {
    Order order = createAndSaveOrder(createdOrderDto);

    productService.updateOrAddProductsForOrder(order.getId(), createdOrderDto.products());

    return order;
  }

  //VERIFICA SE O CUSTOMER EXISTE
  private Customer verifyCustomer(Long id) throws CustomerNotFoundException {
    Customer customer = customerRepository.findById(id)
            .orElseThrow(CustomerNotFoundException::new);

    return customer;
  }

  //CRIA E SAVE A ORDER NO BD
  private Order createAndSaveOrder(CreatedOrderDto createdOrderDto) throws CustomerNotFoundException {
    Customer customer = verifyCustomer(createdOrderDto.idCustomer());

    Order order = new Order();
    order.setActive(true);
    order.setDate(createdOrderDto.date());
    order.setIdCustomer(customer);

    return orderRepository.save(order);
  }

  //ATUALIZA ORDER
  public Order updateOrder(long id, CreatedOrderDto order) throws OrderNotFoundException, ProductNotFoundException, CustomerNotFoundException, SavoryNotFoundException {
    Order oldOrder = orderRepository.findById(id)
            .orElseThrow(OrderNotFoundException::new);

    Customer customer = verifyCustomer(order.idCustomer());
    oldOrder.setIdCustomer(customer);
    oldOrder.setDate(order.date());


    orderRepository.save(oldOrder);

    return oldOrder;

  }

  //BUSCA TODOS AS ORDERS
  public List<Order> findAllOrder(){
    return orderRepository.findAll();
  }

  //DELETA ORDER
  public Order deleteOrderById(long id) throws OrderNotFoundException {
    Order order = orderRepository.findById(id)
            .orElseThrow(OrderNotFoundException::new);

    try {
      orderRepository.deleteById(id);
    } catch (Exception e) {
      new Exception("Error delete");
    }

    return order;
  }

  //FINALIZA ORDER
  public Order orderCompleted(long id) throws OrderNotFoundException {
    Order order = orderRepository.findById(id)
            .orElseThrow(OrderNotFoundException::new);

    productService.completedProduct(order);

    order.setActive(false);

    return orderRepository.save(order);


  }
}
