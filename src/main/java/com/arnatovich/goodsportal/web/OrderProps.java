package com.arnatovich.goodsportal.web;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Component
@ConfigurationProperties(prefix = "bodyshop.orders")
@Data
@Validated
public class OrderProps {
  
  /**
   * As configuration was extracted to a separate class, it's possible to add validation for this property just in one place.
   */
  @Min(value = 5, message = "should be greater then 5")
  @Max(value = 25, message = "should be lower that 25")
  private int pageSize = 20;

}
