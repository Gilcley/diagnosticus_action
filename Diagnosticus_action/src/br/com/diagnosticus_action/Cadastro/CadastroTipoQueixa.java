package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.TipoQueixa;

public class CadastroTipoQueixa {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(TipoQueixa tipoqueixa) {
		this.session.save(tipoqueixa);

	}

	public void atualizar(TipoQueixa tipoqueixa) {
		this.session.update(tipoqueixa);
	}

	public void excluir(TipoQueixa tipoqueixa) {
		this.session.delete(tipoqueixa);
	}

	public TipoQueixa carregar(Integer codigo) {
		return (TipoQueixa) this.session.get(TipoQueixa.class, codigo);
	}

	public List<TipoQueixa> listar() {
		return this.session.createCriteria(TipoQueixa.class).list();
	}
}
