package com.salgadosdama.agenda.Controller;

import com.salgadosdama.agenda.Controller.dto.CreatingCustomerDto;
import com.salgadosdama.agenda.Controller.dto.CustomerDto;
import com.salgadosdama.agenda.service.CustomerService;
import com.salgadosdama.agenda.service.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping
  public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreatingCustomerDto creatingCustomerDto){
    return ResponseEntity.status(HttpStatus.CREATED).body(CustomerDto.fromEntity(
            customerService.createCustomer(creatingCustomerDto.toEntity())
    ));
  }

  @GetMapping
  public List<CustomerDto> findAll(){
    return customerService.findAllCustomer()
            .stream()
            .map(CustomerDto::fromEntity)
            .toList();
  }

  @GetMapping("/{id}")
  public CustomerDto findById(@PathVariable Long id) throws CustomerNotFoundException {
    return CustomerDto.fromEntity(
            customerService.findByIdCustomer(id)
    );
  }

  @DeleteMapping("{id}")
  public CustomerDto deleteById(@PathVariable Long id) throws CustomerNotFoundException{
    return CustomerDto.fromEntity(
            customerService.deleteCustomerById(id)
    );
  }
}
