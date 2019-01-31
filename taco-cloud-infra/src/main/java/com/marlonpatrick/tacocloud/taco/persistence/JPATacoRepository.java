package com.marlonpatrick.tacocloud.taco.persistence;

import org.springframework.data.repository.Repository;

import com.marlonpatrick.tacocloud.taco.domain.model.Taco;
import com.marlonpatrick.tacocloud.taco.domain.model.TacoRepository;

interface JPATacoRepository extends TacoRepository, Repository<Taco, Long> {

}
