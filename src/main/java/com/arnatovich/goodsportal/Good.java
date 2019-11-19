package com.arnatovich.goodsportal;

import lombok.Data;

import java.util.List;

@Data
public class Good {

  private String name;
  private List<String> ingredients;
}
