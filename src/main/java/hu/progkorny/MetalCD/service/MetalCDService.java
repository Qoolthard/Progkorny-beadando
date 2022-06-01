package hu.progkorny.MetalCD.service;

import hu.progkorny.MetalCD.model.MetalCD;

import java.util.List;

public interface MetalCDService {

  List<MetalCD> getAllMetalCDs();

  MetalCD getMetalCD(Long id);

  MetalCD createMetalCD(MetalCD metalCD);

  MetalCD updateMetalCD(Long id, MetalCD metalCDChange);

  void deleteMetalCD(Long id);
}
