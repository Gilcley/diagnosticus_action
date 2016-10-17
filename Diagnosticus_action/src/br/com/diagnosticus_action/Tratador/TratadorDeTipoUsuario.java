package br.com.diagnosticus_action.Tratador;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.diagnosticus_action.Cadastro.CadastroTipoUsuario;
import br.com.diagnosticus_action.dominio.TipoUsuario;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorTipoUsuario")
@SessionScoped
public class TratadorDeTipoUsuario {
	private TipoUsuario tipousuario = new TipoUsuario();
	private CadastroTipoUsuario cadastroTipoUsuario;
	private List<SelectItem> selectTipoUsuario;
	private String novoNome;

	public TratadorDeTipoUsuario() {

	}

	public void starSession() {
		this.cadastroTipoUsuario = DAOFactory.criarTipoUsuarioDAO();
	}

	public void adicionarTipoUsuario() {
		this.cadastroTipoUsuario = new CadastroTipoUsuario();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();
		try {
			this.cadastroTipoUsuario.salvar(tipousuario);
			facesMessage = new FacesMessage("Inserido com sucesso");
		} catch (Exception e) {
			facesMessage = new FacesMessage("Não foi possível inserir!");
		} finally {
			context.addMessage(null, facesMessage);
		}

	}

	public List<TipoUsuario> listar() {
		this.cadastroTipoUsuario = new CadastroTipoUsuario();
		starSession();
		return this.cadastroTipoUsuario.listar();
	}

	public List<SelectItem> getSelectTipoUsuario() {
		this.cadastroTipoUsuario = new CadastroTipoUsuario();
		starSession();
		List<TipoUsuario> listaTipoUsuario = this.cadastroTipoUsuario.listar();
		this.selectTipoUsuario = new ArrayList<>();
		for (TipoUsuario tipousuario : listaTipoUsuario) {
			this.selectTipoUsuario.add(new SelectItem(tipousuario, tipousuario
					.getTipoUsuario()));
		}
		return selectTipoUsuario;
	}

	public TipoUsuario getTipoUsuario() {
		return tipousuario;
	}

	public void setTipoUsuario(TipoUsuario tipousuario) {
		this.tipousuario = tipousuario;

	}

}
