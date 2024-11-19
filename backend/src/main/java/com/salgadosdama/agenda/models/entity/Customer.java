package com.salgadosdama.agenda.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private int cpf;
  private int phone;

  public Customer(){

  }

  public Customer(String name, int cpf, int phone){
    this.name = name;
    this.cpf = cpf;
    this.phone = phone;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCpf() {
    return cpf;
  }

  public void setCpf(int cpf) {
    this.cpf = cpf;
  }

  public int getPhone() {
    return phone;
  }

  public void setPhone(int phone) {
    this.phone = phone;
  }
}
