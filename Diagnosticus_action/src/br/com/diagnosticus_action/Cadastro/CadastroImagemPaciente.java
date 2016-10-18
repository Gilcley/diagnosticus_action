package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.ImagemPaciente;
import br.com.diagnosticus_action.dominio.Usuario;

public class CadastroImagemPaciente {


	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(ImagemPaciente imagemPaciente) {
		this.session.save(imagemPaciente);

	}

	public void atualizar(ImagemPaciente imagemPaciente) {
		this.session.update(imagemPaciente);
	}

	public void excluir(ImagemPaciente imagemPaciente) {
		this.session.delete(imagemPaciente);
	}

	public ImagemPaciente carregar(Integer codigo) {
		return (ImagemPaciente) this.session.get(ImagemPaciente.class, codigo);
	}

	public List<ImagemPaciente> listar() {
		return this.session.createCriteria(ImagemPaciente.class).list();
	}
	
	public ImagemPaciente buscar(int classificacao, String sexo, int raca){
		String hql = "select i from ImagemPaciente i where i.classificacao = :classificacao and i.sexo = :sexo and i.raca = :raca ";
		Query consulta = this.session.createQuery(hql);
		consulta.setInteger("classificacao", classificacao);
		consulta.setString("sexo", sexo);
		consulta.setInteger("raca", raca);
		return (ImagemPaciente) consulta.uniqueResult();
	}
}
