package br.com.alura.spring.data.jpa.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.jpa.orm.Cargo;
import br.com.alura.spring.data.jpa.orm.Funcionario;
import br.com.alura.spring.data.jpa.orm.UnidadeDeTrabalho;
import br.com.alura.spring.data.jpa.repository.CargoRepository;
import br.com.alura.spring.data.jpa.repository.FuncionarioRepository;
import br.com.alura.spring.data.jpa.repository.UnidadeDeTrabalhoRepository;

//indica que a classe será usada em outra

@Service
public class CrudFuncionarioService {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private Boolean system = true;

	private final CargoRepository cargoRepository;
	private final FuncionarioRepository funcionarioRepository;
	private final UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;

	// injeção de dependência
	public CrudFuncionarioService(CargoRepository cargoRepository, FuncionarioRepository funcionarioRepository,
			UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository) {
		this.cargoRepository = cargoRepository;
		this.funcionarioRepository = funcionarioRepository;
		this.unidadeDeTrabalhoRepository = unidadeDeTrabalhoRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual ação de Funcionário deseja executar?");
			System.out.println("Sair - 0");
			System.out.println("Salvar - 1");
			System.out.println("Atualizar - 2");
			System.out.println("Visualizar - 3");
			System.out.println("Deletar - 4");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar(scanner);
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}

	private void salvar(Scanner scanner) {
		System.out.println("Informe os dados do funcionário: ");
		
		System.out.println("Nome:");
		String nome = scanner.next();
		
		System.out.println("CPF:");
		String cpf = scanner.next();
		
		System.out.println("Salário: ");
		Double salario = scanner.nextDouble();
		
		System.out.println("Data de contratação:");
		String dataDeContratacao = scanner.next();
		
		System.out.println("Id do cargo:");
		Long cargoId = scanner.nextLong();
		
		List<UnidadeDeTrabalho> unidades = unidade(scanner);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataDeContratacao(LocalDate.parse(dataDeContratacao, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		funcionario.setUnidadeDeTrabalho(unidades);
		
		funcionarioRepository.save(funcionario);
		System.out.println("Salvo.");
	}

	private List<UnidadeDeTrabalho> unidade(Scanner scanner) {
		Boolean isTrue = true;
		List<UnidadeDeTrabalho> unidades = new ArrayList<UnidadeDeTrabalho>();

		while (isTrue) {
			System.out.println("Digite o Id da unidade (Para sair digite 0)");
			Long unidadeId = scanner.nextLong();

			if (unidadeId != 0) {
				Optional<UnidadeDeTrabalho> unidade = unidadeDeTrabalhoRepository.findById(unidadeId);
				unidades.add(unidade.get());
			} else {
				isTrue = false;
			}
		}
		return unidades;
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Digite o id");
		Long id = scanner.nextLong();

		System.out.println("Digite o nome");
		String nome = scanner.next();

		System.out.println("Digite o cpf");
		String cpf = scanner.next();

		System.out.println("Digite o salario");
		Double salario = scanner.nextDouble();

		System.out.println("Digite a data de contracao");
		String dataContratacao = scanner.next();

		System.out.println("Digite o cargoId");
		Long cargoId = scanner.nextLong();

		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataDeContratacao(LocalDate.parse(dataContratacao, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());

		funcionarioRepository.save(funcionario);
		System.out.println("Alterado");
	}

	private void visualizar(Scanner scanner) {
		System.out.println("Qual página você deseja visualizar");
		Integer page = scanner.nextInt();
		
		//delimita a paginação
		Pageable pegeable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "nome"));
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pegeable);
		
		System.out.println(funcionarios);
		//getNumber() retorna qual a página que o cliente está visualizando no momento atual
		System.out.println("Página atual: " + funcionarios.getNumber());
		//getTotalElements() mostra o total de elementos que tem nesta consulta
		System.out.println("Total de elementos: " + funcionarios.getTotalElements());
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}

	private void deletar(Scanner scanner) {
		System.out.println("Id");
		Long id = scanner.nextLong();
		funcionarioRepository.deleteById(id);
		System.out.println("Deletado");
	}

}
