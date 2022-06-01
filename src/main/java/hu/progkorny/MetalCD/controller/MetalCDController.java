package hu.progkorny.MetalCD.controller;

import hu.progkorny.MetalCD.model.MetalCD;
import hu.progkorny.MetalCD.model.exception.NotFoundException;
import hu.progkorny.MetalCD.service.MetalCDService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/metalcd")
public class MetalCDController {

  private static final String METALCD_LIST_TEMPLATE_NAME = "metalcd/list";
  private static final String METALCD_EDIT_TEMPLATE_NAME = "metalcd/edit";
  private static final String METALCD_ATTRIBUTE_NAME = "metalcd";

  private final MetalCDService metalCDService;

  public MetalCDController(final MetalCDService metalCDService) {
    this.metalCDService = metalCDService;
  }

  @GetMapping
  public String getAllMetalCD(final Model model) {
    final List<MetalCD> metalCDS = metalCDService.getAllMetalCDs();
    model.addAttribute("metalcds", metalCDS);
    return METALCD_LIST_TEMPLATE_NAME;
  }

  @GetMapping("/{id}")
  public String getMetalCD(final Model model, final @PathVariable Long id) {
    final MetalCD metalCD = metalCDService.getMetalCD(id);
    model.addAttribute(METALCD_ATTRIBUTE_NAME, metalCD);
    return METALCD_ATTRIBUTE_NAME;
  }


  @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String updateMetalCD(final Model model,
                               final @RequestParam(value = "id", required = false) Long id,
                               final MetalCD metalCDChanges) {
    final MetalCD metalCD = metalCDService.updateMetalCD(id, metalCDChanges);
    model.addAttribute(METALCD_ATTRIBUTE_NAME, metalCD);
    return METALCD_EDIT_TEMPLATE_NAME;
  }

  @GetMapping("/create")
  public String createMetalCDForm(final Model model) {
    return "MetalCD/create";
  }

  @PostMapping("/create")
  public String createMetalCD(final Model model, final MetalCD metalCD) {
    final MetalCD savedMetalCD = metalCDService.createMetalCD(metalCD);
    model.addAttribute(METALCD_ATTRIBUTE_NAME, savedMetalCD);
    return METALCD_EDIT_TEMPLATE_NAME;
  }

  @GetMapping("/{id}/delete")
  public String deleteMetalCD(final Model model, final @PathVariable("id") Long id) {
    try {
      metalCDService.deleteMetalCD(id);
    } catch (NotFoundException e) {
      // Ignored
    }
    final List<MetalCD> metalCDS = metalCDService.getAllMetalCDs();
    model.addAttribute("MetalCDs", metalCDS);
    return METALCD_LIST_TEMPLATE_NAME;
  }
}
