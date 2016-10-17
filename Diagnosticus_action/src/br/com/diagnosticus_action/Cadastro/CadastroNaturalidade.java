package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.Naturalidade;

public class CadastroNaturalidade {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Naturalidade naturalidade) {
		this.session.save(naturalidade);

	}

	public void atualizar(Naturalidade naturalidade) {
		this.session.update(naturalidade);
	}

	public void excluir(Naturalidade naturalidade) {
		this.session.delete(naturalidade);
	}

	public Naturalidade carregar(Integer codigo) {
		return (Naturalidade) this.session.get(Naturalidade.class, codigo);
	}

	public List<Naturalidade> listar() {
		return this.session.createCriteria(Naturalidade.class).list();
	}
}
