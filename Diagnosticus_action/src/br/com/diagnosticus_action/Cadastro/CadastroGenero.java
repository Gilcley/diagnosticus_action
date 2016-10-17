package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.Genero;

public class CadastroGenero {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Genero genero) {
		this.session.save(genero);

	}

	public void atualizar(Genero genero) {
		this.session.update(genero);
	}

	public void excluir(Genero genero) {
		this.session.delete(genero);
	}

	public Genero carregar(Integer codigo) {
		return (Genero) this.session.get(Genero.class, codigo);
	}

	public List<Genero> listar() {
		return this.session.createCriteria(Genero.class).list();
	}
}
