package com.marlonpatrick.tacocloud.taco;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TacoApplicationService {

	@Autowired
	private TacoRepositoryGateway tacoRepositoryGateway;

	@Autowired
	private SaveTacoUseCase saveTacoUseCase;

	public Optional<Taco> findById(Long id){
		return tacoRepositoryGateway.findById(id);
	}

	public Page<Taco> findAllWithIngredients(Pageable pageable){
		return tacoRepositoryGateway.findAllWithIngredients(pageable);
	}

	public Taco saveTaco(Taco taco) {
		return this.saveTacoUseCase.execute(taco);
	}
}
