package com.salgadosdama.agenda.service;

import com.salgadosdama.agenda.models.entity.Savory;
import com.salgadosdama.agenda.models.entity.Stock;
import com.salgadosdama.agenda.models.repository.SavoryRepository;
import com.salgadosdama.agenda.models.repository.StockRepository;
import com.salgadosdama.agenda.service.exception.SavoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavoryService {
  private final SavoryRepository savoryRepository;
  private final StockRepository stockRepository;

  @Autowired
  public SavoryService(SavoryRepository savoryRepository, StockRepository stockRepository) {
    this.savoryRepository = savoryRepository;
    this.stockRepository = stockRepository;
  }

  public Savory create(Savory savory, int quantity){
    Savory newSavory =  savoryRepository.save(savory);
    stockRepository.save(new Stock(newSavory, quantity));
    return newSavory;
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
