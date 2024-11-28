package com.salgadosdama.agenda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "order_products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @ManyToOne
  @JoinColumn(name = "id_order")
  @JsonIgnore
  private Order idOrder;
  @ManyToOne
  @JoinColumn(name = "id_savory")
  private Savory idSavory;
  private int quantity;

  public Product(){

  }
  public Product(Order idOrder, Savory idSavory, int quantity){
    this.idOrder = idOrder;
    this.idSavory = idSavory;
    this.quantity = quantity;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Order getIdOrder() {
    return idOrder;
  }

  public void setIdOrder(Order idOrder) {
    this.idOrder = idOrder;
  }

  public Savory getIdSavory() {
    return idSavory;
  }

  public void setIdSavory(Savory idSavory) {
    this.idSavory = idSavory;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
