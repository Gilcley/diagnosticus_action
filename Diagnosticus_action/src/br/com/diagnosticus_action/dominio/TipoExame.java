package br.com.diagnosticus_action.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tipoexame")
public class TipoExame {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPOEXAME_SEQ")
	@SequenceGenerator(name = "TIPOEXAME_SEQ", sequenceName = "tipoexame_seq", allocationSize = 1)
	@Column(name = "idtipoexame")
	private Integer IdTipoExame;

	@Column(name = "descricaotipoexame")
	private String DescricaoTipoExame;

	public TipoExame() {
	}

	public Integer getIdTipoExame() {
		return IdTipoExame;
	}

	public void setIdTipoExame(Integer idTipoExame) {
		IdTipoExame = idTipoExame;
	}

	public String getDescricaoTipoExame() {
		return DescricaoTipoExame;
	}

	public void setDescricaoTipoExame(String descricaoTipoExame) {
		DescricaoTipoExame = descricaoTipoExame;
	}

}
