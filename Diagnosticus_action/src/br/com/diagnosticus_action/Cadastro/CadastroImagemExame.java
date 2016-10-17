package br.com.diagnosticus_action.Cadastro;

import java.util.List;

import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.ImagemExames;

public class CadastroImagemExame {


	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(ImagemExames imagemexames) {
		this.session.save(imagemexames);

	}

	public void atualizar(ImagemExames imagemexames) {
		this.session.update(imagemexames);
	}

	public void excluir(ImagemExames imagemexames) {
		this.session.delete(imagemexames);
	}

	public ImagemExames carregar(Integer codigo) {
		return (ImagemExames) this.session.get(ImagemExames.class, codigo);
	}

	public List<ImagemExames> listar() {
		return this.session.createCriteria(ImagemExames.class).list();
	}
}
