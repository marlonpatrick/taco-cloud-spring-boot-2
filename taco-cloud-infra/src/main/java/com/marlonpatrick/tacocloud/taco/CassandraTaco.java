package com.marlonpatrick.tacocloud.taco;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.utils.UUIDs;

import lombok.Data;

@Data
@Table("tacos")
public class CassandraTaco implements Serializable{

	private static final long serialVersionUID = 1L;

	@PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
	private UUID id = UUIDs.timeBased();

	@PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
	private ZonedDateTime createdAt;

	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;

	@Column("ingredients")
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	private List<CassandraIngredientUDT> ingredients;
}
