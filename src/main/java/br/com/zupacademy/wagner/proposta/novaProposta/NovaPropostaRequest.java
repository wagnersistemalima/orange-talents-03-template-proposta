package br.com.zupacademy.wagner.proposta.novaProposta;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.wagner.proposta.validacao.CpfOuCnpj;

public class NovaPropostaRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// atributos basicos
	
	@NotBlank
	private String nome;
	
	@CpfOuCnpj(domainClass = Proposta.class, fieldName = "documento")
	@NotBlank
	private String documento;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String complemento;
	
	@Length(max = 2)
	@NotBlank
	private String uf;
	
	@Positive
	@NotNull
	private BigDecimal salario;
	
	// construtor com argumentos
	
	@JsonCreator
	public NovaPropostaRequest(@NotBlank String nome, @NotBlank String documento, @Email @NotBlank String email,
			@NotBlank String logradouro, @NotBlank String bairro, @NotBlank String complemento,
			@Length(max = 2) @NotBlank String uf, @Positive @NotNull BigDecimal salario) {
		this.nome = nome;
		this.documento = documento.replaceAll("[^0-9]", ""); // retira os caracteres . / - do documento
		this.email = email;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.complemento = complemento;
		this.uf = uf.toUpperCase();
		this.salario = salario;
	}

	// getters

	public String getDocumento() {
		return documento;
	}
	

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
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



	public BigDecimal getSalario() {
		return salario;
	}

	// metodo para converter request em entidade

	public Proposta toModel() {
				
		
		
		return new Proposta(nome, documento, email, salario);
	}

}
