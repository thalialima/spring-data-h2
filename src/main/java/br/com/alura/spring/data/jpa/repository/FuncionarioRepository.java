package br.com.alura.spring.data.jpa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.jpa.orm.Funcionario;
import br.com.alura.spring.data.jpa.orm.FuncionarioProjecao;

//JpaSpecificationExecutor fica responsável por executar as specifications dentro do repository
//as specifications ajudam a fazer queries dinâmicas

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Long>
	,JpaSpecificationExecutor<Funcionario>{
	
	//Derived Query 
	List<Funcionario> findByNome(String nome);
	
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome "
			+ "AND f.salario >= :salario AND f.dataDeContratacao = :data")
	List<Funcionario> findNomeDataContratacaoSalarioMaiorQue(String nome, Double salario, LocalDate data);
	
	//Utilizando Query nativa
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_de_contratacao >= :data", 
			nativeQuery = true)
	List<Funcionario> findDataDeContratacaoMaiorQue(LocalDate data);
	
	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", 
			nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();
	
}
