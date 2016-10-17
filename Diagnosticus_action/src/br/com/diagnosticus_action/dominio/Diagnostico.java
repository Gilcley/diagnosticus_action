package br.com.diagnosticus_action.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "diagnostico")
public class Diagnostico {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIAGNOSTICO_SEQ")
	@SequenceGenerator(name = "DIAGNOSTICO_SEQ", sequenceName = "diagnostico_seq", allocationSize = 1)
	@Column(name = "iddiagnostico")
	private Integer IdDiagnostico;

	@Column(name = "tratamento")
	private String Tratamento;

	@ManyToOne
	@JoinColumn(name = "codigo_cid")
	private CID cid;
	
	
	public Diagnostico() {

	}

	public Integer getIdDiagnostico() {
		return IdDiagnostico;
	}

	public void setIdDiagnostico(Integer idDiagnostico) {
		IdDiagnostico = idDiagnostico;
	}

	public String getTratamento() {
		return Tratamento;
	}

	public void setTratamento(String tratamento) {
		Tratamento = tratamento;
	}

	public CID getCid() {
		return cid;
	}

	public void setCid(CID cid) {
		this.cid = cid;
	}

}
