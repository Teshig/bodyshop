package com.arnatovich.goodsportal;

import com.arnatovich.goodsportal.data.GoodRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @RestController serves two aims:
 *  - stereotype annotation like @Controller, to been discovered by component scanning;
 *  - tells Spring that all handler methods should have their return value written directly to the body of the response;
 *  As an alternative (to reach the same result) you could annotate class with @Controller and also annotate all handler methods with @ResponseBody.
 */
@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignGoodsControllerRest {
  
  private final GoodRepository goodRepo;

  public DesignGoodsControllerRest(GoodRepository goodRepo) {
    this.goodRepo = goodRepo;
  }
  
  @GetMapping("/recent")
  public Iterable<Good> recentGoods() {
    PageRequest page = PageRequest.of(
        0, 12, Sort.by("createdAt").descending());
    return goodRepo.findAll(page).getContent();
  }
  
  @GetMapping("/{id}")
  public Good goodById(@PathVariable("id") Long id) {
    Optional<Good> optGood = goodRepo.findById(id);
    return optGood.orElse(null);
  }
}