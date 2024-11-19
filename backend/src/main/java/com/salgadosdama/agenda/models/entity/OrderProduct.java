package com.salgadosdama.agenda.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_products")
public class OrderProduct {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private long idOrder;
  private long idSavory;
  private int quantity;

  public OrderProduct(){

  }
  public OrderProduct(long idOrder, long idSavory, int quantity){
    this.idOrder = idOrder;
    this.idSavory = idSavory;
    this.quantity = quantity;
  }
}
