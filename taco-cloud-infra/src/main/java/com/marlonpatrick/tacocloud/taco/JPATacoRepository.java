package com.marlonpatrick.tacocloud.taco;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Primary
@RepositoryRestResource(path="tacos", collectionResourceRel="tacos")
interface JPATacoRepository extends TacoRepositoryGateway, Repository<Taco, Long> {

	//test spring data rest
	Page<Taco> findAll(Pageable pageable);

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
