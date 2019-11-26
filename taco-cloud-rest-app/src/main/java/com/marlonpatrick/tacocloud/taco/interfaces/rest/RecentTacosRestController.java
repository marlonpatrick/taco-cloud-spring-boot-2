package com.marlonpatrick.tacocloud.taco.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.marlonpatrick.tacocloud.taco.Taco;
import com.marlonpatrick.tacocloud.taco.TacoApplicationService;

import reactor.core.publisher.Flux;

//@RestController
//@CrossOrigin("*")
//@RequestMapping(path = "/tacos", produces = "application/json")
@RepositoryRestController
public class RecentTacosRestController {

	@Autowired
	private TacoApplicationService tacoApplicationService;
	
//	@Autowired
//	private EntityLinks entityLinks;


	@GetMapping(path="/tacos/recent", produces="application/hal+json")
	public Flux<Taco> recentTacos() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		return tacoApplicationService.findAll(page);

		//TODO: implement reactively 
//		Resources<TacoResource> tacosResources = new Resources<>(TacoResourceAssembler.INSTANCE.toResources(tacos));
//		
//		tacosResources.add(entityLinks.linkFor(Taco.class).slash("recent").withRel("recents"));
//
//
//		return new ResponseEntity<>(tacosResources, HttpStatus.OK);
	}
}
