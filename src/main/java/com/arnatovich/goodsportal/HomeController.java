package com.arnatovich.goodsportal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller allow to Spring's component scanning automatically discover it.
 */
@Controller
public class HomeController {
  
  /**
   * Handle HTTP GET requests for root path "/". 
   */
  @GetMapping("/")
  public String home() {
    return "home";
  }

}
