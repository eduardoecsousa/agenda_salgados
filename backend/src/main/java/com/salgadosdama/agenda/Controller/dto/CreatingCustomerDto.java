package com.salgadosdama.agenda.Controller.dto;

import com.salgadosdama.agenda.models.entity.Customer;

public record CreatingCustomerDto(String name, String cpf, Long phone) {
  public Customer toEntity(){
    return new Customer(name, cpf, phone);
  }
}
