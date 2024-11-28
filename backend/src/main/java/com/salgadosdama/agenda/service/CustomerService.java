package com.salgadosdama.agenda.service;

import com.salgadosdama.agenda.models.entity.Customer;
import com.salgadosdama.agenda.models.repository.CustomerRepository;
import com.salgadosdama.agenda.service.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Customer createCustomer(Customer customer){
    return customerRepository.save(customer);
  }

  public List<Customer> findAllCustomer(){
    return customerRepository.findAll();
  }

  public Customer findByIdCustomer(Long id) throws CustomerNotFoundException {
    return customerRepository.findById(id)
            .orElseThrow(CustomerNotFoundException::new);
  }

  public Customer deleteCustomerById(Long id) throws CustomerNotFoundException{
    Customer customer = findByIdCustomer(id);

    customerRepository.deleteById(id);

    return customer;
  }

}
