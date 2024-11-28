package com.salgadosdama.agenda.service;

import com.salgadosdama.agenda.models.entity.Savory;
import com.salgadosdama.agenda.models.entity.Stock;
import com.salgadosdama.agenda.models.entity.Type;
import com.salgadosdama.agenda.models.repository.SavoryRepository;
import com.salgadosdama.agenda.models.repository.StockRepository;
import com.salgadosdama.agenda.service.exception.StockNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
  private final StockRepository stockRepository;
  private final SavoryRepository savoryRepository;

  public StockService(StockRepository stockRepository, SavoryRepository savoryRepository) {
    this.stockRepository = stockRepository;
    this.savoryRepository = savoryRepository;
  }

  public Stock create(Stock stock){
    Savory savory = savoryRepository.findById(stock.getIdSavory().getId())
            .orElseThrow(() -> new IllegalArgumentException("Type not found with id: " + stock.getIdSavory().getId()));

    stock.setIdSavory(savory);
    return stockRepository.save(stock);
  }

  public List<Stock> findAll(){
    return stockRepository.findAll();
  }

  public Stock findById(Long id) throws StockNotFoundException {
    return stockRepository.findById(id).orElseThrow(StockNotFoundException::new);
  }

  public Stock deleteById (Long id) throws StockNotFoundException{
    Stock stock = findById(id);

    stockRepository.deleteById(id);

    return stock;
  }
}
