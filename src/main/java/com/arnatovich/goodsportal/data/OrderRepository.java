package com.arnatovich.goodsportal.data;

import com.arnatovich.goodsportal.Order;
import com.arnatovich.goodsportal.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * In case extending from CrudRepository Spring Data JPA automatically generates
 * an implementation on the fly. 
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
  
  /**
   * Spring Data understand, that you are trying to find Order, because of
   * parameterized CrudRepository with 'Order'. 
   */
  List<Order> findByDeliveryZip(String deliveryZip);
  
  /**
   * More complicated DSL-query. 
   * - find (read, get) - try extract data;
   * - Orders - optional name and could be skipped;
   * - By - signifies the start of properties to match;
   * - DeliveryZip - match .deliveryZip or .delivery.zip property;
   * - And - additional constraint;
   * - PlacedAt - match .placedAt or .placed.at property;
   * - Between - the value must fall between the given values;
   */
  List<Order> findOrdersByDeliveryZipAndPlacedAtBetween(
      String deliveryZip, Date startDate, Date endDate);
  
  /**
   * If you need use more complex query, @Query could be used,
   * which explicitly specify the query to be performed.
   * 
   * Method's name doesn't matter.
   */
  @Query("SELECT o FROM Order o WHERE o.deliveryCity='Seattle'")
  List<Order> readOrdersDeliveredInSeattle();

  List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pages);
}
