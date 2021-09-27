package br.com.alura.spring.data.jpa;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.alura.spring.data.jpa.service.CrudCargoService;
import br.com.alura.spring.data.jpa.service.CrudFuncionarioService;
import br.com.alura.spring.data.jpa.service.CrudUnidadeDeTrabalhoService;

@EnableJpaRepositories
@SpringBootApplication
//CommandLineRunner executa o método run logo após a finalização do método main
public class SpringDataH2Application implements CommandLineRunner {

	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeDeTrabalhoService unidadeDeTrabalhoService;
	
	private Boolean system = true;

	// o framework spring irá criar uma instância da interface CargoRepository
	public SpringDataH2Application(CrudCargoService cargoService, CrudFuncionarioService funcionarioService, 
			CrudUnidadeDeTrabalhoService unidadeDeTrabalhoService) {
		
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeDeTrabalhoService = unidadeDeTrabalhoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataH2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Qual ação você que executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Funcionário");
			System.out.println("2 - Cargo");
			System.out.println("3 - Unidade de Trabalho");

			// pega um valor inteiro que o usuário digita no console
			int action = scanner.nextInt();

			switch (action) {
			case 1:
				funcionarioService.inicial(scanner);
				break;
			case 2:
				break;
			case 3:
				unidadeDeTrabalhoService.inicial(scanner);
				break;
			default:
				System.out.println("Finalizando");
				system = false;
				break;
			}
		}
	}

}
