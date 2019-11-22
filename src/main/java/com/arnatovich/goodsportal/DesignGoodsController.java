package com.arnatovich.goodsportal;


import lombok.extern.slf4j.Slf4j;

import com.arnatovich.goodsportal.data.GoodRepository;
import com.arnatovich.goodsportal.data.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

/**
 * Slf4j from Lombok will generate Slf4j at runtime.
 * The same, as if you add by hands 
 * private static final Logger log = LoggerFactory.getLogger(DesignGoodsController.class);
 * 
 * RequestMapping points, that will be handled requests are started with '/design'
 * 
 * SessionAttributes specifies model objects (e.g. order) 
 * that should be kept in session and available across multiple requests.
 */
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignGoodsController {
  
  private final IngredientRepository ingredientRepo;
  
  private final GoodRepository goodRepo;

  @Autowired
  public DesignGoodsController(IngredientRepository ingredientRepo,
      GoodRepository goodRepo) {
    this.ingredientRepo = ingredientRepo;
    this.goodRepo = goodRepo;
  }

  @GetMapping
  public String showDesignForm(Model model) {
    List<Ingredient> ingredients = getIngredients();

    model.addAttribute("ingredients", ingredients);
    model.addAttribute("design", new Good());
    
    return "design";
  }

  private List<Ingredient> getIngredients() {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(ingredients::add);
    return ingredients;
  }

  /**
   * PostMapping allow to handle post requests, started from @RequestMapping("/design")
   * All fields from form will be mapped to properties of a Good object by name. 
   * 
   * @Valid is a Java Bean Validation API’s annotation. Tells Spring to validate parameter.
   *  Any validation errors will be captured in an Errors.
   *  
   *  ModelAttribute indicates that its value should come from the model and that Spring
   *  shouldn't attempt to bind request parameters to it.
   */
  @PostMapping
  public String processDesign(
      @Valid Good design, Errors errors,
      @ModelAttribute Order order) {
    if (errors.hasErrors()) {
      return "design";
    }
    
    Good saved = goodRepo.save(design);
    order.addGood(saved);
    
    return "redirect:/orders/current";
  }
  
  /**
   * ModelAttribute ensures that appropriate object will be created in the model.
   */
  @ModelAttribute
  public Order order() {
    return new Order();
  }
  
  @ModelAttribute
  public Good good() {
    return new Good();
  }
}
