package com.marlonpatrick.tacocloud.order;

import java.util.UUID;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

public interface ReactiveCassandraOrderRepository extends ReactiveCassandraRepository<CassandraOrder, UUID> {

}
