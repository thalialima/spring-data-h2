package br.com.alura.spring.data.jpa.orm;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "unidades_de_trabalho")
public class UnidadeDeTrabalho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private String endereco;
	
	@ManyToMany(mappedBy = "unidadesTrabalho", fetch = FetchType.EAGER)
	private List<Funcionario> funcionarios;
	
	public UnidadeDeTrabalho() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Unidade de Trabalho [Id = " + id + ", Descrição = " 
	+ descricao + ", Endereço = " + endereco + " ]";
	}
	
	
}
