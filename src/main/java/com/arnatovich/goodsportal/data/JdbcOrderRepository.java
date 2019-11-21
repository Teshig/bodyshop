package com.arnatovich.goodsportal.data;

import com.arnatovich.goodsportal.Good;
import com.arnatovich.goodsportal.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {
  
  private SimpleJdbcInsert orderInsert;
  private SimpleJdbcInsert orderGoodInsert;
  private ObjectMapper objectMapper;

  @Autowired
  public JdbcOrderRepository(JdbcTemplate jdbc) {
    this.orderInsert = new SimpleJdbcInsert(jdbc)
        .withTableName("Good_Order")
        .usingGeneratedKeyColumns("id");
    
    this.orderGoodInsert = new SimpleJdbcInsert(jdbc)
        .withTableName("Good_Order_Goods");
    
    this.objectMapper = new ObjectMapper();
  }

  @Override
  public Order save(Order order) {
    order.setPlacedAt(new Date());
    long orderId = saveOrderDetail(order);
    order.setId(orderId);
    
    List<Good> goods = order.getGoods();
    for (Good good : goods) {
      saveGoodToOrder(good, orderId);
    }
    return order;
  }

  private long saveOrderDetail(Order order) {
    Map<String, Object> values = objectMapper.convertValue(order, Map.class);
    values.put("placedAt", order.getPlacedAt());
    
    long orderId = orderInsert.executeAndReturnKey(values).longValue();
    return orderId;
  }

  private void saveGoodToOrder(Good good, long orderId) {
    Map<String, Object> values = new HashMap<>();
    values.put("goodOrder", orderId);
    values.put("good", good.getId());
    orderGoodInsert.execute(values);
  }
}
