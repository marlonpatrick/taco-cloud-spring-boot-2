package com.marlonpatrick.tacocloud.taco.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.marlonpatrick.tacocloud.taco.domain.model.Taco;
import com.marlonpatrick.tacocloud.taco.domain.model.TacoRepository;

interface JPATacoRepository extends TacoRepository, Repository<Taco, Long> {
	
	
	@Override
	@EntityGraph(type=EntityGraphType.FETCH, attributePaths= {"ingredients"})
	Optional<Taco> findById(Long id);
	
	@Query("SELECT T FROM Taco T")
	@EntityGraph(type=EntityGraphType.FETCH, attributePaths= {"ingredients"})
	Page<Taco> findAllWithIngredients(Pageable pageable);

	@Query(value="SELECT T FROM Taco T JOIN FETCH T.ingredients", 
			countQuery="SELECT count(T) FROM Taco T")
	Page<Taco> findWithPageableJoinFetchAndCount(Pageable pageable);

}
