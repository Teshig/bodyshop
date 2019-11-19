package com.arnatovich.goodsportal;

import lombok.Data;

import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class Order {
  
  /**
   * Spring supports Java’s Bean Validation API (also known as JSR-303).
   * https://jcp.org/en/jsr/detail?id=303
   * 
   */
  @NotBlank(message = "Value is required")
  private String name;

  @NotBlank(message = "Value is required")
  private String street;

  @NotBlank(message = "Value is required")
  private String city;

  @NotBlank(message = "Value is required")
  private String state;

  @NotBlank(message = "Value is required")
  private String zip;

  @CreditCardNumber(message = "Not valid Credit Card number :(")
  private String ccNumber;

  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
      message="Must be formatted MM/YY")
  private String ccExpiration;

  @Digits(integer=3, fraction=0, message="Invalid CVV")
  private String ccCVV;
}
