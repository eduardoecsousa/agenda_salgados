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

  @OneToMany(mappedBy = "idSavory", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Product> products;

  private Integer quantity;


  public Savory(){

  }

  public Savory(String nameSavory, Integer quantity){
    this.nameSavory = nameSavory;
    this.quantity = quantity;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
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



  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
