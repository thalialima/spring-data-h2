package br.com.alura.spring.data.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.jpa.orm.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Long>{

	
}
