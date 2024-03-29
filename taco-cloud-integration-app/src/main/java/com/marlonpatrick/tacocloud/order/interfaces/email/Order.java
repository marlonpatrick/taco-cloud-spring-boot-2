package com.marlonpatrick.tacocloud.order.interfaces.email;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
class Order {

	private final String email;
	
	private List<Taco> tacos = new ArrayList<>();

	public void addTaco(Taco taco) {
		this.tacos.add(taco);
	}
}
