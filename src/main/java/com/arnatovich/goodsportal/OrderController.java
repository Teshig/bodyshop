package com.arnatovich.goodsportal;

import lombok.extern.slf4j.Slf4j;

import com.arnatovich.goodsportal.data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
  
  private OrderRepository orderRepo;

  @Autowired
  public OrderController(OrderRepository orderRepo) {
    this.orderRepo = orderRepo;
  }

  @GetMapping("/current")
  public String orderForm() {
    return "orderForm";
  }
  
  /**
   * SessionStatus allow to reset the session and refresh session's attributes like 'order'.
   * In opposite case the 'order' remains in the sessions, including all associated goods.
   */
  @PostMapping
  public String processOrder(
      @Valid Order order, Errors errors,
      SessionStatus sessionStatus) {
    if (errors.hasErrors()) {
      return "orderForm";
    }
    orderRepo.save(order);
    sessionStatus.setComplete();
    return "redirect:/";
  }
}
