package com.arnatovich.goodsportal;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity allow declare this class as JPA entity.
 * 
 * NoArgsConstructor with private access provide no arg constructor,
 * required for JPA entities. And as there are final properties, that must be set,
 * force = true is set, what result in the Lombok-generated constructor setting them to null.
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Ingredient {

  @Id
  private final String id;
  private final String name;
  private final Type type;
  
  public static enum Type {
    INTELLECT, SKILL, ATTENTION, RESPONSIBILITY
  }
}
