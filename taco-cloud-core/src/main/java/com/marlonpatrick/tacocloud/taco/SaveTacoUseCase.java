package com.marlonpatrick.tacocloud.taco;

import org.springframework.stereotype.Service;

@Service
class SaveTacoUseCase {

	private FullTacoRepositoryGateway tacoRepository;

	public SaveTacoUseCase(FullTacoRepositoryGateway tacoRepository) {
		this.tacoRepository = tacoRepository;
	}

	public Taco execute(Taco taco){
		return this.tacoRepository.save(taco);
	}
}
