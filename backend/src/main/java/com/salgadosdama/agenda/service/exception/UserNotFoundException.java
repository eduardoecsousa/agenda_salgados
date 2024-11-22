package com.salgadosdama.agenda.service.exception;

public class UserNotFoundException extends Exception{
  public UserNotFoundException(){
    super("User not found!");
  }
}
