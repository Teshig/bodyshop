package com.arnatovich.goodsportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for application.
 * SpringBootApplication = SpringBootConfiguration + EnableAutoConfiguration + ComponentScan
 */
@SpringBootApplication
public class GoodsPortalApp {

  public static void main(String[] args) {
    SpringApplication.run(GoodsPortalApp.class, args);
  }

}
