package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.Exame;

public class CadastroExame {


	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Exame exame) {
		this.session.save(exame);

	}

	public void atualizar(Exame exame) {
		this.session.update(exame);
	}

	public void excluir(Exame exame) {
		this.session.delete(exame);
	}

	public Exame carregar(Integer codigo) {
		return (Exame) this.session.get(Exame.class, codigo);
	}

	public List<Exame> listar() {
		return this.session.createCriteria(Exame.class).list();
	}
}
