package com.marlonpatrick.tacocloud.taco;

import java.util.List;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Data;

@Data
@UserDefinedType("taco")
public class CassandraTacoUDT {

	private final String name;
	
	private final List<CassandraIngredientUDT> ingredients;
}
