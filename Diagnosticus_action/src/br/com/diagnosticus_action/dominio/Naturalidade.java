package br.com.diagnosticus_action.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table (name="naturalidade")
public class Naturalidade {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NATURALIDADE_SEQ")
    @SequenceGenerator(name = "NATURALIDADE_SEQ", sequenceName = "naturalidade_seq", allocationSize = 1)
    @Column(name = "idnaturalidade")
	private Integer IdNaturalidade;
	
	 @Column(name = "naturalidade")
	private String Naturalidade;
	
	public Naturalidade() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getIdNaturalidade() {
		return IdNaturalidade;
	}
	
	public void setIdNaturalidade(Integer idnaturalidade) {
		IdNaturalidade = idnaturalidade;
	}
	
	
	public String getNaturalidade() {
		return Naturalidade;
	}
	
	public void setNaturalidade(String pNaturalidade) {
		Naturalidade = pNaturalidade;
	}
}
