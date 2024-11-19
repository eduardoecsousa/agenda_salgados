package com.salgadosdama.agenda.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "types_savory")
public class TypeSavory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String nameSavory;

  public TypeSavory(){

  }

  public TypeSavory(String nameSavory){
    this.nameSavory = nameSavory;
  }

  public String getNameSavory() {
    return nameSavory;
  }

  public void setNameSavory(String nameSavory) {
    this.nameSavory = nameSavory;
  }
}
