package com.marlonpatrick.tacocloud.taco;

import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Primary
@RepositoryRestResource(path="tacos", collectionResourceRel="tacos")
interface ReactiveTacoRepository extends ReactiveTacoRepositoryGateway, Repository<Taco, UUID> {

	//TODO: implement Pageable reactively
	//test spring data rest
//	Page<Taco> findAll(Pageable pageable);
	
	//TODO: implement specific Query reactively
//	@Query(value="SELECT T FROM Taco T JOIN FETCH T.ingredients", 
//			countQuery="SELECT count(T) FROM Taco T")
//	Page<Taco> findWithPageableJoinFetchAndCount(Pageable pageable);

}
