package com.marlonpatrick.tacocloud.taco.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.marlonpatrick.tacocloud.taco.domain.Ingredient;
import com.marlonpatrick.tacocloud.taco.domain.IngredientRepository;
import com.marlonpatrick.tacocloud.taco.domain.IngredientTypes;

@Repository
class JdbcIngredientRepository implements IngredientRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Iterable<Ingredient> findAll() {
		return this.jdbcTemplate.query("select id, name, type from ingredient", this::mapRowToIngredient);
	}

	@Override
	public Ingredient findById(String id) {
		return this.jdbcTemplate.queryForObject("select id, name, type from ingredient where id=?",
				this::mapRowToIngredient, id);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbcTemplate.update("insert into Ingredient (id, name, type) values (?, ?, ?)", ingredient.getId(),
				ingredient.getName(), ingredient.getType().toString());
		return ingredient;
	}

	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
		return new Ingredient(rs.getString("id"), rs.getString("name"), IngredientTypes.valueOf(rs.getString("type")));
	}
}