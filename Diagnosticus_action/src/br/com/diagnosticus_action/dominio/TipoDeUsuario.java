package br.com.diagnosticus_action.dominio;

import java.util.ArrayList;
import java.util.List;

public class TipoDeUsuario {
	
	/*Define o tipo de usuario '1' para o professor*/
	public final  static int  PROFESSOR = 1;
	
	/*Define o tipo de ususario '2' para o aluno*/
	public final static int ALUNO = 2;
	
	private String nome;
	
	private int id;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
