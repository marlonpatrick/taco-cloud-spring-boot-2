package com.marlonpatrick.tacocloud.order;

import java.util.List;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import com.marlonpatrick.tacocloud.taco.CassandraIngredientUDT;

import lombok.Data;

@Data
@UserDefinedType("taco")
class CassandraTacoUDT {

	private final String name;
	
	private final List<CassandraIngredientUDT> ingredients;
}
