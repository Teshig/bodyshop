package com.arnatovich.goodsportal.data;

import com.arnatovich.goodsportal.Good;
import org.springframework.data.repository.CrudRepository;

public interface GoodRepository extends CrudRepository<Good, Long> {
}
