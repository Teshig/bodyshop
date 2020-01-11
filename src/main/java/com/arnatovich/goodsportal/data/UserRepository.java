package com.arnatovich.goodsportal.data;

import com.arnatovich.goodsportal.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  
  User findByUsername (String username);
}
