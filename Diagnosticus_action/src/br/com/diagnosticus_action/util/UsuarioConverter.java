package br.com.diagnosticus_action.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.diagnosticus_action.Cadastro.CadastroUsuario;
import br.com.diagnosticus_action.dominio.Usuario;
 
@FacesConverter("usuarioConverter")
public class UsuarioConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
            	CadastroUsuario dao = DAOFactory.criarUsuarioDAO();
            	Usuario user = dao.carregar(Integer.parseInt(value));
                return user;
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Usuario) object).getIdUsuario());
        }
        else {
            return null;
        }
    }   
} 