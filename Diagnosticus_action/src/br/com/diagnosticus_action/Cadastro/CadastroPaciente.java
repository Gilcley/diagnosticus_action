package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import javax.persistence.criteria.Order;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.diagnosticus_action.dominio.Paciente;
import br.com.diagnosticus_action.dominio.Usuario;

public class CadastroPaciente {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Paciente paciente) {
		this.session.save(paciente);

	}

	public void atualizar(Paciente paciente) {
		this.session.update(paciente);
	}

	public void excluir(Paciente paciente) {
		this.session.delete(paciente);
	}

	public Paciente carregar(Integer codigo) {
		return (Paciente) this.session.get(Paciente.class, codigo);
	}

	public List<Paciente> listar() {
//		return this.session.createCriteria(Paciente.class).addOrder(Order.asc("nome")).list();
		return this.session.createCriteria(Paciente.class).list();
	}
}
