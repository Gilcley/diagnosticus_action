package br.com.diagnosticus_action.Tratador;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.diagnosticus_action.Cadastro.CadastroImagemExame;
import br.com.diagnosticus_action.dominio.ImagemExames;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorImagemExames")
@SessionScoped
public class TratadorDeImagemExames {
	private ImagemExames imagemexames = new ImagemExames();
	private CadastroImagemExame cadastroImagemExame;
	private List<SelectItem> selectImagemExame;
	private String novoNome;

	public TratadorDeImagemExames() {

	}

	public void starSession() {
		this.cadastroImagemExame = DAOFactory.criarImagemExameDAO();
	}

	public void adicionarImagemExame() {
		this.cadastroImagemExame = new CadastroImagemExame();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();
		try {
			this.cadastroImagemExame.salvar(imagemexames);
			facesMessage = new FacesMessage("Inserida com sucesso");
		} catch (Exception e) {
			facesMessage = new FacesMessage(
					"Não foi possível inserir!");
		} finally {
			context.addMessage(null, facesMessage);
		}

	}

	public List<ImagemExames> listar() {
		this.cadastroImagemExame = new CadastroImagemExame();
		starSession();
		return this.cadastroImagemExame.listar();
	}


	public ImagemExames getImagemExames() {
		return imagemexames;
	}

	public void setImagemExames(ImagemExames imagemexames) {
		this.imagemexames = imagemexames;
	}

}
