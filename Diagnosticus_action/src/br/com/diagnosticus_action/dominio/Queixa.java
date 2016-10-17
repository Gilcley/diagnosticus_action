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
@Table(name = "queixa")
public class Queixa {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUEIXA_SEQ")
	@SequenceGenerator(name = "QUEIXA_SEQ", sequenceName = "queixa_seq", allocationSize = 1)
	@Column(name = "idqueixa")
	private Integer IdQueixa;
	
	@ManyToOne
	@JoinColumn(name="idtipoqueixa")
	private TipoQueixa IdTipoQueixa;
	
	@Column(name = "descricaoqueixa")
	private String DescricaoQueixa;
	
	@ManyToMany
	@JoinTable(name = "queixa_caracteristica", joinColumns = { @JoinColumn(name = "idqueixa") }, inverseJoinColumns = { @JoinColumn(name = "id_caracteristica") })
	private Set<Caracteristica> Caracteristica = new HashSet<Caracteristica>();
	
	
	public Queixa() {
	}

	public TipoQueixa getIdTipoQueixa() {
		return IdTipoQueixa;
	}

	public void setIdTipoQueixa(TipoQueixa idTipoQueixa) {
		IdTipoQueixa = idTipoQueixa;
	}

	public String getDescricaoQueixa() {
		return DescricaoQueixa;
	}

	public void setDescricaoQueixa(String descricaoQueixa) {
		DescricaoQueixa = descricaoQueixa;
	}

	public Integer getIdQueixa() {
		return IdQueixa;
	}

	public void setIdQueixa(Integer idQueixa) {
		IdQueixa = idQueixa;
	}

	public Set<Caracteristica> getCaracteristica() {
		return Caracteristica;
	}

	public void setCaracteristica(Set<Caracteristica> caracteristica) {
		Caracteristica = caracteristica;
	}
	
	

	
}
