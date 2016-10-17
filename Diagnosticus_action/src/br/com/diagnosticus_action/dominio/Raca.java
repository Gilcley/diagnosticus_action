package br.com.diagnosticus_action.dominio;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name="raca")
public class Raca implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RACA_SEQ")
    @SequenceGenerator(name = "RACA_SEQ", sequenceName = "raca_seq", allocationSize = 1)
    @Column(name = "idraca")
	private Integer IdRaca;
	
	@Column(name="raca")
	private String raca;

	public Raca() {
	}

	public Integer getIdRaca() {
		return IdRaca;
	}

	public void setIdRaca(Integer idRaca) {
		IdRaca = idRaca;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}
}
