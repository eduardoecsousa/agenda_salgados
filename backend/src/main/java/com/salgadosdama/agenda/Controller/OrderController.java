package com.salgadosdama.agenda.Controller;

import com.salgadosdama.agenda.Controller.dto.CreatedOrderDto;
import com.salgadosdama.agenda.Controller.dto.OrderDto;
import com.salgadosdama.agenda.models.entity.Order;
import com.salgadosdama.agenda.service.OrderService;
import com.salgadosdama.agenda.service.exception.CustomerNotFoundException;
import com.salgadosdama.agenda.service.exception.OrderNotFoundException;
import com.salgadosdama.agenda.service.exception.SavoryNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public OrderDto createOrder(@RequestBody CreatedOrderDto createdOrderDto) throws SavoryNotFoundException, CustomerNotFoundException, OrderNotFoundException {
    return OrderDto.fromEntity(
            orderService.createNewOrder(createdOrderDto)
    );
  }

  @GetMapping
  public List<OrderDto> findAllOrder(){
    return orderService.findAllOrder()
            .stream()
            .map(OrderDto::fromEntity)
            .toList();
  }
}
