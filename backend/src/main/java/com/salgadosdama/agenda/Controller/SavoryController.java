package com.salgadosdama.agenda.Controller;

import com.salgadosdama.agenda.Controller.dto.CreateSavoryDto;
import com.salgadosdama.agenda.Controller.dto.SavoryDto;
import com.salgadosdama.agenda.models.entity.Savory;
import com.salgadosdama.agenda.service.SavoryService;
import com.salgadosdama.agenda.service.exception.SavoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import java.util.List;

@RestController
@RequestMapping(value = "/savory")
public class SavoryController {
  private final SavoryService savoryService;

  @Autowired
  public SavoryController(SavoryService savoryService) {
    this.savoryService = savoryService;
  }

  @PostMapping
  public SavoryDto createSavory(@RequestBody CreateSavoryDto createSavoryDto){
    return SavoryDto.fromEntity(
            savoryService.create(createSavoryDto.toEntity(), createSavoryDto.quantity())
    );
  }

  @GetMapping
  public List<SavoryDto> getAllSavory(){
    List<Savory> allSavory = savoryService.findAll();

    return allSavory.stream()
            .map(SavoryDto::fromEntity)
            .toList();
  }

  @GetMapping("/{id}")
  public SavoryDto getSavoryById(@PathVariable Long id) throws SavoryNotFoundException {
    return SavoryDto.fromEntity(
            savoryService.findById(id)
    );
  }

  @DeleteMapping("/{id}")
  public SavoryDto deleteSavory(@PathVariable Long id) throws SavoryNotFoundException{
    return SavoryDto.fromEntity(
            savoryService.deleteById(id)
    );
  }
}
