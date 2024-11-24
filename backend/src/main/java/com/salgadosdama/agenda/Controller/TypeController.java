package com.salgadosdama.agenda.Controller;

import com.salgadosdama.agenda.Controller.dto.CreatedTypeDto;
import com.salgadosdama.agenda.Controller.dto.TypeDto;
import com.salgadosdama.agenda.models.entity.Type;
import com.salgadosdama.agenda.service.TypeServise;
import com.salgadosdama.agenda.service.exception.TypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/types")
public class TypeController {

  final TypeServise typeServise;

  @Autowired
  public TypeController(TypeServise typeServise) {
    this.typeServise = typeServise;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TypeDto createType(@RequestBody CreatedTypeDto createdTypeDto){
    return TypeDto.fromEntity(
      typeServise.create(createdTypeDto.toEntity())
    );
  }

  @GetMapping
  public List<TypeDto> getAllTypes(){
    List<Type> allTypes = typeServise.findAll();
    return allTypes.stream()
            .map(TypeDto::fromEntity)
            .toList();
  }

  @GetMapping("/{id}")
  public TypeDto getTypeId(@PathVariable Long id) throws TypeNotFoundException {
    return TypeDto.fromEntity(
            typeServise.findById(id)
    );
  }

  @DeleteMapping("/{id}")
  public TypeDto deleteTypeById(@PathVariable Long id) throws TypeNotFoundException {
    return TypeDto.fromEntity(
            typeServise.deleteTypeById(id)
    );
  }
}
