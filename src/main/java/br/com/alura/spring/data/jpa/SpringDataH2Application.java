package br.com.alura.spring.data.jpa;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.alura.spring.data.jpa.service.CrudCargoService;
import br.com.alura.spring.data.jpa.service.CrudFuncionarioService;
import br.com.alura.spring.data.jpa.service.CrudUnidadeDeTrabalhoService;
import br.com.alura.spring.data.jpa.service.RelatorioFuncionarioDinamico;
import br.com.alura.spring.data.jpa.service.RelatoriosService;

@EnableJpaRepositories
@SpringBootApplication
//CommandLineRunner executa o método run logo após a finalização do método main
public class SpringDataH2Application implements CommandLineRunner {

	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeDeTrabalhoService unidadeDeTrabalhoService;
	private final RelatoriosService relatoriosService;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;
	
	private Boolean system = true;

	// o framework spring irá criar uma instância da interface CargoRepository
	public SpringDataH2Application(CrudCargoService cargoService, CrudFuncionarioService funcionarioService, 
			CrudUnidadeDeTrabalhoService unidadeDeTrabalhoService, RelatoriosService relatoriosService,
			RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
		
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeDeTrabalhoService = unidadeDeTrabalhoService;
		this.relatoriosService = relatoriosService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataH2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Qual ação você quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Funcionário");
			System.out.println("2 - Cargo");
			System.out.println("3 - Unidade de Trabalho");
			System.out.println("4 - Relatórios");
			System.out.println("5 - Relatório Dinâmico");

			// pega um valor inteiro que o usuário digita no console
			int action = scanner.nextInt();

			switch (action) {
			case 1:
				funcionarioService.inicial(scanner);
				break;
			case 2:
				cargoService.inicial(scanner);
				break;
			case 3:
				unidadeDeTrabalhoService.inicial(scanner);
				break;
			case 4:
				relatoriosService.inicial(scanner);
				break;
			case 5:
				relatorioFuncionarioDinamico.inicial(scanner);
				break;
			default:
				System.out.println("Finalizando");
				system = false;
				break;
			}
		}
	}

}
