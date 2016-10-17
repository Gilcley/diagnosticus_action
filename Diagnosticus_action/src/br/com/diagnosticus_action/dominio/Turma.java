package br.com.diagnosticus_action.dominio;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "turma")
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TURMA_SEQ")
	@SequenceGenerator(name = "TURMA_SEQ", sequenceName = "turma_seq", allocationSize = 1)
	@Column(name = "id_turma")
	private Integer id;
	
	@Column(name = "nome_turma")
	private String nomeTurma;
	
	@ManyToMany
	@JoinTable(name = "turma_usuario", joinColumns = { @JoinColumn(name = "id_turma") }, inverseJoinColumns = { @JoinColumn(name = "idusuario") })
	private Set<Usuario> Alunos = new HashSet<Usuario>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	public Set<Usuario> getAlunos() {
		return Alunos;
	}

	public void setAlunos(Set<Usuario> alunos) {
		Alunos = alunos;
	}
	
}
