package br.com.diagnosticus_action.Tratador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;

import org.apache.commons.mail.EmailException;

import br.com.diagnosticus_action.Cadastro.CadastroUsuario;
import br.com.diagnosticus_action.dominio.Mensagem;
import br.com.diagnosticus_action.dominio.Usuario;
import br.com.diagnosticus_action.util.DAOFactory;
import br.com.diagnosticus_action.util.EmailUtils;

@ManagedBean(name = "tratadorEmail")
@SessionScoped
public class TratadorDeEmail {

	private Mensagem mensagem = new Mensagem();
	private CadastroUsuario cadastro = new CadastroUsuario();

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public String enviaEmail() throws MessagingException {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		cadastro = new DAOFactory().criarUsuarioDAO();
		Usuario usuario = cadastro.buscarPorEmail(mensagem.getDestino());
		if(usuario==null){
			facesMessage = new FacesMessage("Este email não está cadastrado");
			context.addMessage(null, facesMessage);
			return null;
		}
		
		mensagem.setMensagem(usuario.getSenha());
		
		try {
			EmailUtils.enviaEmail(mensagem);
		} catch (EmailException ex) {
			facesMessage = new FacesMessage("Não foi possível enviar o email");
			context.addMessage(null, facesMessage);
		}
		return null;
	}

	public void limpaCampos() {
		mensagem = new Mensagem();
	}

}
