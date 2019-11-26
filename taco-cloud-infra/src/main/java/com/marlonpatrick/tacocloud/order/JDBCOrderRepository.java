package com.marlonpatrick.tacocloud.order;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marlonpatrick.tacocloud.taco.Taco;

@Repository
@ConditionalOnMissingBean(ImperativeOrderRepositoryGateway.class)
class JDBCOrderRepository implements ImperativeOrderRepositoryGateway {

	private SimpleJdbcInsert orderInserter;

	private SimpleJdbcInsert orderTacoInserter;

	private ObjectMapper objectMapper;

	@Autowired
	public JDBCOrderRepository(JdbcTemplate jdbc) {
		this.orderInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order").usingGeneratedKeyColumns("id");

		this.orderTacoInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order_Tacos");

		this.objectMapper = new ObjectMapper();
	}

	@Override
	public <S extends Order> S save(S order) {
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

	@Override
	public Optional<Order> findById(Long id) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public boolean existsById(Long id) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public Iterable<Order> findAllById(Iterable<Long> ids) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public long count() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public <S extends Order> Iterable<S> saveAll(Iterable<S> entities) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void deleteById(Long id) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void delete(Order entity) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void deleteAll(Iterable<? extends Order> entities) {
		throw new UnsupportedOperationException("Not implemented");
	}
}