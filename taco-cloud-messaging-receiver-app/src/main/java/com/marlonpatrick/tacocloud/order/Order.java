package com.marlonpatrick.tacocloud.order;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.marlonpatrick.tacocloud.taco.Taco;

import lombok.Data;

@Data
public class Order {

  private ZonedDateTime placedAt;
  private String deliveryName;
  private String deliveryStreet;
  private String deliveryCity;
  private String deliveryState;
  private String deliveryZip;

  private List<Taco> tacos = new ArrayList<>();

}
