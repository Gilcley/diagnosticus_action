package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.Caracteristica;

public class CadastroCaracteristica {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Caracteristica caracteristica) {
		this.session.save(caracteristica);

	}

	public void atualizar(Caracteristica caracteristica) {
		this.session.update(caracteristica);
	}

	public void excluir(Caracteristica caracteristica) {
		this.session.delete(caracteristica);
	}

	public Caracteristica carregar(Integer codigo) {
		return (Caracteristica) this.session.get(Caracteristica.class, codigo);
	}

	public List<Caracteristica> listar() {
		return this.session.createCriteria(Caracteristica.class).list();
	}

}
