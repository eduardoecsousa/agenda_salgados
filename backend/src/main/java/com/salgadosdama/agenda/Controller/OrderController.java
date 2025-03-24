package com.salgadosdama.agenda.Controller;

import com.salgadosdama.agenda.Controller.dto.CreatedOrderDto;
import com.salgadosdama.agenda.Controller.dto.OrderDto;
import com.salgadosdama.agenda.models.entity.Order;
import com.salgadosdama.agenda.service.OrderService;
import com.salgadosdama.agenda.service.ProductService;
import com.salgadosdama.agenda.service.exception.CustomerNotFoundException;
import com.salgadosdama.agenda.service.exception.OrderNotFoundException;
import com.salgadosdama.agenda.service.exception.ProductNotFoundException;
import com.salgadosdama.agenda.service.exception.SavoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
  private final OrderService orderService;

  private final ProductService productService;

  public OrderController(OrderService orderService, ProductService productService) {
    this.orderService = orderService;
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<OrderDto> createOrder(@RequestBody CreatedOrderDto createdOrderDto) throws SavoryNotFoundException, CustomerNotFoundException, OrderNotFoundException, ProductNotFoundException {
    return ResponseEntity.status(HttpStatus.CREATED).body(OrderDto.fromEntity(
            orderService.createNewOrder(createdOrderDto)
    ));
  }

  @GetMapping
  public List<OrderDto> findAllOrder(){
    return orderService.findAllOrder()
            .stream()
            .map(OrderDto::fromEntity)
            .toList();
  }

  @GetMapping("/active")
  public List<OrderDto> findAllOrderActive(){
    return orderService.findAllOrderActive()
            .stream()
            .map(OrderDto::fromEntity)
            .toList();
  }

  @PutMapping("/{id}")
  public OrderDto updateOrder(@PathVariable Long id, @RequestBody CreatedOrderDto createdOrderDto) throws OrderNotFoundException, ProductNotFoundException, CustomerNotFoundException, SavoryNotFoundException {
    productService.updateOrAddProductsForOrder(id, createdOrderDto.products());
    return OrderDto.fromEntity(
            orderService.updateOrder(id, createdOrderDto)
    );
  }

  @PutMapping("/complete/{id}")
  public OrderDto completeOrderForId(@PathVariable Long id) throws OrderNotFoundException, ProductNotFoundException, CustomerNotFoundException, SavoryNotFoundException {
    Order order = orderService.orderCompleted(id);
    return OrderDto.fromEntity(
            order
    );
  }

  @DeleteMapping("/{id}")
  public OrderDto deteleOrder(@PathVariable long id) throws OrderNotFoundException {
    return OrderDto.fromEntity(
            orderService.deleteOrderById(id)
    );

  }
}
