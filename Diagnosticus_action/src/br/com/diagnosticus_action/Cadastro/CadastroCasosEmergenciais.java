package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.CasoEmergencial;

public class CadastroCasosEmergenciais {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(CasoEmergencial casoemergencial) {
		this.session.save(casoemergencial);

	}

	public void atualizar(CasoEmergencial casoemergencial) {
		this.session.update(casoemergencial);
	}

	public void excluir(CasoEmergencial casoemergencial) {
		this.session.delete(casoemergencial);
	}

	public CasoEmergencial carregar(Integer codigo) {
		return (CasoEmergencial) this.session.get(CasoEmergencial.class, codigo);
	}

	public List<CasoEmergencial> listar() {
		return this.session.createCriteria(CasoEmergencial.class).list();
	}
}
