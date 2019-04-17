package com.marlonpatrick.tacocloud.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.repository.Repository;

import com.marlonpatrick.tacocloud.taco.domain.model.Taco;

@org.springframework.stereotype.Repository
public class JPATest implements Repository<Taco, Long>{

	@PersistenceContext
    private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Taco> findAllWithIngredients(){
		return this.em.createQuery("SELECT T FROM Taco T JOIN FETCH T.ingredients ").getResultList();
	}
}
