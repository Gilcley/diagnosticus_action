package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.TipoExame;

public class CadastroTipoExame {


	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(TipoExame tipoexame) {
		this.session.save(tipoexame);

	}

	public void atualizar(TipoExame tipoexame) {
		this.session.update(tipoexame);
	}

	public void excluir(TipoExame tipoexame) {
		this.session.delete(tipoexame);
	}

	public TipoExame carregar(Integer codigo) {
		return (TipoExame) this.session.get(TipoExame.class, codigo);
	}

	public List<TipoExame> listar() {
		return this.session.createCriteria(TipoExame.class).list();
	}
}
