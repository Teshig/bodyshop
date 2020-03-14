package com.arnatovich.goodsportal;

import com.arnatovich.goodsportal.data.GoodRepository;
import com.arnatovich.goodsportal.jms.JmsFeatureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
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
  private final RestTemplate restTemplate;
  private final JmsFeatureService jmsService;

  public DesignGoodsControllerRest(GoodRepository goodRepo, RestTemplate restTemplate, JmsFeatureService jmsService) {
    this.goodRepo = goodRepo;
    this.restTemplate = restTemplate;
    this.jmsService = jmsService;
  }
  
  @GetMapping("/recent")
  public Feature[] recentGoods() {
    ResponseEntity<Feature[]> entity = restTemplate.getForEntity("http://localhost:8887/features/all", Feature[].class);
    jmsService.sendFeature(Objects.requireNonNull(entity.getBody())[0]);
    return entity.getBody();
  }
  
  @GetMapping("/{id}")
  public Good goodById(@PathVariable("id") Long id) {
    Optional<Good> optGood = goodRepo.findById(id);
    return optGood.orElse(null);
  }
}