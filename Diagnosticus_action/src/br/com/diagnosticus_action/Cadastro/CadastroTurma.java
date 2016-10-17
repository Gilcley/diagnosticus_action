package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.Turma;

public class CadastroTurma {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Turma turma) {
		this.session.save(turma);

	}

	public void atualizar(Turma turma) {
		this.session.update(turma);
	}

	public void excluir(Turma turma) {
		this.session.delete(turma);
	}

	public Turma carregar(Integer codigo) {
		return (Turma) this.session.get(Turma.class, codigo);
	}

	public List<Turma> listar() {
		return this.session.createCriteria(Turma.class).list();
	}

	public Turma buscarTurma(String turma) {
		String hql = "select t from Turma t where t.turma = :turma";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("turma", turma);
		return (Turma) consulta.uniqueResult();
	}
}
