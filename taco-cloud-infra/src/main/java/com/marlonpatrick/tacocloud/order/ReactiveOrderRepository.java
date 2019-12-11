package com.marlonpatrick.tacocloud.order;

import java.util.UUID;
import java.util.concurrent.Flow.Publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//TODO: validate @RepositoryRestResource reactive
@RepositoryRestResource
class ReactiveOrderRepository implements ReactiveOrderRepositoryGateway, Repository<Order, UUID> {

	
//	PENDENCIAS:
//		- Substituir o campo Order.id (Long) por Order.id (UUID) ---->>>>>>> OK
	
//		- Criar o ReactiveCassandraOrderRepository como interface mapeando a classe CassandraOrder
	
//		- Injetar ReactiveCassandraOrderRepository aqui nesta classe
	
//		- Fazer o mapper entre Order e CassandraOrder aqui nesta classe e usar ReactiveCassandraOrderRepository 
	
	@Autowired
	private ReactiveCassandraOrderRepository reactiveCassandraOrderRepository;
		
	@Override
	@SuppressWarnings("unchecked")
	public <S extends Order> Mono<S> save(S entity) {
		
		Mono<CassandraOrder> monoCassandraOrder = reactiveCassandraOrderRepository.save(CassandraDomainOrderMapper.fromDomain(entity));
		
		return (Mono<S>) monoCassandraOrder.map(co -> co.toOrder());
	}

	@Override
	public <S extends Order> Flux<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Order> Flux<S> saveAll(Publisher<S> entityStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Order> Mono<S> insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Order> Flux<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Order> Flux<S> insert(Publisher<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Order> findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Order> findById(Publisher<UUID> id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Boolean> existsById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Boolean> existsById(Publisher<UUID> id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Order> findAllById(Iterable<UUID> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Order> findAllById(Publisher<UUID> idStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Long> count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteById(Publisher<UUID> id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> delete(Order entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll(Iterable<? extends Order> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll(Publisher<? extends Order> entityStream) {
		// TODO Auto-generated method stub
		return null;
	}

}
