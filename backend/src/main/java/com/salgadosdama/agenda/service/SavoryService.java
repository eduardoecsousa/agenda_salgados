package com.salgadosdama.agenda.service;

import com.salgadosdama.agenda.models.entity.Savory;
import com.salgadosdama.agenda.models.repository.SavoryRepository;
import com.salgadosdama.agenda.service.exception.SavoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavoryService {
  private final SavoryRepository savoryRepository;

  @Autowired
  public SavoryService(SavoryRepository savoryRepository) {
    this.savoryRepository = savoryRepository;
  }

  public Savory create(Savory savory){
    return savoryRepository.save(savory);
  }

  public List<Savory> findAll(){
    return savoryRepository.findAll();
  }

  public Savory findById(Long id) throws SavoryNotFoundException {
    return savoryRepository.findById(id).orElseThrow(SavoryNotFoundException::new);
  }

  public Savory deleteById(Long id) throws SavoryNotFoundException{
    Savory savory = findById(id);

    savoryRepository.deleteById(id);

    return savory;
  }
}
