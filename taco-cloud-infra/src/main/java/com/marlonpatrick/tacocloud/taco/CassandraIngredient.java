package com.marlonpatrick.tacocloud.taco;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Table("ingredients")
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CassandraIngredient {

	@PrimaryKey
	private final String id;

	private final String name;

	private final IngredientTypes type;
}
