package com.arnatovich.goodsportal.security;

import com.arnatovich.goodsportal.data.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
  
  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  public RegistrationController(UserRepository userRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }
  
  @GetMapping
  public String registerForm() {
    return "registration";
  }
  
  @PostMapping
  public String processRegistration(RegistrationForm form) {
    userRepository.save(form.toUser(passwordEncoder));
    return "redirect:/login";
  }
}
