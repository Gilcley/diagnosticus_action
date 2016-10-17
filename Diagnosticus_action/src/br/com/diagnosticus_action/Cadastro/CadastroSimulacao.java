package br.com.diagnosticus_action.Cadastro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.Simulacao;

public class CadastroSimulacao {


	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Simulacao simulacao) {
		this.session.save(simulacao);

	}

	public void atualizar(Simulacao simulacao) {
		this.session.update(simulacao);
	}

	public void excluir(Simulacao simulacao) {
		this.session.delete(simulacao);
	}

	public Simulacao carregar(Integer codigo) {
		return (Simulacao) this.session.get(Simulacao.class, codigo);
	}

	public List<Simulacao> listar() {
		return this.session.createCriteria(Simulacao.class).list();
	}
	
	public List<Integer> consultarSimulacaoPorAluno(int idUsuario) throws SQLException{
		Connection  con = session.connection();
		List<Integer> listaIdSimulacao = new ArrayList<>();
		PreparedStatement stm = con.prepareStatement("select idsimulacao from simulacao_usuario where idusuario="+idUsuario);
		  ResultSet res = stm.executeQuery();
		  while(res.next()){
			  listaIdSimulacao.add(res.getInt("idsimulacao"));
         }
		return listaIdSimulacao;
	}
}
