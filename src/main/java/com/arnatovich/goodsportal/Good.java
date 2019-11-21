package com.arnatovich.goodsportal;

import lombok.Data;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Good {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  private Date createdAt;

  @NotNull
  @Size(min = 2, message = "Name shod be not less then two!")
  private String name;

  /**
   * ManyToMany declare relationship between a Good and Ingredients.
   * A Good can have many Ingredients, and an Ingredient can be a 
   * part of many Goods.
   */
  @ManyToMany(targetEntity = Ingredient.class)
  @Size(min = 1, message = "At least one ingredient should be chosen")
  private List<Ingredient> ingredients;
  
  /**
   * Allow to initialize or reset property before Good object will be persisted.
   */
  @PrePersist
  void createdAt() {
    this.createdAt = new Date();
  }
}
