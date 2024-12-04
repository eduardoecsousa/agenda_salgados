package com.salgadosdama.agenda.Controller.dto;

import com.salgadosdama.agenda.models.entity.Customer;
import com.salgadosdama.agenda.models.entity.Order;
import com.salgadosdama.agenda.models.entity.Product;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDate;
import java.util.List;

public record CreatedOrderDto(Customer idCustomer, LocalDate date, Boolean completed,List<Product> products) {

  public Order toEntity(){
    return new Order(idCustomer, date, completed);
  }
}
