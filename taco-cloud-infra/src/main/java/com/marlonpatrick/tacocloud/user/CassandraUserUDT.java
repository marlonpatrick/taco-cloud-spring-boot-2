package com.marlonpatrick.tacocloud.user;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Data;

@Data
@UserDefinedType("user")
public class CassandraUserUDT {

	private final String username;
	private final String fullname;
	private final String phoneNumber;
}
