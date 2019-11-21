package com.arnatovich.goodsportal.data;

import com.arnatovich.goodsportal.Order;

public interface OrderRepository {

  Order save(Order order);
}
