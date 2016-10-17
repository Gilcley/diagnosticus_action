package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.Raca;
import br.com.diagnosticus_action.dominio.Usuario;

public class CadastroRaca {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Raca raca) {
		this.session.save(raca);

	}

	public void atualizar(Raca raca) {
		this.session.update(raca);
	}

	public void excluir(Raca raca) {
		this.session.delete(raca);
	}

	public Raca carregar(Integer codigo) {
		return (Raca) this.session.get(Raca.class, codigo);
	}

	public List<Raca> listar() {
		return this.session.createCriteria(Raca.class).list();
	}
	
	public Raca buscarRaca(String raca){
		String hql = "select r from Raca r where r.raca = :raca";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("raca", raca);
		return (Raca) consulta.uniqueResult();
	}
}
