package com.marlonpatrick.tacocloud.taco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnMissingBean(ImperativeIngredientRepositoryGateway.class)
class JDBCIngredientRepository implements ImperativeIngredientRepositoryGateway {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCIngredientRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Iterable<Ingredient> findAll() {
		return this.jdbcTemplate.query("select id, name, type from ingredient", this::mapRowToIngredient);
	}

	@Override
	public Optional<Ingredient> findById(String id) {
		Ingredient ingredient = this.jdbcTemplate.queryForObject("select id, name, type from ingredient where id=?",
				this::mapRowToIngredient, id);
		
		return Optional.ofNullable(ingredient);
	}

	@Override
	public <S extends Ingredient> S save(S ingredient) {
		jdbcTemplate.update("insert into Ingredient (id, name, type) values (?, ?, ?)", ingredient.getId(),
				ingredient.getName(), ingredient.getType().toString());
		return ingredient;
	}

	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
		return new Ingredient(rs.getString("id"), rs.getString("name"), IngredientTypes.valueOf(rs.getString("type")));
	}

	@Override
	public boolean existsById(String id) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public Iterable<Ingredient> findAllById(Iterable<String> ids) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public long count() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public <S extends Ingredient> Iterable<S> saveAll(Iterable<S> entities) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void deleteById(String id) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void delete(Ingredient entity) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void deleteAll(Iterable<? extends Ingredient> entities) {
		throw new UnsupportedOperationException("Not implemented");
	}
}