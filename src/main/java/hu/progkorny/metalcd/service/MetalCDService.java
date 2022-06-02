package hu.progkorny.metalcd.service;

import java.util.List;

import hu.progkorny.metalcd.model.MetalCD;

/**
 * SERVICE INTERFACE FOR METALCD.
 */
public interface MetalCDService {

  List<MetalCD> getAllMetalCDs();

  MetalCD getMetalCD(Long id);

  MetalCD createMetalCD(MetalCD metalCD);

  MetalCD updateMetalCD(Long id, MetalCD metalCDChange);

  void deleteMetalCD(Long id);
}
