package com.marlonpatrick.tacocloud.order.interfaces.email;

import java.util.List;

import lombok.Data;

@Data
class Taco {

  private final String name;
  
  private List<String> ingredients;
  
}
