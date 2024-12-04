package com.salgadosdama.agenda.Controller.dto;

import com.salgadosdama.agenda.models.entity.Customer;
import com.salgadosdama.agenda.models.entity.Order;
import com.salgadosdama.agenda.models.entity.Product;

import java.time.LocalDate;
import java.util.List;

public record OrderDto(Long id, Customer customer, LocalDate date, Boolean completed,List<Product> products) {
  public static OrderDto fromEntity(Order order){
    System.out.println(order.getProducts());
    return new OrderDto(
            order.getId(),
            order.getIdCustomer(),
            order.getDate(),
            order.getCompleted(),
            order.getProducts()
    );
  }
}
