package com.marlonpatrick.tacocloud.taco.rest;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.marlonpatrick.tacocloud.taco.Taco;

public class TacoResourceAssembler extends ResourceAssemblerSupport<Taco, TacoResource>{

	public static final TacoResourceAssembler INSTANCE = new TacoResourceAssembler();

	private TacoResourceAssembler() {
		super(TacoRestController.class, TacoResource.class);
	}

	@Override
	protected TacoResource instantiateResource(Taco entity) {
		return new TacoResource(entity);
	}
	
	@Override
	public TacoResource toResource(Taco entity) {
		return createResourceWithId(entity.getId(), entity);
	}

}
