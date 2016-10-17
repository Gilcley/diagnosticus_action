package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.TipoUsuario;

public class CadastroTipoUsuario {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(TipoUsuario tipousuario) {
		this.session.save(tipousuario);

	}

	public void atualizar(TipoUsuario tipousuario) {
		this.session.update(tipousuario);
	}

	public void excluir(TipoUsuario tipousuario) {
		this.session.delete(tipousuario);
	}

	public TipoUsuario carregar(Integer codigo) {
		return (TipoUsuario) this.session.get(TipoUsuario.class, codigo);
	}

	public List<TipoUsuario> listar() {
		return this.session.createCriteria(TipoUsuario.class).list();
	}
}
