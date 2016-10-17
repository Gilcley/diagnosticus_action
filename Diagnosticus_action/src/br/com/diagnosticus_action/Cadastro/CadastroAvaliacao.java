package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.Avaliacao;
import br.com.diagnosticus_action.dominio.Jogo;

public class CadastroAvaliacao {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Avaliacao avaliacao) {
		this.session.save(avaliacao);

	}

	public void atualizar(Avaliacao avaliacao) {
		this.session.update(avaliacao);
	}

	public void excluir(Avaliacao avaliacao) {
		this.session.delete(avaliacao);
	}

	public Avaliacao carregar(Integer codigo) {
		return (Avaliacao) this.session.get(Avaliacao.class, codigo);
	}

	public List<Avaliacao> listar() {
		return this.session.createCriteria(Avaliacao.class).list();
	}
	
	public Avaliacao buscarPorAvaliacao(int idjogo){
		String hql = "select u from Avaliacao u where u.Jogo = :idjogo";
		Query consulta = this.session.createQuery(hql);
		consulta.setInteger("idjogo", idjogo);
		return (Avaliacao) consulta.uniqueResult();
	}

}
