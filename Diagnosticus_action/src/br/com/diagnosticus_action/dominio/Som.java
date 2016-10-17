package br.com.diagnosticus_action.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "som")
public class Som {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOM_SEQ")
	@SequenceGenerator(name = "SOM_SEQ", sequenceName = "som_seq", allocationSize = 1)
	@Column(name = "idsom")
	private Integer IdSom;

	@Column(name = "endereco")
	private String Endereco;

	public Som() {
	}

	public Integer getIdSom() {
		return IdSom;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
	}

}
