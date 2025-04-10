package com.salgadosdama.agenda.security.security;

public enum Role {
  ADMIN("ADMIN"),
  MANAGER("MANAGER"),
  USER("USER");

  private final String name;

  Role(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
