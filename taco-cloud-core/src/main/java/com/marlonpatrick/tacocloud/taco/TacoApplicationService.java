package com.marlonpatrick.tacocloud.taco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TacoApplicationService {

	@Autowired
	private SaveTacoUseCase saveTacoUseCase;
		
	public Taco saveTaco(Taco taco) {
		return this.saveTacoUseCase.execute(taco);
	}
}
