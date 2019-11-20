package com.arnatovich.goodsportal;

import lombok.Data;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Design {
  
  private Long id;
  
  private Date createdAt;

  @NotNull
  @Size(min = 2, message = "Name shod be not less then two!")
  private String name;

  @NotNull
  @Size(min = 1, message = "At least one ingredient should be chosen")
  private List<String> ingredients;
}
