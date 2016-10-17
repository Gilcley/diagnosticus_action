package br.com.diagnosticus_action.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name="profissao")
public class Profissao {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFISSAO_SEQ")
    @SequenceGenerator(name = "PROFISSAO_SEQ", sequenceName = "profissao_seq", allocationSize = 1)
    @Column(name = "idprofissao")
	private Integer IdProfissao;
	
	@Column(name = "profissao")
	private String Profissao;
	
	public Profissao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdProfissao() {
		return IdProfissao;
	}
	
	public void setIdProfissao(Integer IdProfissao) {
		this.IdProfissao = IdProfissao;
	}

	public String getProfissao() {
		return Profissao;
	}

	public void setProfissao(String pProfissao) {
		Profissao = pProfissao;
	}
	
}
