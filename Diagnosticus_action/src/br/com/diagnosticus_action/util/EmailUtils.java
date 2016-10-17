package br.com.diagnosticus_action.util;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.diagnosticus_action.dominio.Mensagem;


public class EmailUtils {
	

	 
	 public static void enviaEmail(Mensagem mensagem) throws EmailException {
//		    Email email = new SimpleEmail();
//		    email.setDebug(true);
//		    email.setHostName("smtp.googlemail.com");
//		    email.setSmtpPort(465);
//		    email.setAuthenticator(new DefaultAuthenticator("diagnosticusactionjogo@gmail.com", "!!!1a2b3c"));
//		    email.setStartTLSRequired(true);
//		    email.setSSLOnConnect(true);
//		    email.setFrom("diagnosticusactionjogo@gmail.com");
//		    email.setSubject("TestMail");
//		    email.setMsg("This is a test mail ... :-)");
//		    email.addTo(mensagem.getDestino());
//		    email.send();
		 
			 Email email = new SimpleEmail();
			 email.setDebug(true);
			 email.setSmtpPort(587);
//			 email.setHostName("smtp.gmail.com");
			 email.setHostName("smtp-mail.outlook.com");
			 email.setAuthentication("diagnosticusaction", "!!33AA&&");
			 email.setStartTLSEnabled(true);
			 email.setStartTLSRequired(true);
			 email.setFrom("diagnosticusaction@outlook.com", "Diagnosticus Action III");
			 email.setSubject("Hi this is testing email only");
			 email.setMsg("Hello there testing to send email from GMail");
			 email.addTo(mensagem.getDestino());
			 email.send();
	 
	 }
	 
	}
