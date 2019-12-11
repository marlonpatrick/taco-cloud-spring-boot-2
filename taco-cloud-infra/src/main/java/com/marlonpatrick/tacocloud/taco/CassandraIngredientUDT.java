package com.marlonpatrick.tacocloud.taco;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import com.marlonpatrick.tacocloud.taco.IngredientTypes;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@UserDefinedType("ingredient")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CassandraIngredientUDT {

	private final String name;

	private final IngredientTypes type;
}
