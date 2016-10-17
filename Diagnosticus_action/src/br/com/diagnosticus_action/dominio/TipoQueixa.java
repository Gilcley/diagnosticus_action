package br.com.diagnosticus_action.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tipoqueixa")
public class TipoQueixa {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPOQUEIXA_SEQ")
	@SequenceGenerator(name = "TIPOQUEIXA_SEQ", sequenceName = "tipoqueixa_seq", allocationSize = 1)
	@Column(name = "idtipoqueixa")
	private Integer IdTipoQueixa;

	@Column(name = "descricaotipoqueixa")
	private String DescricaoTipoQueixa;

	public String getDescricaoTipoQueixa() {
		return DescricaoTipoQueixa;
	}

	public void setDescricaoTipoQueixa(String descricaoTipoQueixa) {
		DescricaoTipoQueixa = descricaoTipoQueixa;
	}

	public Integer getIdTipoQueixa() {
		return IdTipoQueixa;
	}

	public void setIdTipoQueixa(Integer idTipoQueixa) {
		IdTipoQueixa = idTipoQueixa;
	}

}
