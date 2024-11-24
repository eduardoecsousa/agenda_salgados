package com.salgadosdama.agenda.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  @ManyToOne
  @JoinColumn(name = "user_type_id")
  private Type userType;
  private String cpf;

  public User(){
  }

  public User(String name, Type userType, String cpf){
    this.name = name;
    this.userType = userType;
    this.cpf = cpf;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
  }

  public Type getUserType(){
    return userType;
  }

  public void setUserType(Type userType){
    this.userType = userType;
  }
  public String getCpf(){
    return cpf;
  }

  public void setCpf(String cpf){
    this.cpf = cpf;
  }

}
