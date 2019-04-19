package com.marlonpatrick.tacocloud.taco;

import org.springframework.stereotype.Service;

@Service
class SaveTacoUseCase {

	private FullTacoRepository tacoRepository;

	public SaveTacoUseCase(FullTacoRepository tacoRepository) {
		this.tacoRepository = tacoRepository;
	}

	public Taco execute(Taco taco){
		return this.tacoRepository.save(taco);
	}
}
