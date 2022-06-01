package hu.progkorny.MetalCD.service.impl;

import hu.progkorny.MetalCD.model.Type;
import hu.progkorny.MetalCD.model.Genre;
import hu.progkorny.MetalCD.model.MetalCD;
import hu.progkorny.MetalCD.model.exception.NotFoundException;
import hu.progkorny.MetalCD.service.MetalCDService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetalCDServiceImpl implements MetalCDService {

  private final List<MetalCD> dataBase = new ArrayList<>();

  @Autowired
  public MetalCDServiceImpl() {
    dataBase.add(new MetalCD(1L, "Number of the beast", Genre.HEAVY, Type.MAXI));
    dataBase.add(new MetalCD(2L, "Toxicity", Genre.NUMETAL, Type.MINI));
    dataBase.add(new MetalCD(3L, "Silverside", Genre.CLASSIC, Type.MINI));
    dataBase.add(new MetalCD(4L, "Blonded by", Genre.TRASH, Type.SINGLE));
  }

  public MetalCDServiceImpl(final List<MetalCD> metalCDS) {
    dataBase.addAll(metalCDS);
  }

  @Override
  public List<MetalCD> getAllMetalCDs() {
    return Collections.unmodifiableList(dataBase);
  }

  @Override
  public MetalCD getMetalCD(final Long id) {
    return dataBase.stream()
            .filter(metalCD -> metalCD.getId().equals(id))
            .findFirst()
            .orElseThrow(NotFoundException::new);
  }

  @Override
  public MetalCD createMetalCD(final MetalCD metalCD) {
    metalCD.setId(getNextId());
    dataBase.add(metalCD);
    return metalCD;
  }

  @Override
  public MetalCD updateMetalCD(final Long id, final MetalCD metalCDChange) {
    final MetalCD metalCD = getMetalCD(id);
    metalCD.setName(metalCDChange.getName());
    metalCD.setGenre(metalCDChange.getGenre());
    metalCD.setType(metalCDChange.getType());
    return metalCD;
  }

  @Override
  public void deleteMetalCD(final Long id) {
    final MetalCD metalCD = getMetalCD(id);
    dataBase.remove(metalCD);
  }

  private long getNextId() {
    return getLastId() + 1L;
  }

  private long getLastId() {
    return dataBase.stream()
            .mapToLong(MetalCD::getId)
            .max()
            .orElse(0);
  }
}
