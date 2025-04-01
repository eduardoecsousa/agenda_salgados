package com.salgadosdama.agenda.Controller;

import com.salgadosdama.agenda.Controller.dto.AuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager){
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/login")
  public String login(@RequestBody AuthDto authDto){
    UsernamePasswordAuthenticationToken usernamePassword =
            new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password());

    Authentication auth = authenticationManager.authenticate(usernamePassword);

    return "Pessoa autorizada com sucesso: %s".formatted(auth.getName());
  }
}
