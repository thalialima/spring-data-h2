package br.com.alura.spring.data.jpa.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.hibernate.type.LocalDateTimeType;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.jpa.orm.Funcionario;
import br.com.alura.spring.data.jpa.orm.FuncionarioProjecao;
import br.com.alura.spring.data.jpa.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	
	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual ação de relatório deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Buscar Funcionário por nome");
			System.out.println("2 - Buscar Funcionário por nome, data de contratação e salário");
			System.out.println("3 - Buscar Funcionário por data de contratação");
			System.out.println("4 - Pesquisa Funcionário por Salário");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacao(scanner);
				break;
			case 4:
				pesquisaFuncionarioSalario();
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Informe o nome do funcionário: ");
		String nome = scanner.next();
		//o spring data é capaz de criar uma quey para o BD com um método Java
		//iniciar o método com findBy indica que é uma consulta
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar");
		String nome = scanner.next();
		
		System.out.println("Qual data de contratação deseja pesquisar?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		System.out.println("Qual salário maior deseja pesquisar?");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> list = funcionarioRepository
				.findNomeDataContratacaoSalarioMaiorQue(nome, salario, localDate);
		list.forEach(System.out::println);
	}
	
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Qual data de contratação deseja pesquisar?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> list = funcionarioRepository.findDataDeContratacaoMaiorQue(localDate);
	
		list.forEach(System.out::println);
	}
	

	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario: Id: " + f.getId() 
		+ " | Nome: " + f.getNome() + " | Salário: " + f.getSalario()));
	}
	
	
}
