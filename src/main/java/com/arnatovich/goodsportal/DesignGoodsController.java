package com.arnatovich.goodsportal;


import lombok.extern.slf4j.Slf4j;

import com.arnatovich.goodsportal.Ingredient.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;

/**
 * Slf4j from Lombok will generate Slf4j at runtime.
 * The same, as if you add by hands 
 * private static final Logger log = LoggerFactory.getLogger(DesignGoodsController.class);
 * 
 * RequestMapping points, that will be handled requests are started with '/design'
 */
@Slf4j
@Controller
@RequestMapping("/design")
public class DesignGoodsController {

  @GetMapping
  public String showDesignForm(Model model) {
    List<Ingredient> ingredients = getIngredients();

    model.addAttribute("ingredients", ingredients);
    model.addAttribute("design", new Design());
    
    return "design";
  }

  private List<Ingredient> getIngredients() {
    return Arrays.asList(
          // INTELLECT, SKILL, ATTENTION, RESPONSIBILITY
          new Ingredient("0", "intellect", Type.INTELLECT),
          new Ingredient("1", "skill", Type.SKILL),
          new Ingredient("2", "attention", Type.ATTENTION),
          new Ingredient("3", "responsibility", Type.RESPONSIBILITY)
      );
  }

  /**
   * PostMapping allow to handle post requests, started from @RequestMapping("/design")
   * All fields from form will be mapped to properties of a Good object by name. 
   * 
   * @Valid is a Java Bean Validation API’s annotation. Tells Spring to validate parameter.
   *  Any validation errors will be captured in an Errors.
   */
  @PostMapping
  public String processDesign(@Valid Design design, Errors errors) {
    log.info("Processing : " + design);
    
    return "redirect:/orders/current";
  }
}
