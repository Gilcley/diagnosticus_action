package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.EstadoCivil;

public class CadastroEstadoCivil {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(EstadoCivil estadocivil) {
		this.session.save(estadocivil);

	}

	public void atualizar(EstadoCivil estadocivil) {
		this.session.update(estadocivil);
	}

	public void excluir(EstadoCivil estadocivil) {
		this.session.delete(estadocivil);
	}

	public EstadoCivil carregar(Integer codigo) {
		return (EstadoCivil) this.session.get(EstadoCivil.class, codigo);
	}

	public List<EstadoCivil> listar() {
		return this.session.createCriteria(EstadoCivil.class).list();
	}

}
