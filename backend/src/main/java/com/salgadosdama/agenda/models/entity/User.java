package com.salgadosdama.agenda.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private long userType;
  private int cpf;

  public User(){
  }

  public User(String name, long userType, int cpf){
    this.name = name;
    this.userType = userType;
    this.cpf = cpf;
  }

  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
  }

  public long getUserType(){
    return userType;
  }

  public void setUserType(long userType){
    this.userType = userType;
  }
  public int getCpf(){
    return cpf;
  }

  public void setCpf(int cpf){
    this.cpf = cpf;
  }

}
