package com.arnatovich.goodsportal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * JUnit RunWith expect actual runner.
 * 
 * SpringBootTest tells JUnit to bootstrap the test with Spring Boot capabilities. 
 * Almost the same, as SpringApplication.run() in the main method.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsPortalAppTest {
  
  /**
   * Even empty method shows, that app context can be loaded successfully
   */
  @Test
  public void contextLoads(){}

}
