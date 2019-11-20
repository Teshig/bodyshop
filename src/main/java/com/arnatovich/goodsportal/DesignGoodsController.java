package com.arnatovich.goodsportal;


import lombok.extern.slf4j.Slf4j;

import com.arnatovich.goodsportal.Ingredient.Type;
import com.arnatovich.goodsportal.data.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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
  
  private final IngredientRepository ingredientRepo;

  @Autowired
  public DesignGoodsController(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

  @GetMapping
  public String showDesignForm(Model model) {
    List<Ingredient> ingredients = getIngredients();

    model.addAttribute("ingredients", ingredients);
    model.addAttribute("design", new Design());
    
    return "design";
  }

  private List<Ingredient> getIngredients() {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(element -> ingredients.add(element));
    return ingredients;
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
