package br.com.alura.spring.data.jpa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.jpa.orm.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long>{
	
	//Derived Query 
	List<Funcionario> findByNome(String nome);
	
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome "
			+ "AND f.salario >= :salario AND f.dataDeContratacao = :data")
	List<Funcionario> findNomeDataContratacaoSalarioMaiorQue(String nome, Double salario, LocalDate data);
	
	//Utilizando Query nativa
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_de_contratacao >= :data", 
			nativeQuery = true)
	List<Funcionario> findDataDeContratacaoMaiorQue(LocalDate data);
	
	
	
}
