package com.salgadosdama.agenda.service;

import com.salgadosdama.agenda.models.entity.Type;
import com.salgadosdama.agenda.models.repository.TypeRepository;
import com.salgadosdama.agenda.service.exception.TypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServise {
  private final TypeRepository typeRepository;

  @Autowired
  public TypeServise(TypeRepository typeRepository){
    this.typeRepository = typeRepository;
  }

  public Type create(Type type){
    return typeRepository.save(type);
  }

  public List<Type> findAll(){
    return typeRepository.findAll();
  }

  public Type findById(Long id) throws TypeNotFoundException {
    return typeRepository.findById(id).orElseThrow(TypeNotFoundException::new);
  }
}
