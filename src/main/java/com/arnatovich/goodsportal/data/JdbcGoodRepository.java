package com.arnatovich.goodsportal.data;

import com.arnatovich.goodsportal.Good;
import com.arnatovich.goodsportal.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Repository
public class JdbcGoodRepository implements GoodRepository {

  private JdbcTemplate jdbc;

  @Autowired
  public JdbcGoodRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Override
  public Good save(Good good) {
    long goodId = saveGoodInfo(good);
    good.setId(goodId);
    
    for (String ingredient : good.getIngredients()) {
      saveIngredientToGood(ingredient, goodId);
    }
    return good;
  }
  
  private long saveGoodInfo(Good good) {
    good.setCreatedAt(new Date());

    PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
        "INSERT INTO Good (name, createdAt) VALUES (?, ?)", Types.VARCHAR, Types.TIMESTAMP);
    PreparedStatementCreator psc = preparedStatementCreatorFactory
        .newPreparedStatementCreator(Arrays.asList(good.getName(), new Timestamp(good.getCreatedAt().getTime())));

    // By default, returnGeneratedKeys = false so change it to true
    preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
    
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbc.update(psc, keyHolder);
    
    return Objects.requireNonNull(keyHolder.getKey()).longValue();
  }
  
  private void saveIngredientToGood(String ingredient, long goodId) {
    jdbc.update("INSERT INTO Good_Ingredients (good, ingredient) VALUES (?, ?)", goodId, ingredient);
  }
  
}
