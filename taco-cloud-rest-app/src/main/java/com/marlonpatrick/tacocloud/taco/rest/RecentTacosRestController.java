package com.marlonpatrick.tacocloud.taco.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.marlonpatrick.tacocloud.taco.Taco;
import com.marlonpatrick.tacocloud.taco.TacoRepository;

//@RestController
//@CrossOrigin("*")
//@RequestMapping(path = "/tacos", produces = "application/json")
@RepositoryRestController
public class RecentTacosRestController {

	@Autowired
	private TacoRepository tacoRepository;
	
	@Autowired
	private EntityLinks entityLinks;


	@GetMapping(path="/tacos/recent", produces="application/hal+json")
	public ResponseEntity<Resources<TacoResource>> recentTacos() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		List<Taco> tacos = tacoRepository.findAllWithIngredients(page).getContent();

		Resources<TacoResource> tacosResources = new Resources<>(TacoResourceAssembler.INSTANCE.toResources(tacos));
		
		tacosResources.add(entityLinks.linkFor(Taco.class).slash("recent").withRel("recents"));


		return new ResponseEntity<>(tacosResources, HttpStatus.OK);
	}
}
