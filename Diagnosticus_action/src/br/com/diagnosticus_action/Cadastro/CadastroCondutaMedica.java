package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.CondutaMedica;

public class CadastroCondutaMedica {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(CondutaMedica condutamedica) {
		this.session.save(condutamedica);

	}

	public void atualizar(CondutaMedica condutamedica) {
		this.session.update(condutamedica);
	}

	public void excluir(CondutaMedica condutamedica) {
		this.session.delete(condutamedica);
	}

	public CondutaMedica carregar(Integer codigo) {
		return (CondutaMedica) this.session.get(CondutaMedica.class, codigo);
	}

	public List<CondutaMedica> listar() {
//		return this.session.createCriteria(Paciente.class).addOrder(Order.asc("nome")).list();
		return this.session.createCriteria(CondutaMedica.class).list();
	}
}
