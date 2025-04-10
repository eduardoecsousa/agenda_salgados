package com.salgadosdama.agenda.Controller;

import com.salgadosdama.agenda.Controller.dto.CreateUserDto;
import com.salgadosdama.agenda.Controller.dto.UserDto;
import com.salgadosdama.agenda.models.entity.User;
import com.salgadosdama.agenda.service.UserService;
import com.salgadosdama.agenda.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public UserDto createUser(@RequestBody CreateUserDto user){
    return UserDto.fromEntity(
            userService.create(user.toEntity())
    );
  }

  @GetMapping
  public List<UserDto> getAllUsers(){
    List<User> allUsers = userService.findAll();
    return allUsers.stream()
            .map(UserDto::fromEntity)
            .toList();
  }

  @GetMapping("/{id}")
  public UserDto getUserById(@PathVariable Long id) throws UserNotFoundException {
    return UserDto.fromEntity(
            userService.findById(id)
    );
  }

  @DeleteMapping("/{id}")
  public UserDto deleteById(@PathVariable Long id) throws UserNotFoundException{
    return UserDto.fromEntity(
            userService.deleteById(id)
    );
  }
}
