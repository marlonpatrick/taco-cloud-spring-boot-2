package com.marlonpatrick.tacocloud.order;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.utils.UUIDs;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Table("tacoorders")
public class CassandraOrder implements Serializable{

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	private UUID id = UUIDs.timeBased();

	private ZonedDateTime placedAt;

	@NotBlank(message = "Name is required")
	private String deliveryName;

	@NotBlank(message = "Street is required")
	private String deliveryStreet;

	@NotBlank(message = "City is required")
	private String deliveryCity;

	@NotBlank(message = "State is required")
	private String deliveryState;

	@NotBlank(message = "Zip code is required")
	private String deliveryZip;

	@CreditCardNumber(message = "Not a valid credit card number")
	private String ccNumber;

	@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
	private String ccExpiration;

	@Digits(integer = 3, fraction = 0, message = "Invalid CVV")
	private String ccCVV;

	@Column("tacos")
	@Setter(AccessLevel.NONE)
	private List<CassandraTacoUDT> tacos = new ArrayList<>();
	
	@Column("user")
	private CassandraUserUDT user;

	public void addTaco(CassandraTacoUDT taco) {
		this.tacos.add(taco);
	}
	
	public Order toOrder() {
		return null;
	}
}