package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.Jogo;
import br.com.diagnosticus_action.dominio.Simulacao;
import br.com.diagnosticus_action.dominio.Usuario;

public class CadastroJogo {


	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Jogo jogo) {
		this.session.save(jogo);

	}

	public void atualizar(Jogo jogo) {
		this.session.update(jogo);
	}

	public void excluir(Jogo jogo) {
		this.session.delete(jogo); 
	}

	public Jogo carregar(Integer codigo) {
		return (Jogo) this.session.get(Jogo.class, codigo);
	}

	public List<Jogo> listar() {
		return this.session.createCriteria(Jogo.class).list();
	}
	
	public Jogo buscarPorSimulacao(int idsimulacao){
		String hql = "select u from Jogo u where u.Simulacao = :idsimulacao";
		Query consulta = this.session.createQuery(hql);
		consulta.setInteger("idsimulacao", idsimulacao);
		return (Jogo) consulta.uniqueResult();
	}
	
	public List<Jogo> buscarJogo(int idsimulacao){
		String hql = "select u from Jogo u where u.Simulacao = :idsimulacao";
		Query consulta = this.session.createQuery(hql);
		consulta.setInteger("idsimulacao", idsimulacao);
		return (List<Jogo>) consulta.list();
	}
	
	public Jogo buscarJogo(int idusuario, int idsimulacao){
		String hql = "select u from Jogo u where u.Simulacao = :idsimulacao and u.Aluno = :idusuario";
		Query consulta = this.session.createQuery(hql);
		consulta.setInteger("idsimulacao", idsimulacao);
		consulta.setInteger("idusuario", idusuario);
		return (Jogo) consulta.uniqueResult();
	}
}
