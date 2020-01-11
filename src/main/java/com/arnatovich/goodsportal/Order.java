package com.arnatovich.goodsportal;

import lombok.Data;

import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @Table specifies that Order entity should be persisted to a specific table.
 * As order is a reserved word in SQL, table should be named in another way.
 */
@Data
@Entity
@Table(name = "Good_Order")
public class Order implements Serializable {
  
  private static final long serialVersionUID = 0;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  private Date placedAt;
  
  @ManyToOne
  private User user;
  
  /**
   * Spring supports Java’s Bean Validation API (also known as JSR-303).
   * https://jcp.org/en/jsr/detail?id=303
   * 
   */
  @ManyToMany(targetEntity = Good.class)
  @NotNull
  @Size(min = 1, message = "At least one good should be chosen")
  private List<Good> goods;
  
  @NotBlank(message = "Value is required")
  private String deliveryName;

  @NotBlank(message = "Value is required")
  private String deliveryStreet;

  @NotBlank(message = "Value is required")
  private String deliveryCity;

  @NotBlank(message = "Value is required")
  private String deliveryState;

  @NotBlank(message = "Value is required")
  private String deliveryZip;

//  @CreditCardNumber(message = "Not valid Credit Card number :(")
  private String ccNumber;

  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
      message="Must be formatted MM/YY")
  private String ccExpiration;

  @Digits(integer=3, fraction=0, message="Invalid CVV")
  private String ccCVV;
  
  @PrePersist
  void placedAt() {
    this.placedAt = new Date();
  }

  public void addGood(Good good) {
    if (goods == null) {
      goods = new ArrayList<>();
    }
    goods.add(good);
  }
}
