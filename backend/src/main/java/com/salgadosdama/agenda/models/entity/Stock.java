package com.salgadosdama.agenda.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @OneToOne(optional = false)
  @JoinColumn(name = "id_savory")
  private Savory idSavory;
  private int quantity;

  public Stock() {

  }
  public Stock(Savory idSavory, int quantity) {
    this.idSavory = idSavory;
    this.quantity = quantity;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Savory getIdSavory() {
    return idSavory;
  }

  public void setIdSavory(Savory idSavory) {
    this.idSavory = idSavory;
  }
}
