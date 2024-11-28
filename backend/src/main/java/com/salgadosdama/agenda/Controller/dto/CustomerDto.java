package com.salgadosdama.agenda.Controller.dto;

import com.salgadosdama.agenda.models.entity.Customer;

public record CustomerDto(String name, String cpf, Long phone) {
  public static CustomerDto fromEntity(Customer customer){
    return new CustomerDto(
            customer.getName(),
            customer.getCpf(),
            customer.getPhone()
    );
  }
}
