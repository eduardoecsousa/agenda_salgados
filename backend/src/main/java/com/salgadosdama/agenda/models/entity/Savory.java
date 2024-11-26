package com.salgadosdama.agenda.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "types_savory")
public class Savory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String nameSavory;

  @OneToOne(mappedBy = "idSavory", cascade = CascadeType.ALL)
  private Stock stock;

  @OneToMany(mappedBy = "idSavory", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Product> products;


  public Savory(){

  }

  public Savory(String nameSavory){
    this.nameSavory = nameSavory;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNameSavory() {
    return nameSavory;
  }

  public void setNameSavory(String nameSavory) {
    this.nameSavory = nameSavory;
  }

  public Stock getStock() {
    return stock;
  }

  public void setStock(Stock stock) {
    this.stock = stock;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
