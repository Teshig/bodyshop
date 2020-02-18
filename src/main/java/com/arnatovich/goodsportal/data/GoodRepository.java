package com.arnatovich.goodsportal.data;

import com.arnatovich.goodsportal.Good;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GoodRepository extends PagingAndSortingRepository<Good, Long> {
}
