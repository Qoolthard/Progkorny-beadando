package hu.progkorny.metalcd.controller;

import java.util.List;

import hu.progkorny.metalcd.model.MetalCD;
import hu.progkorny.metalcd.model.exception.NotFoundException;
import hu.progkorny.metalcd.service.MetalCDService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * CONTROLLER CLASS.
 */
@Controller
@RequestMapping("/metal-cd")
public class MetalCDController {

  private static final String METALCD_LIST_TEMPLATE_NAME = "metalcd/list";
  private static final String METALCD_EDIT_TEMPLATE_NAME = "metalcd/edit";
  private static final String METALCD_ATTRIBUTE_NAME = "metalcd";

  private final MetalCDService metalCDService;

  public MetalCDController(final MetalCDService metalCDService) {
    this.metalCDService = metalCDService;
  }

  /**
   * GET ALL METAL CDS.
   */
  @GetMapping
  public String getAllMetalCD(final Model model) {
    final List<MetalCD> metalCDS = metalCDService.getAllMetalCDs();
    model.addAttribute("metalcds", metalCDS);
    return METALCD_LIST_TEMPLATE_NAME;
  }

  /**
   * GET METAL CD ID.
   */
  @GetMapping("/{id}")
  public String getMetalCD(final Model model, final @PathVariable Long id) {
    final MetalCD metalCD = metalCDService.getMetalCD(id);
    model.addAttribute(METALCD_ATTRIBUTE_NAME, metalCD);
    return METALCD_EDIT_TEMPLATE_NAME;
  }


  /**
   * HOST UPDATE METAL CD.
   */
  @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String updateMetalCD(final Model model,
                               final @RequestParam(value = "id", required = false) Long id,
                               final MetalCD metalCDChanges) {
    final MetalCD metalCD = metalCDService.updateMetalCD(id, metalCDChanges);
    model.addAttribute(METALCD_ATTRIBUTE_NAME, metalCD);
    return METALCD_EDIT_TEMPLATE_NAME;
  }

  /**
   * GET CREATE METAL CD.
   */
  @GetMapping("/create")
  public String createMetalCDForm(final Model model) {
    return "metalcd/create";
  }

  /**
   * POST CREATE METAL CD.
   */
  @PostMapping("/create")
  public String createMetalCD(final Model model, final MetalCD metalCD) {
    final MetalCD savedMetalCD = metalCDService.createMetalCD(metalCD);
    model.addAttribute(METALCD_ATTRIBUTE_NAME, savedMetalCD);
    return METALCD_EDIT_TEMPLATE_NAME;
  }

  /**
   * DELETE METAL CD.
   */
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
