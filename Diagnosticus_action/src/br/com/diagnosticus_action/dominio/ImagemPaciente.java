

package br.com.diagnosticus_action.dominio;

	import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


	@Entity
	@Table(name = "imagem_paciente" )
	public class ImagemPaciente {

		public static final int ADOLESCENTE = 1;
		public static final int ADULTO = 2;
		public static final int BEBE = 3;
		public static final int CRIANCA = 4;
        public static final int IDOSO = 5;
		
		@Id
		@Column(name = "id_imagem_paciente")
		private Integer id;
		
		@Column(name = "sexo")
		private String sexo;
		
		@ManyToOne
		@JoinColumn(name="id_raca")
		private Raca raca;
		
		/** 1-ADOLESCENTE, 2-ADULTO, 3-BEBÊ, 4-CRIANÇA, 5-IDOSO*/
		@Column(name = "classificacao")
		private int classificacao;
		
		@Column(name = "imagem")
		private String imagem;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getSexo() {
			return sexo;
		}

		public void setSexo(String sexo) {
			this.sexo = sexo;
		}

		public Raca getRaca() {
			return raca;
		}

		public void setRaca(Raca raca) {
			this.raca = raca;
		}

		public int getClassificacao() {
			return classificacao;
		}

		public void setClassificacao(int classificacao) {
			this.classificacao = classificacao;
		}

		public String getImagem() {
			return imagem;
		}

		public void setImagem(String imagem) {
			this.imagem = imagem;
		}
		
	}

	
