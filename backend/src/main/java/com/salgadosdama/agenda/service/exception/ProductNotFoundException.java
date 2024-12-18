package com.salgadosdama.agenda.service.exception;

public class ProductNotFoundException extends Exception{
  public ProductNotFoundException() {
    super("Product not found!!");
  }
}
