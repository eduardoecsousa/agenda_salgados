package com.salgadosdama.agenda.models.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @ManyToOne
  @JoinColumn(name = "id_customer")
  private Customer idCustomer;
  private LocalDate date;

  @OneToMany(mappedBy = "idOrder", cascade = CascadeType.ALL)
  private List<Product> products;

  private Boolean completed;

  public Order(){

  }

  public Order(Customer idCustomer, LocalDate date, Boolean completed){
    this.idCustomer = idCustomer;
    this.date = date;
    this.completed = completed;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Customer getIdCustomer() {
    return idCustomer;
  }

  public void setIdCustomer(Customer idCustomer) {
    this.idCustomer = idCustomer;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public Boolean getCompleted() {
    return completed;
  }

  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }
}
