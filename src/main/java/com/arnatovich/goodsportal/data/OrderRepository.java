package com.arnatovich.goodsportal.data;

import com.arnatovich.goodsportal.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * In case extending from CrudRepository Spring Data JPA automatically generates
 * an implementation on the fly. 
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
}
