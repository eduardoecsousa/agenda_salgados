package com.salgadosdama.agenda.Controller;

import com.salgadosdama.agenda.Controller.dto.CreateStockDto;
import com.salgadosdama.agenda.Controller.dto.StockDto;
import com.salgadosdama.agenda.models.entity.Stock;
import com.salgadosdama.agenda.service.StockService;
import com.salgadosdama.agenda.service.exception.StockNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/stock")
public class StockController {
  private final StockService stockService;

  @Autowired
  public StockController(StockService stockService) {
    this.stockService = stockService;
  }

  @PostMapping
  public StockDto createStock(@RequestBody CreateStockDto createStockDto){
    return StockDto.fromEntity(
            stockService.create(createStockDto.toEntity())
    );
  }

  @GetMapping
  public List<StockDto> getAllStock(){
    List<Stock> allStock = stockService.findAll();

    return allStock.stream()
            .map(StockDto::fromEntity)
            .toList();
  }

  @GetMapping("/{id}")
  public StockDto getStockById(@PathVariable Long id) throws StockNotFoundException {
    return StockDto.fromEntity(
            stockService.findById(id)
    );
  }

  @DeleteMapping("/{id}")
  public StockDto deleteById(@PathVariable Long id) throws StockNotFoundException {
    return StockDto.fromEntity(
            stockService.deleteById(id)
    );
  }
}
