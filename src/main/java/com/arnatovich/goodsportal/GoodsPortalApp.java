package com.arnatovich.goodsportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Entry point for application.
 * SpringBootApplication = SpringBootConfiguration + EnableAutoConfiguration + ComponentScan
 * 
 * WebMvcConfigurer allow to get rid from simple controllers via overriding addViewControllers
 */
@SpringBootApplication
public class GoodsPortalApp implements WebMvcConfigurer {

  public static void main(String[] args) {
    SpringApplication.run(GoodsPortalApp.class, args);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("home");
    registry.addViewController("login");
  }
}
