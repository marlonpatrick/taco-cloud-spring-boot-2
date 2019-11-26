package com.marlonpatrick.tacocloud.taco.interfaces.rest;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marlonpatrick.tacocloud.taco.Taco;
import com.marlonpatrick.tacocloud.taco.TacoApplicationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/tacos", produces = "application/json")
public class TacoRestController {

	@Autowired
	private TacoApplicationService tacoApplicationService;

	@GetMapping("/{id}")
	public Mono<Taco> tacoById(@PathVariable("id") UUID id) {
		return tacoApplicationService.findById(id);
	}

	@GetMapping("/recent")
	public Flux<Taco> recentTacos() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		return tacoApplicationService.findAll(page);
	}

	@GetMapping("/recent/hateoas")
	public Flux<Taco> recentTacosHateoas() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		return tacoApplicationService.findAll(page);

		//TODO: implement reactively 
//		Resources<TacoResource> tacosResources = new Resources<>(TacoResourceAssembler.INSTANCE.toResources(tacos));
//		tacosResources.add(linkTo(methodOn(TacoRestController.class).recentTacosHateoas()).withRel("recents"));
//
//		return tacosResources;
	}

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Taco> postTaco(@RequestBody Taco taco) {
		return tacoApplicationService.saveTaco(taco);
	}
}
