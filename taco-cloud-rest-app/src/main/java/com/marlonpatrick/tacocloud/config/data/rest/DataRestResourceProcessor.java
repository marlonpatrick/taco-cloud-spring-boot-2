package com.marlonpatrick.tacocloud.config.data.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import com.marlonpatrick.tacocloud.taco.Taco;

@Configuration
public class DataRestResourceProcessor {

	@Bean
	public ResourceProcessor<PagedResources<Resource<Taco>>> tacoProcessor(EntityLinks links) {
		return new ResourceProcessor<PagedResources<Resource<Taco>>>() {

			@Override
			public PagedResources<Resource<Taco>> process(PagedResources<Resource<Taco>> resource) {

				resource.add(links.linkFor(Taco.class).slash("recent").withRel("recents"));

//				resource.add(linkTo(methodOn(RecentTacosRestController.class).recentTacos())
//						.withRel("recents"));

				return resource;
			}

		};
	}
}
