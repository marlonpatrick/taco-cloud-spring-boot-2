package com.marlonpatrick.tacocloud.taco;

import java.sql.Timestamp;
import java.sql.Types;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnMissingBean(ImperativeTacoRepositoryGateway.class)
class JDBCTacoRepository implements ImperativeTacoRepositoryGateway {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCTacoRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public <S extends Taco> S save(S taco) {
		long tacoId = saveTacoInfo(taco);

		taco.setId(tacoId);

		for (Ingredient ingredient : taco.getIngredients()) {
			saveIngredientToTaco(ingredient, tacoId);
		}

		return taco;
	}

	private long saveTacoInfo(Taco taco) {
		taco.setCreatedAt(ZonedDateTime.now());

		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
				"insert into Taco (name, createdAt) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP_WITH_TIMEZONE);
		
		pscf.setReturnGeneratedKeys(Boolean.TRUE);
		
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
								Arrays.asList(taco.getName(), Timestamp.from(taco.getCreatedAt().toInstant())));
		
		KeyHolder keyHolder = new GeneratedKeyHolder();

		this.jdbcTemplate.update(psc, keyHolder);

		return keyHolder.getKey().longValue();
	}

	private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
		this.jdbcTemplate.update("insert into Taco_Ingredients (taco, ingredient) values (?, ?)", tacoId,
				ingredient.getId());

	}

	@Override
	public Optional<Taco> findById(Long id) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public boolean existsById(Long id) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public Iterable<Taco> findAllById(Iterable<Long> ids) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public long count() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public Page<Taco> findAllWithIngredients(Pageable pageable) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public <S extends Taco> Iterable<S> saveAll(Iterable<S> entities) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void deleteById(Long id) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void delete(Taco entity) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void deleteAll(Iterable<? extends Taco> entities) {
		throw new UnsupportedOperationException("Not implemented");
	}
}