package com.salgadosdama.agenda.service;

import com.salgadosdama.agenda.models.entity.Savory;
import com.salgadosdama.agenda.models.entity.Stock;
import com.salgadosdama.agenda.models.repository.StockRepository;
import com.salgadosdama.agenda.service.exception.StockNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
  private final StockRepository stockRepository;

  public StockService(StockRepository stockRepository) {
    this.stockRepository = stockRepository;
  }

  public Stock create(Stock stock){
    return stockRepository.save(stock);
  }

  public List<Stock> findAll(){
    return stockRepository.findAll();
  }

  public Stock findById(Long id) throws StockNotFoundException {
    return stockRepository.findById(id).orElseThrow(StockNotFoundException::new);
  }
}
