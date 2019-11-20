package com.arnatovich.goodsportal.data;

import com.arnatovich.goodsportal.Ingredient;

public interface IngredientRepository {
  Iterable<Ingredient> findAll();
  
  Ingredient findById(String id);
  
  Ingredient save(Ingredient ingredient);
}
