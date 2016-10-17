package br.com.diagnosticus_action.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tipousuario")
public class TipoUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPO_USUARIO_SEQ")
	@SequenceGenerator(name = "TIPO_USUARIO_SEQ", sequenceName = "tipo_usuario_seq", allocationSize = 1)
	@Column(name = "idtipousuario")
	private Integer IdTipoUsuario;

	@Column(name = "tipousuario")
	private String TipoUsuario;

	public TipoUsuario() {
	}

	public Integer getIdTipoUsuario() {
		return IdTipoUsuario;
	}

	public void setIdTipoUsuario(Integer idTipoUsuario) {
		IdTipoUsuario = idTipoUsuario;
	}

	public String getTipoUsuario() {
		return TipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		TipoUsuario = tipoUsuario;
	}

}
