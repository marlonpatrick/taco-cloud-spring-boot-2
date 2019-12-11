package com.marlonpatrick.tacocloud.order;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Data;

@Data
@UserDefinedType("user")
class CassandraUserUDT {

	private final String username;
	private final String fullname;
	private final String phoneNumber;
}
