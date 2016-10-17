
package br.com.diagnosticus_action.Cadastro;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.CID;

public class CadastroCID {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(CID cid) {
		this.session.save(cid);

	}

	public void atualizar(CID cid) {
		this.session.update(cid);
	}

	public void excluir(CID cid) {
		this.session.delete(cid);
	}

	public CID carregar(String codigo) {
		return (CID) this.session.get(CID.class, codigo);
	}

	public List<CID> listar() {
		return this.session.createCriteria(CID.class).list();
	}

	public CID buscarCID(String cid) {
		String hql = "select c from CID c where c.CID = :CID";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("CID", cid);
		return (CID) consulta.uniqueResult();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CID> findCidByDescricao(String descricao){
		String sql= " select c.codigo, c.descricao_abreviada from cid c where  c.descricao ilike ('"+descricao+"%')";
		Query consulta = session.createSQLQuery(sql);
		List<Object> lista = consulta.list();
		List<CID> resultado= new ArrayList<>();
		CID cid = new CID();
		for (int i = 0; i < lista.size(); i++) {
			Object [] coluna= (Object[]) lista.get(i);
			cid.setCodigo((String) coluna [0]);
			cid.setDescricaoAbreviada((String) coluna[1]);
			resultado.add(cid);
			cid = new CID();
		}
		return resultado;
	}
}
