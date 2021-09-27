package br.com.alura.spring.data.jpa.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.jpa.orm.UnidadeDeTrabalho;
import br.com.alura.spring.data.jpa.repository.UnidadeDeTrabalhoRepository;

//indica que a classe será usada em outra
@Service
public class CrudUnidadeDeTrabalhoService {

	private Boolean system = true;
	private final UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;

	public CrudUnidadeDeTrabalhoService(UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository) {
		this.unidadeDeTrabalhoRepository = unidadeDeTrabalhoRepository;
	}

	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual ação de Unidade de Trabalho deseja executar? ");
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
		System.out.println("Informe a descrição da unidade:");
		String descricao = scanner.next();

		System.out.println("Informe o endereço: ");
		String endereco = scanner.next();

		UnidadeDeTrabalho unidadeDeTrabalho = new UnidadeDeTrabalho();
		unidadeDeTrabalho.setDescricao(descricao);
		unidadeDeTrabalho.setEndereco(endereco);

		unidadeDeTrabalhoRepository.save(unidadeDeTrabalho);
		System.out.println("Unidade de trabalho salva");
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Informe o id: ");
		Long id = scanner.nextLong();

		System.out.println("Informe a nova descrição: ");
		String descricao = scanner.next();

		System.out.println("Informe o novo endereço: ");
		String endereco = scanner.next();

		UnidadeDeTrabalho unidadeDeTrabalho = new UnidadeDeTrabalho();
		unidadeDeTrabalho.setId(id);
		unidadeDeTrabalho.setDescricao(descricao);
		unidadeDeTrabalho.setEndereco(endereco);

		unidadeDeTrabalhoRepository.save(unidadeDeTrabalho);
		System.out.println("Unidade de trabalho atualizada");
	}

	private void visualizar() {
		Iterable<UnidadeDeTrabalho> unidades = unidadeDeTrabalhoRepository.findAll();
		unidades.forEach(unidade -> System.out.println(unidade));
	}

	private void deletar(Scanner scanner) {
		System.out.println("Informe o id da Unidade a ser deletada: ");
		Long id = scanner.nextLong();
		unidadeDeTrabalhoRepository.deleteById(id);
		System.out.println("A unidade foi deletada");
	}

}
