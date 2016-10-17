package br.com.diagnosticus_action.dominio;

import javax.persistence.*;

@Entity
@Table (name="estadocivil")
public class EstadoCivil {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTADOCIVIL_SEQ")
    @SequenceGenerator(name = "ESTADOCIVIL_SEQ", sequenceName = "estadocivil_seq", allocationSize = 1)
    @Column(name = "idestadocivil")
	private Integer IdEstadoCivil;

	@Column(name = "estadocivil")
	private String EstadoCivil;
	
	public EstadoCivil() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getIdEstadoCivil() {
		return IdEstadoCivil;
	}
	
	public void setIdEstadoCivil(Integer idestadocivil) {
		IdEstadoCivil = idestadocivil;
	}
	
	public String getEstadoCivil() {
		return EstadoCivil;
	}
	public void setEstadoCivil(String pEstadoCivil) {
		EstadoCivil = pEstadoCivil;
	}
}
