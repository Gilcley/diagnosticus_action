package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.TipoCaracteristica;

public class CadastroTipoCaracteristica {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(TipoCaracteristica tipocaracteristica) {
		this.session.save(tipocaracteristica);

	}

	public void atualizar(TipoCaracteristica tipocaracteristica) {
		this.session.update(tipocaracteristica);
	}

	public void excluir(TipoCaracteristica tipocaracteristica) {
		this.session.delete(tipocaracteristica);
	}

	public TipoCaracteristica carregar(Integer codigo) {
		return (TipoCaracteristica) this.session.get(TipoCaracteristica.class, codigo);
	}

	public List<TipoCaracteristica> listar() {
		return this.session.createCriteria(TipoCaracteristica.class).list();
	}
	
	public TipoCaracteristica buscarRaca(String tipocaracteristica){
		String hql = "select r from TipoCaracteristica r where r.tipocaracteristica = :tipocaracteristica";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("tipocaracteristica", tipocaracteristica);
		return (TipoCaracteristica) consulta.uniqueResult();
	}
}
