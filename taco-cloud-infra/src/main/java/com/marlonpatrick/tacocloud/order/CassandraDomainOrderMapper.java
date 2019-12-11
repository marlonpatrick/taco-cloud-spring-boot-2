package com.marlonpatrick.tacocloud.order;

import java.util.List;
import java.util.stream.Collectors;

import com.marlonpatrick.tacocloud.taco.CassandraIngredientUDT;
import com.marlonpatrick.tacocloud.taco.Ingredient;
import com.marlonpatrick.tacocloud.taco.Taco;
import com.marlonpatrick.tacocloud.user.User;

class CassandraDomainOrderMapper {
	
	public static CassandraOrder fromDomain(Order order) {
		CassandraOrder cassandraOrder = new CassandraOrder();
		cassandraOrder.setCcCVV(order.getCcCVV());
		cassandraOrder.setCcExpiration(order.getCcExpiration());
		cassandraOrder.setCcNumber(order.getCcNumber());
		cassandraOrder.setDeliveryCity(order.getDeliveryCity());
		cassandraOrder.setDeliveryName(order.getDeliveryName());
		cassandraOrder.setDeliveryState(order.getDeliveryState());
		cassandraOrder.setDeliveryStreet(order.getDeliveryStreet());
		cassandraOrder.setDeliveryZip(order.getDeliveryZip());
		cassandraOrder.setId(order.getId());
		cassandraOrder.setPlacedAt(order.getPlacedAt());
		
		order.getTacos().forEach(t -> cassandraOrder.addTaco(fromDomain(t)));
		
		cassandraOrder.setUser(fromDomain(order.getUser()));
		
		return cassandraOrder;		
	}
	
	private static CassandraTacoUDT fromDomain(Taco taco) {		
		List<CassandraIngredientUDT> cassandraIngredients = taco.getIngredients().stream().map(i -> fromDomain(i)).collect(Collectors.toList());
				
		return new CassandraTacoUDT(taco.getName(), cassandraIngredients);
	}

	private static CassandraIngredientUDT fromDomain(Ingredient ingredient) {
		return new CassandraIngredientUDT(ingredient.getName(), ingredient.getType());
	}

	private static CassandraUserUDT fromDomain(User user) {
		return new CassandraUserUDT(user.getUsername(), user.getFullname(), user.getPhoneNumber());
	}

	public static Order toDomain(CassandraOrder cassandraOrder) {
		Order order = new Order();
		order.setCcCVV(cassandraOrder.getCcCVV());
		order.setCcExpiration(cassandraOrder.getCcExpiration());
		order.setCcNumber(cassandraOrder.getCcNumber());
		order.setCcNumber(cassandraOrder.getCcNumber());
		order.setDeliveryCity(cassandraOrder.getDeliveryCity());
		order.setDeliveryName(cassandraOrder.getDeliveryName());
		order.setDeliveryState(cassandraOrder.getDeliveryState());
		order.setDeliveryStreet(cassandraOrder.getDeliveryStreet());
		order.setDeliveryZip(cassandraOrder.getDeliveryZip());
		order.setId(cassandraOrder.getId());
		order.setPlacedAt(cassandraOrder.getPlacedAt());

		cassandraOrder.getTacos().forEach(t -> order.addTaco(toDomain(t)));

		order.setUser(toDomain(cassandraOrder.getUser()));
				
		return order;
	}

	private static User toDomain(CassandraUserUDT cassandraUser) {
		//TODO: populate all User informations or findById
		return new User(cassandraUser.getUsername(), null, cassandraUser.getFullname(), null, null, null, null, cassandraUser.getPhoneNumber());
	}

	private static Taco toDomain(CassandraTacoUDT cassandraTaco) {
		//TODO: populate all Taco informations or findById
		
		Taco taco = new Taco();
		taco.setName(cassandraTaco.getName());
		
		List<Ingredient> ingredients = cassandraTaco.getIngredients().stream().map(i -> toDomain(i)).collect(Collectors.toList());
		
		taco.setIngredients(ingredients);
		
		return taco;
	}

	private static Ingredient toDomain(CassandraIngredientUDT cassandraIngredient) {
		//TODO: populate all Igredient informations		
		return new Ingredient(null, cassandraIngredient.getName(), cassandraIngredient.getType());
	}
}
