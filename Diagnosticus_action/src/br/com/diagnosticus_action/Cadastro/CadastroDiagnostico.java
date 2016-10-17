package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.Diagnostico;

public class CadastroDiagnostico {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Diagnostico diagnostico) {
		this.session.save(diagnostico);

	}

	public void atualizar(Diagnostico diagnostico) {
		this.session.update(diagnostico);
	}

	public void excluir(Diagnostico diagnostico) {
		this.session.delete(diagnostico);
	}

	public Diagnostico carregar(Integer codigo) {
		return (Diagnostico) this.session.get(Diagnostico.class, codigo);
	}

	public List<Diagnostico> listar() {
//		return this.session.createCriteria(Paciente.class).addOrder(Order.asc("nome")).list();
		return this.session.createCriteria(Diagnostico.class).list();
	}
}
