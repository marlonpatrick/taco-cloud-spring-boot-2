package com.marlonpatrick.tacocloud.taco;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.Data;

@Data
public class Taco {

  private String name;
  
  private ZonedDateTime createdAt;

  private List<Ingredient> ingredients;

}
