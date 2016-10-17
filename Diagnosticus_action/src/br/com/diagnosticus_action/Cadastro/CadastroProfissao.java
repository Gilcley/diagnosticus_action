package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.Profissao;

public class CadastroProfissao {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Profissao profissao) {
		this.session.save(profissao);

	}

	public void atualizar(Profissao profissao) {
		this.session.update(profissao);
	}

	public void excluir(Profissao profissao) {
		this.session.delete(profissao);
	}

	public Profissao carregar(Integer codigo) {
		return (Profissao) this.session.get(Profissao.class, codigo);
	}

	public List<Profissao> listar() {
		return this.session.createCriteria(Profissao.class).list();
	}
}
