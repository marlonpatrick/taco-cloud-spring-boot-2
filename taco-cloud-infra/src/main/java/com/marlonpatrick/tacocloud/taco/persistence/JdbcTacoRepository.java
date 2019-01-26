package com.marlonpatrick.tacocloud.taco.persistence;

import java.sql.Timestamp;
import java.sql.Types;
import java.time.ZonedDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.marlonpatrick.tacocloud.taco.domain.Ingredient;
import com.marlonpatrick.tacocloud.taco.domain.Taco;
import com.marlonpatrick.tacocloud.taco.domain.TacoRepository;

@Repository
class JdbcTacoRepository implements TacoRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Taco save(Taco taco) {
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

}