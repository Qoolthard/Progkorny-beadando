package hu.progkorny.MetalCD.controller;

import hu.progkorny.MetalCD.model.MetalCD;
import hu.progkorny.MetalCD.model.exception.NotFoundException;
import hu.progkorny.MetalCD.service.MetalCDService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/metalcd")
public class MetalCDRestController {

  private final MetalCDService metalCDService;

  public MetalCDRestController(final MetalCDService metalCDService) {
    this.metalCDService = metalCDService;
  }

  @GetMapping
  public List<MetalCD> getAllMetalCDs() {
    return metalCDService.getAllMetalCDs();
  }

  @GetMapping("/{id}")
  MetalCD getMetalCD(final @PathVariable Long id) {
    return metalCDService.getMetalCD(id);
  }

  @PostMapping
  MetalCD createMetalCD(final @RequestBody MetalCD metalCD) {
    return metalCDService.createMetalCD(metalCD);
  }

  @PutMapping("/{id}")
  MetalCD updateMetalCD(final @PathVariable Long id, final @RequestBody MetalCD metalCDChange) {
    return metalCDService.updateMetalCD(id, metalCDChange);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteMetalCD(final @PathVariable Long id) {
    try {
      metalCDService.deleteMetalCD(id);
    } catch (NotFoundException e) {
      // Ignored
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
