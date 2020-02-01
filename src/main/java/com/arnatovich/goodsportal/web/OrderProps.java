package com.arnatovich.goodsportal.web;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bodyshop.orders")
@Data
public class OrderProps {
  
  private int pageSize = 20;

}
