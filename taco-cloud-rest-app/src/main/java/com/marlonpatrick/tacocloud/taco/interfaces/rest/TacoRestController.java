package com.marlonpatrick.tacocloud.taco.interfaces.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.marlonpatrick.tacocloud.taco.TacoRepositoryGateway;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/tacos", produces = "application/json")
public class TacoRestController {

	@Autowired
	private TacoRepositoryGateway tacoRepository;

	@Autowired
	private TacoApplicationService tacoApplicationService;

//	@Autowired
//	private EntityLinks entityLinks;

	@GetMapping("/{id}")
	public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
		Optional<Taco> optTaco = tacoRepository.findById(id);

		if (optTaco.isPresent()) {
			return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/recent")
	public Iterable<Taco> recentTacos() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		return tacoRepository.findAllWithIngredients(page).getContent();
	}

	@GetMapping("/recent/hateoas")
	public Resources<TacoResource> recentTacosHateoas() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		List<Taco> tacos = tacoRepository.findAllWithIngredients(page).getContent();

		Resources<TacoResource> tacosResources = new Resources<>(TacoResourceAssembler.INSTANCE.toResources(tacos));
		tacosResources.add(linkTo(methodOn(TacoRestController.class).recentTacosHateoas()).withRel("recents"));

		return tacosResources;
	}

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoApplicationService.saveTaco(taco);
	}
}
