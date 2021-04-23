package br.com.zupacademy.wagner.proposta.novaProposta;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// atributos basico
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String complemento;
	
	@Length(max = 2)
	@NotBlank
	private String uf;
	
	// construtor default
	
	@Deprecated
	public Endereco() {
		
	}

	public Endereco(@NotBlank String logradouro, @NotBlank String bairro, @NotBlank String complemento,
			@Length(max = 2) @NotBlank String uf) {
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.complemento = complemento;
		this.uf = uf;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getUf() {
		return uf;
	}
	
	

}
