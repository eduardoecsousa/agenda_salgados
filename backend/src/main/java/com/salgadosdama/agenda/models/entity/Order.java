package com.salgadosdama.agenda.models.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private long idCustomer;
  private LocalDate date;

  public Order(){

  }

  public Order(long idCustomer, LocalDate date){
    this.idCustomer = idCustomer;
    this.date = date;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public long getIdCustomer() {
    return idCustomer;
  }

  public void setIdCustomer(long idCustomer) {
    this.idCustomer = idCustomer;
  }
}
