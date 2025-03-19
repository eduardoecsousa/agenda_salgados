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

  private Boolean active;

  public Product(){

  }
  public Product(Order idOrder, Savory idSavory, int quantity, Boolean active){
    this.idOrder = idOrder;
    this.idSavory = idSavory;
    this.quantity = quantity;
    this.active = active;
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

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
