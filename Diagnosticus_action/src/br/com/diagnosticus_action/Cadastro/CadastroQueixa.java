package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.Queixa;

public class CadastroQueixa {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Queixa queixa) {
		this.session.save(queixa);

	}

	public void atualizar(Queixa queixa) {
		this.session.update(queixa);
	}

	public void excluir(Queixa queixa) {
		this.session.delete(queixa);
	}

	public Queixa carregar(Integer codigo) {
		return (Queixa) this.session.get(Queixa.class, codigo);
	}

	public List<Queixa> listar() {
		return this.session.createCriteria(Queixa.class).list();
	}
}
