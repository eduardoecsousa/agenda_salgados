package com.salgadosdama.agenda.models.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  @ManyToOne
  @JoinColumn(name = "user_type_id")
  private Type userType;
  private String cpf;

  @Column(unique = true)
  private String username;
  private String password;

  public User(){
  }

  public User(String name, Type userType, String cpf, String username, String password){
    this.name = name;
    this.userType = userType;
    this.cpf = cpf;
    this.username = username;
    this.password = password;
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

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
