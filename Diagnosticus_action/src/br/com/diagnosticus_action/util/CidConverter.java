package br.com.diagnosticus_action.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.diagnosticus_action.Cadastro.CadastroCID;
import br.com.diagnosticus_action.dominio.CID;
 
@FacesConverter("cidConverter")
public class CidConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
            	CadastroCID dao = DAOFactory.criarCidDAO();
            	CID cid = dao.carregar(value);
                return cid;
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
            return String.valueOf(((CID) object).getCodigo());
        }
        else {
            return null;
        }
    }   
} 