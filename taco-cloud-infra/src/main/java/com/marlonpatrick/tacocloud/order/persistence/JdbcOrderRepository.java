package com.marlonpatrick.tacocloud.order.persistence;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marlonpatrick.tacocloud.order.domain.Order;
import com.marlonpatrick.tacocloud.order.domain.OrderRepository;
import com.marlonpatrick.tacocloud.taco.domain.Taco;

@Repository
class JdbcOrderRepository implements OrderRepository {

	private SimpleJdbcInsert orderInserter;

	private SimpleJdbcInsert orderTacoInserter;

	private ObjectMapper objectMapper;

	@Autowired
	public JdbcOrderRepository(JdbcTemplate jdbc) {
		this.orderInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order").usingGeneratedKeyColumns("id");

		this.orderTacoInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order_Tacos");

		this.objectMapper = new ObjectMapper();
	}

	public Order save(Order order) {

		order.setPlacedAt(ZonedDateTime.now());

		long orderId = saveOrderDetails(order);

		order.setId(orderId);

		List<Taco> tacos = order.getTacos();

		for (Taco taco : tacos) {
			saveTacoToOrder(taco, orderId);
		}

		return order;
	}

	@SuppressWarnings("unchecked")
	private long saveOrderDetails(Order order) {
		
		Map<String, Object> values = this.objectMapper.convertValue(order, Map.class);
		
		values.put("placedAt", Timestamp.from(order.getPlacedAt().toInstant()));

		long orderId = orderInserter.executeAndReturnKey(values).longValue();

		return orderId;
	}

	private void saveTacoToOrder(Taco taco, long orderId) {
		Map<String, Object> values = new HashMap<>();
		values.put("tacoOrder", orderId);
		values.put("taco", taco.getId());
		orderTacoInserter.execute(values);
	}

}