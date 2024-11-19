package com.salgadosdama.agenda.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private long idSavory;
  private int quantity;

  public Stock() {

  }
  public Stock(long idSavory, int quantity) {
    this.idSavory = idSavory;
    this.quantity = quantity;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public long getIdSavory() {
    return idSavory;
  }

  public void setIdSavory(long idSavory) {
    this.idSavory = idSavory;
  }
}
