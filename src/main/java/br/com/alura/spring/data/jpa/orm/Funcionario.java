package br.com.alura.spring.data.jpa.orm;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	private Double salario;
	private LocalDate dataDeContratacao;

	@ManyToOne
	@JoinColumn(name = "cargo_id", nullable = false)
	private Cargo cargo;

	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "funcionarios_unidades", joinColumns = {
			@JoinColumn(name = "fk_funcionario") }, inverseJoinColumns = { 
					@JoinColumn(name = "fk_unidade") })
	private List<UnidadeDeTrabalho> unidadesTrabalho;

	public Funcionario() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public LocalDate getDataDeContratacao() {
		return dataDeContratacao;
	}

	public void setDataDeContratacao(LocalDate dataDeContratacao) {
		this.dataDeContratacao = dataDeContratacao;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<UnidadeDeTrabalho> getUnidadeDeTrabalho() {
		return unidadesTrabalho;
	}

	public void setUnidadeDeTrabalho(List<UnidadeDeTrabalho> unidadesTrabalho) {
		this.unidadesTrabalho = unidadesTrabalho;
	}

	@Override
	public String toString() {
		return "Funcion??rio [ Id = " + id + ", Nome = " + nome + ", CPF = " + cpf + ", Sal??rio = " + salario
				+ ", Data de Contrata????o =" + dataDeContratacao + " ]";
	}

}
