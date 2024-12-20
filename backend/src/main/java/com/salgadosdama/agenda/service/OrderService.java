package com.salgadosdama.agenda.service;

import com.salgadosdama.agenda.Controller.dto.CreateProductDto;
import com.salgadosdama.agenda.Controller.dto.CreatedOrderDto;
import com.salgadosdama.agenda.Controller.dto.ProductDto;
import com.salgadosdama.agenda.models.entity.Customer;
import com.salgadosdama.agenda.models.entity.Order;
import com.salgadosdama.agenda.models.entity.Product;
import com.salgadosdama.agenda.models.entity.Savory;
import com.salgadosdama.agenda.models.repository.*;
import com.salgadosdama.agenda.service.exception.CustomerNotFoundException;
import com.salgadosdama.agenda.service.exception.OrderNotFoundException;
import com.salgadosdama.agenda.service.exception.ProductNotFoundException;
import com.salgadosdama.agenda.service.exception.SavoryNotFoundException;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.OpenDataException;
import java.util.List;

@Service
public class OrderService {
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final SavoryRepository savoryRepository;
  private final CustomerRepository customerRepository;
  private final StockRepository stockRepository;

  @Autowired
  public OrderService(OrderRepository orderRepository,
                      ProductRepository productRepository, SavoryRepository savoryRepository, CustomerRepository customerRepository, StockRepository stockRepository) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.savoryRepository = savoryRepository;
    this.customerRepository = customerRepository;
    this.stockRepository = stockRepository;
  }

  public Order createNewOrder(CreatedOrderDto createdOrderDto) throws SavoryNotFoundException, CustomerNotFoundException, OrderNotFoundException, ProductNotFoundException {
    Order order = createAndSaveOrder(createdOrderDto);

    addProductsToOrder(order, createdOrderDto.products());

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

  private void addProductsToOrder(Order order, List<CreateProductDto> products) throws SavoryNotFoundException, ProductNotFoundException, OrderNotFoundException {
    for (CreateProductDto productDto : products) {
      Savory savory = savoryRepository.findById(productDto.idSavory())
              .orElseThrow(SavoryNotFoundException::new);

      Product product = new Product();
      product.setIdOrder(order);
      product.setIdSavory(savory);
      product.setQuantity(productDto.quantity());
      product.setActive(true);
      productRepository.save(product);
    }
  }

  private void updateProductsToOrder(List<Product> products) throws ProductNotFoundException {
    for (Product product:products){
      Product oldProduct = productRepository.findById(product.getId())
              .orElseThrow(ProductNotFoundException::new);

      oldProduct.setQuantity(product.getQuantity());
      oldProduct.setIdSavory(product.getIdSavory());

      productRepository.save(oldProduct);

    }
  }

  public Order updateOrder(long id, CreatedOrderDto order) throws OrderNotFoundException, ProductNotFoundException, CustomerNotFoundException {
    Order oldOrder = orderRepository.findById(id)
            .orElseThrow(OrderNotFoundException::new);

    List<Product> products = productRepository.findByIdOrderAndActive(oldOrder, true);
    updateProductsToOrder(products);
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
