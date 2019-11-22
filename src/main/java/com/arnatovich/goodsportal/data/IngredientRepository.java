package com.arnatovich.goodsportal.data;

import com.arnatovich.goodsportal.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
