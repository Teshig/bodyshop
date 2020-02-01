package com.arnatovich.goodsportal;

import lombok.extern.slf4j.Slf4j;

import com.arnatovich.goodsportal.data.OrderRepository;
import com.arnatovich.goodsportal.web.OrderProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
  
  private OrderRepository orderRepo;
  
  private OrderProps props;

  @Autowired
  public OrderController(OrderRepository orderRepo, OrderProps props) {
    this.orderRepo = orderRepo;
    this.props = props;
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
      SessionStatus sessionStatus,
      @AuthenticationPrincipal User user) {
    if (errors.hasErrors()) {
      return "orderForm";
    }
    order.setUser(user);
    orderRepo.save(order);
    sessionStatus.setComplete();
    return "redirect:/";
  }
  
  @GetMapping
  public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
    Pageable pages = PageRequest.of(0, props.getPageSize());
    model.addAttribute("orders", orderRepo.findByUserOrderByPlacedAtDesc(user, pages));
    
    return "orderList";
  }

}
