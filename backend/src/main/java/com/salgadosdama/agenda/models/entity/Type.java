package com.salgadosdama.agenda.models.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "types")
public class Type {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String nameType;

  public Type(){

  }

  public Type(String nameType){
    this.nameType = nameType;
  }

  public String getNameType() {
    return nameType;
  }

  public void setNameType(String nameType) {
    this.nameType = nameType;
  }
}
