package br.com.alura.spring.data.jpa.service;

import java.util.Scanner;


import org.springframework.stereotype.Service;

import br.com.alura.spring.data.jpa.orm.Cargo;
import br.com.alura.spring.data.jpa.repository.CargoRepository;

//indica que a classe será usada em outra
@Service
public class CrudCargoService {

	private Boolean system = true;

	private final CargoRepository cargoRepository;

	// injeção de dependências
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual ação de cargo deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");

			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
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
		System.out.println("Descrição do cargo");

		// pega o valor digitado no console
		String descricao = scanner.next();
		Cargo cargo = new Cargo();

		// atribui a descrição feita pelo cliente
		cargo.setDescricao(descricao);

		// salva o cargo com a descrição dinâmica passada pelo cliente
		cargoRepository.save(cargo);
		System.out.println("Salvo");
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Alterar descrição");

		System.out.println("Informe o ID do cargo:");
		Long id = scanner.nextLong();

		System.out.println("Nova descrição:");
		String novaDescricao = scanner.next();

		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(novaDescricao);

		// o método save tem a função de atualizar o registro da tabela
		// caso também seja informado o id de registro da tabela
		cargoRepository.save(cargo);
		System.out.println("Atualizado");
	}
	
	private void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Informe o id: ");
		Long id = scanner.nextLong();
		cargoRepository.deleteById(id);
	}

}
