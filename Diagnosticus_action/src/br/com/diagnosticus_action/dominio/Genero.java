package br.com.diagnosticus_action.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name="genero")
public class Genero {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERO_SEQ")
    @SequenceGenerator(name = "GENERO_SEQ", sequenceName = "genero_seq", allocationSize = 1)
    @Column(name = "idgenero")
	private Integer IdGenero;

	@Column(name = "genero")
	private String Genero;
	
	public Genero() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdGenero() {
		return IdGenero;
	}
	
	public void setIdGenero(Integer idgenero) {
		IdGenero = idgenero;
	}

	public String getGenero() {
		return Genero;
	}

	public void setGenero(String pGenero) {
		Genero = pGenero;
	}

}
