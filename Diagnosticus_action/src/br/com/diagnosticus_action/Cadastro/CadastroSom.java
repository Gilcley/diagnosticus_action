package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.Som;

public class CadastroSom {


	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Som som) {
		this.session.save(som);

	}

	public void atualizar(Som som) {
		this.session.update(som);
	}

	public void excluir(Som som) {
		this.session.delete(som);
	}

	public Som carregar(Integer codigo) {
		return (Som) this.session.get(Som.class, codigo);
	}

	public List<Som> listar() {
		return this.session.createCriteria(Som.class).list();
	}
}
