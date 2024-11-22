package com.salgadosdama.agenda.service.exception;

public class SavoryNotFoundException extends Exception{
  public SavoryNotFoundException() {
    super("Savory not found!");
  }
}
