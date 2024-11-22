package com.salgadosdama.agenda.service.exception;

public class StockNotFoundException extends Exception{
  public StockNotFoundException() {
    super("Stock not found");
  }
}
